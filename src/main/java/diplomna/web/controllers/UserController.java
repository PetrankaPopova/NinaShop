package diplomna.web.controllers;


import diplomna.model.bindingmodel.UserEditBindingModel;
import diplomna.model.bindingmodel.UserRegisterBindingModel;
import diplomna.model.service.UserServiceModel;
import diplomna.model.view.ProductViewModel;
import diplomna.model.view.UserViewModel;
import diplomna.model.view.UserView;
import diplomna.service.OrderService;
import diplomna.service.UserService;
import diplomna.web.Tools;
import diplomna.web.anotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Tools tools;
    private final ModelMapper modelMapper;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, Tools tools, ModelMapper modelMapper, OrderService orderService) {
        this.userService = userService;
        this.tools = tools;
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }


    @GetMapping("/login")
    //@PreAuthorize("isAnonymous()")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    //@PreAuthorize("isAnonymous()")
    // @PageTitle("Register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel
                                          userRegisterBindingModel, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            userRegisterBindingModel.setPassword("null");
            userRegisterBindingModel.setConfirmPassword("null");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);

            return "redirect:register";
        }
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passwordNotMatch", true);
            redirectAttributes.addFlashAttribute(" userRegisterBindingModel", userRegisterBindingModel);
        }
        this.userService.registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "login";
    }

    @GetMapping("/profile")
    //@PreAuthorize("isAuthenticated()")
    public String profile(Model model) {
        UserServiceModel user = this.userService.findByUsername(this.tools.getLoggedUser());
        UserView userView = this.modelMapper.map(user, UserView.class);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/user/buy/{productId}")
    public String buyProduct(@PathVariable("productId") String productId) {
        this.userService.buyProduct(productId);
        return "redirect:/home";
    }


    @GetMapping("/edit")
    // @PreAuthorize("isAuthenticated()")
    //  @PageTitle("Edit User")
    public String editProfile(@Valid @ModelAttribute(name = "userEditBindingModel") UserEditBindingModel userEditBindingModel,
                              Model model,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!model.containsAttribute("userEditBindingModel")) {
            model.addAttribute("UserEditBindingModel", new UserEditBindingModel());

            model.addAttribute("userEditBindingModel");
        }
        return "edit-profile";
    }

    @PostMapping("/edit")
    //  @PreAuthorize("isAuthenticated()")
    public String editProfileConfirm(@Valid @ModelAttribute(name = "userEditBindingModel") UserEditBindingModel userEditBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                     Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("userEditBindingModel", userEditBindingModel);
        }
        if (!userEditBindingModel.getPassword().equals(userEditBindingModel.getConfirmPassword())) {
            return "edit-profile";
        }
        this.userService.editUserProfile(this.modelMapper.map(userEditBindingModel, UserServiceModel.class), userEditBindingModel.getOldPassword());

        return "redirect:/profile";

    }

    @GetMapping("/delete/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Delete User")
    public ModelAndView deleteUser(@PathVariable String username, ModelAndView modelAndView) {
        UserServiceModel userServiceModel = this.userService.findByUsername(username);

        modelAndView.addObject("user", userServiceModel);
        modelAndView.addObject("userN", username);
        modelAndView.setViewName("/delete-user");

        return modelAndView;
    }

    @PostMapping("/delete/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deleteUserConfirm(@PathVariable String username, ModelAndView modelAndView) {
        this.userService.deleteUser(username);
        modelAndView.setViewName("redirect:/all");
        return modelAndView;

    }

    @GetMapping("/all")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("All Users")
    public ModelAndView allUsers(ModelAndView modelAndView) {
        List<UserViewModel> users = this.userService.findAllUsers()
                .stream()
                .map(u -> {
                    UserViewModel user = this.modelMapper.map(u, UserViewModel.class);
                    Set<String> authorities = u.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet());
                    user.setAuthorities(authorities);

                    return user;
                })
                .collect(Collectors.toList());

        modelAndView.addObject("users", users);
        modelAndView.setViewName("users");

        return modelAndView;
    }

    @PageTitle("User Cart")
    //@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/cart")
    public String userCart(Model model) {
        List<ProductViewModel> boughtProducts
                = this.userService.findByUsername(this.tools.getLoggedUser())
                .getBoughtProducts()
                .stream().map(pr -> this.modelMapper.map(pr, ProductViewModel.class))
                .collect(Collectors.toList());
        double totalPrice = getTotalPrice(boughtProducts);
        model.addAttribute("boughtProducts", boughtProducts);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    /*@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'WORKER', 'USER')")
    @GetMapping(path = GET_MAPPING_PRODUCT_ADD_TO_CART)
    public String addToCart(@PathVariable("productId") String productId) throws UserCannotSaveException {
        if (!"anonymousUser".equals(this.tools.getLoggedUser())) {
            this.userService.buyProduct(productId, this.tools.getLoggedUser());
            return REDIRECT_TO_USER_CART;
        } else {
            //niakakva greshka...
            return REDIRECT_TO_HOME;
        }
    }*/

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'WORKER', 'USER')")
    @GetMapping("/remove-all-from-cart")
    public String removeAllFromCart() {
        System.out.println();
        if (!"anonymousUser".equals(this.tools.getLoggedUser())) {
            this.userService.removeAllProductCart(this.tools.getLoggedUser());
            return "redirect:/cart";
        }
        return "redirect:/home";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/user/finish")
    public String finishCart () {
        this.orderService.createOrder();
        return "redirect:/home";
    }

    /*
    //http://localhost:8000/product/remove-from-cart/0b5e6d87-cc02-4fc7-aaa2-79e7630966f8
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'WORKER', 'USER')")
    @GetMapping(GET_MAPPING_PRODUCT_REMOVE_FROM_CART)
    public String removeFromCart(@PathVariable("productId") String productId) {
        if (!"anonymousUser".equals(this.tools.getLoggedUser())) {
            this.userService.removeOneProductCart(productId, this.tools.getLoggedUser());
        }
        return CART_VIEW;




        @ExceptionHandler({UserNotFoundException.class, UserIsNullOrCartIsNullException.class,
                UserWithThisNameIsNotLogged.class})
        public ModelAndView handleUserException (BaseException e){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("error");
            return modelAndView;
        }*/
    private double getTotalPrice(List<ProductViewModel> boughtProducts) {
        double totalPrice = 0.00;
        for (ProductViewModel boughtProduct : boughtProducts) {
            totalPrice += boughtProduct.getPrice();
        }
        return totalPrice;

    }

}
    /*



     */


