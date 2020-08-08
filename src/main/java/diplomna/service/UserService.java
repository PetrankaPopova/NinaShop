package diplomna.service;


import diplomna.error.exception.ProductIsNotExistException;
import diplomna.error.exception.UserCannotSaveException;
import diplomna.model.service.UserServiceModel;
import diplomna.model.view.ProductViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;
import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    UserServiceModel findByUsername(String username);

    void deleteProductById(String id);

    void deleteAllProducts();

    void buyProduct(String productId);

     UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword);

    void deleteUser(String username);

    List<ProductViewModel> getAllBoughtProducts ();


    List<UserServiceModel> findAllUsers();


    UserServiceModel findById(String userId);

    void buyProduct(String productId, String loggedUserStr) throws UserCannotSaveException;

    void removeAllProductCart(String loggedUserStr);

    void removeOneProductCart(String productId, String loggedUser) throws ProductIsNotExistException;


}
