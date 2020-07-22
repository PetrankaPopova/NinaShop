package diplomna.service.serviceImpl;

import diplomna.exception.AlreadyExistsException;
import diplomna.model.entity.User;
import diplomna.model.service.UserServiceModel;
import diplomna.repository.ProductRepository;
import diplomna.repository.RoleRepository;
import diplomna.repository.UserRepository;
import diplomna.service.RoleService;
import diplomna.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static diplomna.constant.Constants.USER_EMAIL_EXISTS_MASSAGE;
import static diplomna.constant.Constants.USER_NAME_EXISTS_MESSAGE;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository,
                          RoleService roleService, ProductRepository productRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);
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
       // user.setAuthorities(new HashSet<>(Set.of(Objects.requireNonNull(this.roleRepository.findByAuthority("USER")
           //     .orElse(null)))));


      //  return this.modelMapper.map(this.userRepository
               // .saveAndFlush(user),UserServiceModel.class);

        /*User saved = this.userRepository.findByUsername(userServiceModel.getUsername()).orElse(null);
        if (saved != null)
            throw new UsernameAlreadyExistException("User with username " + saved.getUsername() + " already exists!");

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User u = this.userRepository.saveAndFlush(user);
        return this.modelMapper.map(u, UserServiceModel.class);*/
        /*User savedUser = null;
        try {
            savedUser = this.userRepository.saveAndFlush(user);
        } catch (Exception ignored) {
            throw new UserRegistrationException("Cannot register user with username " + user.getUsername());
        }*/

    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        User findedUser = this.userRepository.findUserByUsernameAndPassword(username, password).orElse(null);
        return findedUser == null ? null : this.modelMapper.map(findedUser, UserServiceModel.class);
    }
    @Override
    public UserServiceModel findByUsername(String username) {
        User findedUser = this.userRepository.findUserByUsername(username).orElse(null);
        return findedUser == null ? null : this.modelMapper.map(findedUser, UserServiceModel.class);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = this.userRepository.findUserByUsername(username).orElse(null);
        if (findUser == null) {
            throw new UsernameNotFoundException("User does not exists!");
        }
        return findUser;
    }
}
