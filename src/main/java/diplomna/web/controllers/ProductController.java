package diplomna.web.controllers;

import diplomna.model.bindingmodel.ProductAddBindingModel;
import diplomna.model.service.ProductServiceModel;
import diplomna.service.CategoryService;
import diplomna.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper mapper;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, ModelMapper mapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }


    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String addProductForm(Model model) {

        if (!model.containsAttribute("productAddBindModel")) {
            model.addAttribute("productAddBindModel", new ProductAddBindingModel());
            model.addAttribute("errors", false);
        }
        return "product-add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String addProduct(@Valid @ModelAttribute("productAddBindModel") ProductAddBindingModel bindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes attributes) throws IOException {

        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("productAddBindModel", bindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindModel", bindingResult);
            return "redirect:/products/add";
        }

        ProductServiceModel productServiceModel = mapper.map(bindingModel, ProductServiceModel.class);
        productServiceModel.setCategory(categoryService.getByName(bindingModel.getCategory()));
        productService.addProduct(productServiceModel);
        return "redirect:/home";
    }


    @RequestMapping("/buy/{id}")
    @PreAuthorize("isAuthenticated()")
    public String buy(Model model,
                      HttpSession session,
                      @PathVariable("id") String id) {

        productService.buyById(id);

        return "redirect:/";
    }


    @GetMapping("/buyAll")
    @PreAuthorize("isAuthenticated()")
    public String buyAll(Model model, HttpSession session) {

        productService.buyAll();
        return "redirect:/";
    }

    @GetMapping("")
    public String productsView(Model model) {

        return "/product";
    }


}
