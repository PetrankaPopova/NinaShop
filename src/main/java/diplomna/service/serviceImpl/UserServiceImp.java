package diplomna.service.serviceImpl;

import diplomna.error.constant.GlobalConstants;
import diplomna.error.exception.*;
import diplomna.model.entity.Product;
import diplomna.model.entity.User;
import diplomna.model.service.UserServiceModel;
import diplomna.model.view.ProductViewModel;
import diplomna.repository.ProductRepository;
import diplomna.repository.RoleRepository;
import diplomna.repository.UserRepository;
import diplomna.service.RoleService;
import diplomna.service.UserService;
import diplomna.web.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static diplomna.error.constant.GlobalConstants.USER_EMAIL_EXISTS_MASSAGE;
import static diplomna.error.constant.GlobalConstants.USER_NAME_EXISTS_MESSAGE;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RoleService roleService;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Tools tools;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository,
                          RoleService roleService, ProductRepository productRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, Tools tools) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tools = tools;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        User returnedUserFromDb = this.userRepository.findUserByUsername(userServiceModel.getUsername()).orElse(null);
        if (userRepository.existsByUsername(userServiceModel.getUsername())) {
            throw new AlreadyExistsException("username", USER_NAME_EXISTS_MESSAGE);
        }
        if (userRepository.existsByEmail(userServiceModel.getEmail())) {
            throw new AlreadyExistsException("email", USER_EMAIL_EXISTS_MASSAGE);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (this.userRepository.count() == 0) {
            this.roleService.seedRolesToDb();
            user.setAuthorities(new HashSet<>(this.roleRepository.findAll()));
        } else {
            user.setAuthorities(new HashSet<>());
            user.getAuthorities().add(this.roleRepository.findByAuthority("USER").orElse(null));
        }
        this.userRepository.saveAndFlush(user);
        return this.modelMapper.map(user, UserServiceModel.class);


    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        User findedUser = this.userRepository.findUserByUsernameAndPassword(username, password).orElse(null);
        return findedUser == null ? null : this.modelMapper.map(findedUser, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User findedUser = this.userRepository.findUserByUsername(username).orElse(null);
        return this.modelMapper.map(findedUser, UserServiceModel.class);
    }

    @Override
    public void deleteProductById(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        this.productRepository.deleteAll();
    }

    @Override
    public void buyProduct(String productId) {
        Product p = this.productRepository.findById(productId).orElse(null);
        String userStr = this.tools.getLoggedUser();
        if ("anonymousUser".equals(userStr)) {
            throw new UserWithThisNameIsNotLogged("User with this name is not logged!");
        }
        User u = this.userRepository.findByUsername(userStr).orElse(null);
        if (u == null) {
            throw new UserIsNullOrCartIsNullException("User is null or car is null exception!");
        }
        u.getBoughtProducts().add(p);
        this.userRepository.saveAndFlush(u);
    }


    @Override
    public UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword) {

        User user = this.userRepository.findByUsername(userServiceModel.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(GlobalConstants.USER_ID_NOT_FOUND));

        if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("NOT CORRECT");
        }

        user.setPassword(!"".equals(userServiceModel.getPassword()) ?
                this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());
        user.setEmail(userServiceModel.getEmail());

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }


    @Override
    public List<ProductViewModel> getAllBoughtProducts() {
        String userStr = this.tools.getLoggedUser();
        User user = this.userRepository.findByUsername(userStr)
                .orElseThrow(() -> new UserNotFoundException("User with given id was not found!"));
        return user.getBoughtProducts().stream()
                .map(pr -> this.modelMapper.map(pr, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll().
                stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public UserServiceModel findById(String userId) {
        User u = this.userRepository.findById(userId).orElse(null);
        if (u == null) {
            throw new UserIsNotExistException("User is not Exist!");
        }
        UserServiceModel userServiceModel = this.modelMapper.map(u, UserServiceModel.class);
        return userServiceModel;
    }

    @Override
    public void buyProduct(String productId, String loggedUserStr) throws UserCannotSaveException {
        Product productForBye = this.productRepository.findById(productId).orElse(null);
        User loggedUser = this.userRepository.findByUsername(loggedUserStr).orElse(null);
        if (this.isInputDataCorrect(loggedUser, productForBye)
                && !loggedUser.getBoughtProducts().contains(productForBye)) {
            loggedUser.getBoughtProducts().add(productForBye);
            this.userRepository.saveAndFlush(loggedUser);
        } else {
            throw new UserCannotSaveException("User cannot be save!");
        }

    }

    @Override
    public void removeAllProductCart(String loggedUserStr) {
        User loggedUser = this.userRepository.findByUsername(loggedUserStr).orElse(null);
        if (loggedUser != null) {
            loggedUser.getBoughtProducts().clear();
            this.userRepository.saveAndFlush(loggedUser);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = this.userRepository.findUserByUsername(username).orElse(null);
        if (findUser == null) {
            throw new UsernameNotFoundException("User does not exists!");
        }
        return findUser;
    }


    private boolean isInputDataCorrect(User loggedUser, Product productForBuy) {
        return loggedUser != null && productForBuy != null;
    }
}

