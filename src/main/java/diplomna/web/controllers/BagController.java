package diplomna.web.controllers;

import diplomna.model.bindingmodel.BagBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
//@RequestMapping("/bag")
public class BagController {

    @GetMapping("/bag")
    public String showBag(Model model){
        model.addAttribute("title", "User Bag");
        model.addAttribute("userClickShowBag", true);
        model.addAttribute("bagLines", null);
        return "bag";
    }

    @PostMapping("/bag")
   public String addToBag(@Valid @ModelAttribute("bagBindingModel")BagBindingModel bagBindingModel,
    BindingResult bindingResult, RedirectAttributes attributes){

        if (bindingResult.hasErrors()){
            return "redirect:/bag";
        }

        return "bag";
    }



}