//    @GetMapping("/all")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PageTitle("All users")
//    public ModelAndView showAllUsers(ModelAndView modelAndView){
//        List<UserServiceModel> users = this.userService.findAllUsers()
//                .stream()
//                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
//                .collect(Collectors.toList());
//
//        Map<String, Set<RoleServiceModel>> userAndAuthorities = new HashMap<>();
//        users.forEach(u -> userAndAuthorities.put(u.getId(), u.getAuthorities()));
//
//        modelAndView.addObject("users", users);
//        modelAndView.addObject("usersAndAuths", userAndAuthorities);
//        return super.view("user/all-users", modelAndView);
//    }
//
//    @GetMapping("/delete/{username}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PageTitle("Delete User")
//    public ModelAndView deleteUser(@PathVariable String username, ModelAndView modelAndView) {
//        UserServiceModel userServiceModel = this.userService.findUserByUserName(username);
//
//        modelAndView.addObject("user", userServiceModel);
//        modelAndView.addObject("userN", username);
//
//        return super.view("user/delete-user", modelAndView);
//    }
//
//    @PostMapping("/delete/{username}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ModelAndView deleteUserConfirm(@PathVariable String username) {
//        this.userService.deleteUser(username);
//
//        return super.redirect("/users/all");
//    }
//
//    @PostMapping("/set-admin/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ModelAndView setAdminRole(@PathVariable String id) {
//        this.userService.makeAdmin(id);
//
//        return super.redirect("/users/all");
//    }
//
//    @PostMapping("/set-user/{id}")
//    @PreAuthorize("hasRole('ROLE_ROOT')")
//    public ModelAndView setUserRole(@PathVariable String id) {
//        this.userService.makeUser(id);
//
//        return super.redirect("/users/all");
//    }


