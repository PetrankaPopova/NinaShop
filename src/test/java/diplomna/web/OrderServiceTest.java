//package diplomna.web;
//
//import diplomna.model.entity.Order;
//import diplomna.model.entity.User;
//import diplomna.model.service.UserServiceModel;
//import diplomna.repository.OrderRepository;
//import diplomna.repository.UserRepository;
//import diplomna.service.OrderService;
//import diplomna.service.ProductService;
//import diplomna.service.serviceImpl.OrderServiceImpl;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class OrderServiceTest {
//    private Optional<Order> testOrder;
//    private OrderRepository mockedOrderRepository;
//    private ModelMapper mockedModelMapper;
//    private OrderRepository orderRepository;
//    private final ProductService productService;
//    private final UserRepository userRepository;
//
//    @Autowired
//    public OrderServiceTest(OrderRepository orderRepository, ProductService productService, UserRepository userRepository) {
//        this.orderRepository = orderRepository;
//
//        this.productService = productService;
//        this.userRepository = userRepository;
//    }
//
//    @Before()
//    public void init() {
//        this.testOrder = Optional.of(new Order() {{
//            setId("aaaa");
//            setName("Pesho");
//            setPrice(BigDecimal.valueOf(480));
//            setUsername("Pesho");
//            setAddress("Sofia");
//
//        }});
//
//        this.mockedOrderRepository = Mockito.mock(OrderRepository.class);
//        this.mockedModelMapper = Mockito.mock(ModelMapper.class);
//
//    }
//
//    @Test
//    public void userService_GetUserWithCorrectUsername_shouldReturnCorrect() {
//
//
//        Mockito.when(this.mockedOrderRepository.f("Pesho"))
//                .thenReturn(this.testOrder);
//
//        Optional<User> mockedUsers = this.mockedOrderRepository.findUserByUsername("Pesho");
//        Optional<User> mockedUsers2 = this.mockedOrderRepository.findUserByUsername("Pesho");
//        UserServiceModel usm = new UserServiceModel();
//        usm.setUsername("Pesho");
//        Mockito.when(this.mockedModelMapper.map(mockedUsers.get(), UserServiceModel.class))
//                .thenReturn(usm);
//
//        OrderService orderService = new OrderServiceImpl(this.mockedOrderRepository, orderRepository, null,null,null);
//        //Optional<User> user = this.mockedUserRepository.findByUsername("Pesho");
//        // UserServiceModel user2 = userService.findUserByUsername("Pesho");
//        Optional<User> expected = this.testUser;
//        UserServiceModel actual = userService.findByUsername("Pesho");
//
//        User findedUser = this.mockedUserRepository.findUserByUsername("Pesho").orElse(null);
//        Optional<User> mockedUsers3 = this.mockedUserRepository.findUserByUsername("Pesho");
//
//
//        // Assert.assertEquals("4456d57a-fe5b-4b6a-a675-db29933236ac", expected.getId(), model.getId());
//        Assert.assertEquals("Pesho", expected.get().getUsername(), actual.getUsername());
//        // Assert.assertEquals("123", expected.getPassword(), model.getPassword());
//        // System.out.println(user2);
//    }
//}
//}
