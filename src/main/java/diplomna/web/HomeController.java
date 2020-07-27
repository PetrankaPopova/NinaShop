package diplomna.web;



import diplomna.service.ProductService;
import diplomna.view.ProductViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public String index(Model model){
        List<ProductViewModel> allProducts = getAllProducts();
        model.addAttribute("allProducts", allProducts);
        return "home";
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public String home(Model model){
        List<ProductViewModel> allProducts = getAllProducts();
        model.addAttribute("allProducts", allProducts);
        return "home";
    }

    private List<ProductViewModel> getAllProducts() {
        return this.productService
                .findAllProducts().stream()
                .map(pr->this.modelMapper.map(pr, ProductViewModel.class))
                .collect(Collectors.toList());
    }

}
