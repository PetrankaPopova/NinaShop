package diplomna.service.serviceImpl;

import diplomna.exception.AlreadyExistsException;
import diplomna.model.entity.Category;
import diplomna.model.entity.Product;
import diplomna.model.service.ProductServiceModel;
import diplomna.repository.ProductRepository;
import diplomna.service.ProductService;
import diplomna.view.ProductViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static diplomna.constant.Constants.PRODUCT_NAME_EXISTS_MESSAGE;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ProductServiceImpl(ProductRepository repository, ModelMapper mapper) {
        this.productRepository = repository;
        this.modelMapper = mapper;

    }

    @Override
    public ProductServiceModel findByProductName(String name) {
        return this.productRepository.findByName(name)
                .map(product -> this.modelMapper.map(product,ProductServiceModel.class))
                .orElse(null);
    }

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {

        if (productRepository.existsByName(productServiceModel.getName())) {
            throw new AlreadyExistsException("name", PRODUCT_NAME_EXISTS_MESSAGE);
        }

        Product product = modelMapper.map(productServiceModel, Product.class);
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