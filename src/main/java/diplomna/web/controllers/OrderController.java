package diplomna.web.controllers;

import diplomna.model.bindingmodel.OrderBindingModel;
import diplomna.model.bindingmodel.UserRegisterBindingModel;
import diplomna.service.OrderService;
import diplomna.web.anotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Show Order")
    public String order(@Valid @ModelAttribute("orderBindingModel") OrderBindingModel orderBindingModel,
                        Model model, BindingResult bindingResult){
        if (!model.containsAttribute("orderBindingModel")) {
            model.addAttribute("orderBindingModel", new OrderBindingModel());
        }
        return "order";
    }

    }



