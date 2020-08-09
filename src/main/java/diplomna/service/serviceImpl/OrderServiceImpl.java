package diplomna.service.serviceImpl;

import diplomna.error.exception.UserIsNotExistException;
import diplomna.model.entity.Order;
import diplomna.model.entity.Product;
import diplomna.model.entity.User;
import diplomna.model.service.OrderServiceModel;
import diplomna.repository.OrderRepository;
import diplomna.repository.UserRepository;
import diplomna.service.OrderService;
import diplomna.service.ProductService;
import diplomna.web.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final Tools tools;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(ProductService productService, ModelMapper modelMapper, Tools tools, UserRepository userRepository, OrderRepository orderRepository) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.tools = tools;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }





    @Override
    public void createOrder() {
        String userStr = this.tools.getLoggedUser();
        User u = this.userRepository.findByUsername(userStr).orElse(null);
        if (u == null) {
            throw new UserIsNotExistException("User is not Exist!");
        }
        if (u != null && u.getBoughtProducts() != null
                && u.getBoughtProducts().size() > 0) {
            for (Product product : u.getBoughtProducts()) {
                Order order = new Order();
                order.setUsername(u.getUsername());
                order.setAddress(u.getUserAddress());
                order.setName(product.getName());
                order.setPrice(product.getPrice());
                this.orderRepository.saveAndFlush(order);
            }
            u.getBoughtProducts().clear();
            this.userRepository.saveAndFlush(u);
        }

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
