package diplomna.service;

import diplomna.model.service.OrderServiceModel;

import java.util.List;

public interface OrderService {
    void createOrder();

    List<OrderServiceModel> findAllOrders();

    List<OrderServiceModel> findOrdersByCustomer(String username);

    OrderServiceModel findOrderById(String id);
}
