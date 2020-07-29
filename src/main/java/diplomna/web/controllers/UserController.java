package diplomna.web.controllers;


import diplomna.model.bindingmodel.UserEditBindingModel;
import diplomna.model.bindingmodel.UserLoginBindingModel;
import diplomna.model.bindingmodel.UserRegisterBindingModel;
import diplomna.model.service.UserServiceModel;
import diplomna.service.UserService;
import diplomna.view.UserView;
import diplomna.web.Tools;
import diplomna.web.anotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller

//@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Tools tools;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, Tools tools, ModelMapper modelMapper) {
        this.userService = userService;
        this.tools = tools;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute(name = "userLoginBindingModel")
                                      UserLoginBindingModel userLoginBindingModel,
                              BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.addObject("userLoginBindingModel", userLoginBindingModel);
        modelAndView.setViewName("login");
        return modelAndView;

    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute ("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username");
        modelAndView.addObject("password");
        modelAndView.setViewName("login");

        return modelAndView;

    }

    @GetMapping("/register")
    //@PreAuthorize("isAnonymous()")
    @PageTitle("Register")
    public ModelAndView register(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                 ModelAndView modelAndView) {
       // modelAndView.addObject("user", new User());
        modelAndView.addObject("userRegisterBindingModel",new UserRegisterBindingModel());

        modelAndView.setViewName("/register");

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                        ModelAndView modelAndView, BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            modelAndView.setViewName("register");

        } else {
            UserServiceModel userServiceModel = this.userService
                    .registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

           // modelAndView.addObject("username", userRegisterBindingModel.getUsername());
           // modelAndView.addObject("email", userRegisterBindingModel.getEmail());
            //modelAndView.addObject("password", userRegisterBindingModel.getPassword());
           // modelAndView.addObject("confirmPassword", userRegisterBindingModel.getConfirmPassword());
           // modelAndView.addObject("userAddress", userRegisterBindingModel.getUserAddress());
           // modelAndView.addObject("userPhone", userRegisterBindingModel.getUserPhone());

            modelAndView.setViewName("login");
        }
        return modelAndView;
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
        return "/home";
    }


    @GetMapping("/edit")
   // @PreAuthorize("isAuthenticated()")
    //  @PageTitle("Edit User")
    public String editProfile(@Valid @ModelAttribute(name ="userEditBindingModel") UserEditBindingModel userEditBindingModel,
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
            if (!userEditBindingModel.getPassword().equals (userEditBindingModel.getConfirmPassword())){
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
}
    /*

    @PageTitle(name = "User register")
    @GetMapping(GET_MAPPING_USER_REGISTER)
    public String register(Model model) {
        if (!model.containsAttribute("urbm")) {
            model.addAttribute("urbm", new UserRegisterBindingModel());
        }
        //List<ProductViewModel> allProducts = this.listShop.getAllProducts();
        List<CategoryViewModel> allCategories = this.listShop.getAllCategories();
        model.addAttribute("allCategories", allCategories);
        return REGISTER_VIEW;
    }

    @PostMapping(POST_MAPPING_USER_REGISTER)
    public String registerConfirm(@Valid @ModelAttribute("urbm") UserRegisterBindingModel urbm,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            //urbm.setPassword("");
            //urbm.setConfirmPassword("");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.urbm", bindingResult);
            redirectAttributes.addFlashAttribute("urbm", urbm);
            return REDIRECT_TO_REGISTER;
        }
        if (!urbm.getPassword().equals(urbm.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passwordsNotMatch", true);
            redirectAttributes.addFlashAttribute("urbm", urbm);
        }

        this.userService.register(this.modelMapper.map(urbm, UserServiceModel.class));
        return REDIRECT_TO_LOGIN;
    }

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


