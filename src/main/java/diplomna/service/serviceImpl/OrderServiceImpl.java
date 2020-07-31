package diplomna.service.serviceImpl;

import diplomna.model.entity.Product;
import diplomna.model.service.OrderServiceModel;
import diplomna.model.view.ProductViewModel;
import diplomna.service.OrderService;
import diplomna.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }



    /*@Scheduled(fixedRate = 300000)
    private void generateOffers() {
        this.offerRepository.deleteAll();
        List<ProductViewModel> products = this.productService.findAllProducts();

        if (products.isEmpty()) {
            return;
        }

        Random rnd = new Random();
        List<Offer> offers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Offer offer = new Offer();
            offer.setProduct(this.modelMapper.map(products.get(rnd.nextInt(products.size())), Product.class));
            offer.setPrice(offer.getProduct().getPrice().multiply(new BigDecimal(0.8)));

            if (offers.stream().filter(o -> o.getProduct().getId().equals(offer.getProduct().getId())).count() == 0) {
                offers.add(offer);
            }
        }

        this.offerRepository.saveAll(offers);
    }*/

    @Override
    public void createOrder(OrderServiceModel orderServiceModel) {

    }

    @Override
    public List<OrderServiceModel> findAllOrders() {
        return null;
    }

    @Override
    public List<OrderServiceModel> findOrdersByCustomer(String username) {
        return null;
    }

    @Override
    public OrderServiceModel findOrderById(String id) {
        return null;
    }
}
