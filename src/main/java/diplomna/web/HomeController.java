package diplomna.web;



import diplomna.service.ProductService;
import diplomna.view.ProductViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String index(Model model){
        List<ProductViewModel> allProducts = getAllProducts();
        model.addAttribute("allProducts", allProducts);
        return "home";
    }

    @GetMapping("/home")
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
