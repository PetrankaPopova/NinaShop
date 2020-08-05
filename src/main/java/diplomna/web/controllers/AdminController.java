package diplomna.web.controllers;

import diplomna.model.bindingmodel.SaveNewRolesBindingModel;
import diplomna.model.service.SaveNewRolesServiceModel;
import diplomna.model.service.UserServiceModel;
import diplomna.model.view.UserViewModel;
import diplomna.service.ProductService;
import diplomna.service.UserRoleService;
import diplomna.service.UserService;
import diplomna.web.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;
    private final ProductService productService;
    private final UserService userService;
    private final Tools tools;

    @Autowired
    public AdminController(ModelMapper modelMapper, UserRoleService userRoleService,
                           ProductService productService, UserService userService,
                           Tools tools) {
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
        this.productService = productService;
        this.userService = userService;
        this.tools = tools;
    }

    @GetMapping("/admin/users")
    public String allUsers(Model model) {
        List<UserViewModel> allUsers = this.userService.findAllUsers()
                .stream().map(u -> this.modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("allUsers", allUsers);
        return "users";
    }

    @GetMapping("/admin/users/edit/{userId}")
    public String editUserRole(@PathVariable("userId") String userId, Model model) {
        UserServiceModel user = this.userService.findById(userId);
        model.addAttribute("user", user);
        return "user-edit-roles";
    }

    @PostMapping("/admin/users/save")
    public String saveNewRolePost(@ModelAttribute("snrbm") SaveNewRolesBindingModel snrbm) {
        this.userRoleService.saveNewRoleToUser(this.modelMapper.map(snrbm, SaveNewRolesServiceModel.class));
        return "redirect:/admin/users";
    }
}
