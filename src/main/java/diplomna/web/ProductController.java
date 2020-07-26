package diplomna.web;

import diplomna.exception.AlreadyExistsException;
import diplomna.model.bindingmodel.ProductAddBindingModel;
import diplomna.model.service.ProductServiceModel;
import diplomna.service.CategoryService;
import diplomna.service.ProductService;
import diplomna.view.ProductViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public String addProductForm(Model model) {

        if (!model.containsAttribute("productAddBindModel")) {
            model.addAttribute("productAddBindModel", new ProductAddBindingModel());
            model.addAttribute("errors", false);
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("productAddBindModel") ProductAddBindingModel bindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes attributes) {

        if (!bindingResult.hasErrors()) {
            try {
                ProductServiceModel productServiceModel = mapper.map(bindingModel, ProductServiceModel.class);
                productServiceModel.setCategory(categoryService.getByName(bindingModel.getCategory()));
                productService.addProduct(productServiceModel);

            } catch (AlreadyExistsException ex) {
                // service error
                bindingResult.rejectValue(ex.getField(), "error.productAddBindModel", ex.getMessage());
            }
        }

        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("productAddBindModel", bindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindModel", bindingResult);
            return "redirect:/products/add";
        }
        return "redirect:/";
    }


    @RequestMapping("/buy/{id}")
    public String buy(Model model,
                      HttpSession session,
                      @PathVariable("id") String id) {

        //  if(!isAuthorizedUser(session)) {
        //     return "redirect:/";
        //   }

        productService.buyById(id);

        return "redirect:/";
    }


    @GetMapping("/buyAll")
    public String buyAll(Model model,
                         HttpSession session) {

        //   if(!isAuthorizedUser(session)) {
        //     return "redirect:/";
        // }

        productService.buyAll();
        return "redirect:/";
    }

    @GetMapping("")
    public String productsView(Model model){

        return "/product";
    }


}
