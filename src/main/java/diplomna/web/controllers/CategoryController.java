package diplomna.web.controllers;

import diplomna.model.entity.CategoryName;
import diplomna.model.service.CategoryServiceModel;
import diplomna.service.CategoryService;
import diplomna.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CategoryController(CategoryService categoryService, UserService userService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView all(ModelAndView modelAndView){
        List<CategoryServiceModel> categories = this.categoryService.findAllCategories();

        modelAndView.addObject("categories",categories);

        modelAndView.setViewName("category");
        return modelAndView;
    }
}
