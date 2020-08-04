package diplomna.web.controllers;

import diplomna.model.service.UserRoleServiceModel;
import diplomna.service.ProductService;
import diplomna.service.UserRoleService;
import diplomna.web.anotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;

import javax.xml.namespace.QName;

@Controller
public class AdminController {
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;
    private final ProductService productService;

    @Autowired
    public AdminController(ModelMapper modelMapper, UserRoleService userRoleService, ProductService productService) {
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
        this.productService = productService;
    }

}
