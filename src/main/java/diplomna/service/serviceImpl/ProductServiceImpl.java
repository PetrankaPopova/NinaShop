package diplomna.service.serviceImpl;

import diplomna.exception.AlreadyExistsException;
import diplomna.model.entity.Product;
import diplomna.model.service.ProductServiceModel;
import diplomna.model.view.ProductViewModel;
import diplomna.repository.ProductRepository;
import diplomna.service.CloudinaryService;
import diplomna.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static diplomna.constant.GlobalConstants.PRODUCT_NAME_EXISTS_MESSAGE;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;


    @Autowired
    public ProductServiceImpl(ProductRepository repository, ModelMapper mapper, CloudinaryService cloudinaryService) {
        this.productRepository = repository;
        this.modelMapper = mapper;

        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public ProductServiceModel findByProductName(String name) {
        return this.productRepository.findByName(name)
                .map(product -> this.modelMapper.map(product,ProductServiceModel.class))
                .orElse(null);
    }

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) throws IOException {

        if (productRepository.existsByName(productServiceModel.getName())) {
            throw new AlreadyExistsException("name", PRODUCT_NAME_EXISTS_MESSAGE);
        }
        Product product = modelMapper.map(productServiceModel, Product.class);
        if (productServiceModel.getPhoto().length > 0) {
            for (MultipartFile photo : productServiceModel.getPhoto()) {
                String imgUrl = this.cloudinaryService.uploadImage(photo);
                product.setUrl(imgUrl);
                break;
            }
        } else {
            product.setUrl("");
        }
        return modelMapper.map(productRepository.save(product), ProductServiceModel.class);
    }

    @Override
    public List<ProductViewModel> findAllProducts() {
       return productRepository.findAll()
                .stream()
               // .filter(product -> product.getCategory().getCategoryType() == categoryType)
                .map(product -> modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    @Override
    public void buyById(String id) {
        productRepository.findById(id).ifPresent(productRepository::delete);
    }

    @Override
    public void buyAll() {
        productRepository.deleteAll();

    }
}