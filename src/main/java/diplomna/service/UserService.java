package diplomna.service;


import diplomna.error.exception.ProductIsNotExistException;
import diplomna.error.exception.UserCannotSaveException;
import diplomna.error.exception.UserPasswordsNotMatchException;
import diplomna.error.exception.UserWithUsernameAlreadyExistException;
import diplomna.model.service.UserEditServiceModel;
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

     UserEditServiceModel editUserProfile(UserEditServiceModel userEditServiceModel, String oldPassword) throws UserPasswordsNotMatchException, UserWithUsernameAlreadyExistException;


    List<ProductViewModel> getAllBoughtProducts ();


    List<UserServiceModel> findAllUsers();


    UserServiceModel findById(String userId);

    void buyProduct(String productId, String loggedUserStr) throws UserCannotSaveException;

    void removeAllProductCart(String loggedUserStr);




}
