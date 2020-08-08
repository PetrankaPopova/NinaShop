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
    @PreAuthorize("isAnonymous()")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Register")
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
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model) {
        UserServiceModel user = this.userService.findByUsername(this.tools.getLoggedUser());
        UserView userView = this.modelMapper.map(user, UserView.class);
        model.addAttribute("user", user);
        return "profile";
    }
    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Edit User")
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
    @PreAuthorize("isAuthenticated()")
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


    @GetMapping("/user/buy/{productId}")
    public String buyProduct(@PathVariable("productId") String productId) {
        this.userService.buyProduct(productId);
        return "redirect:/home";
    }







    @PageTitle("User Cart")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
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


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/remove-all-from-cart")
    public String removeAllFromCart() {
        if (!"anonymousUser".equals(this.tools.getLoggedUser())) {
            this.userService.removeAllProductCart(this.tools.getLoggedUser());
            return "redirect:/cart";
        }
        return "redirect:/home";
    }

    ///remove-from-cart/73e92efe-971c-45f3-82a4-b5973b6125d2
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/remove-from-cart/{productId}")
    public String removeOneFromCart(@PathVariable("productId") String productId) {
        if (!"anonymousUser".equals(this.tools.getLoggedUser())) {
            this.userService.removeOneProductCart(productId, this.tools.getLoggedUser());
        }
            return "redirect:/cart";
        }



    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/user/finish")
    public String finishCart () {
        this.orderService.createOrder();
        return "redirect:/home";
    }


    private double getTotalPrice(List<ProductViewModel> boughtProducts) {
        double totalPrice = 0.00;
        for (ProductViewModel boughtProduct : boughtProducts) {
            totalPrice += boughtProduct.getPrice();
        }
        return totalPrice;

    }
}
