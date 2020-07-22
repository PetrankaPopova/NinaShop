package diplomna.service;


import diplomna.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    UserServiceModel findByUsername(String username);

    void deleteProductById(String id);

    void deleteAllProducts();


}
