package diplomna.web;

import diplomna.model.entity.Order;
import diplomna.repository.OrderRepository;
import diplomna.service.serviceImpl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class OrderTest {
    private OrderRepository orderRepository;
    private OrderServiceImpl orderService;

    @Before
    public void setupTest() {
     //   orderService = new OrderServiceImpl();
    }

    @Test
    public void getAllOrders_whenExist_ShouldReturnAllOrderInDb(){

        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());

        when(orderRepository.findAll())
                .thenReturn(orders);

        List<Order>allOrders = orderRepository.findAll();

        assertEquals(2, allOrders.size());
    }
}
