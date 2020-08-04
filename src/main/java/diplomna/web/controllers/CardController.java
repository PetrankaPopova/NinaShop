package diplomna.web.controllers;

import diplomna.model.bindingmodel.CardBindingModel;
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
public class CardController {

    @GetMapping("/card")
    public String showBag(Model model) {
        model.addAttribute("title", "User Bag");
        model.addAttribute("userClickShowBag", true);
        model.addAttribute("bagLines", null);
        return "card";
    }

    @PostMapping("/bag")
    public String addToBag(@Valid @ModelAttribute("bagBindingModel") CardBindingModel cardBindingModel,
                           BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return "redirect:/card";
        }

        return "all";
    }

  //  @GetMapping("/details")
   // @PreAuthorize("isAuthenticated()")
   // @PageTitle("Cart Details")
    //public ModelAndView cartDetails(ModelAndView modelAndView,
             //                       HttpSession session) {
       // List<CartItemViewModel> cart = this.retrieveCart(session);
     //   modelAndView.addObject("totalPrice", this.calcTotal(cart));
     //   modelAndView.setViewName("bag-details");

      //  return modelAndView;
    }


  //  @SuppressWarnings(value = "unchecked")
    //private List<CartItemViewModel> retrieveCart(HttpSession session) {
      //  this.initCart(session);
      //  private Object calcTotal(List<CartItemViewModel> cart) {
          //  BigDecimal result = new BigDecimal(0);
          //  for (CartItemViewModel item : cart) {
                //  BigDecimal price = getIndividualPriceWithDiscount(item);
                // result = result.add(price.multiply(new BigDecimal(item.getQuantity())));
           // }
        //    return result;
      //  }

        //  private BigDecimal getIndividualPriceWithDiscount(CartItemViewModel item) {
        //   double price = item.getProductViewModel().getPrice();
        // double discount = (100 - item.getProductViewModel().getDiscount()) / 100;

        //  price = price.multiply(BigDecimal.valueOf(discount));
        //return price;
        // }
        //  }

        //  private List<CartItemViewModel> retrieveCart(HttpSession session) {


        // private BigDecimal getIndividualPriceWithDiscount(CartItemViewModel item) {
        // BigDecimal price = item.getBicycle().getPrice();
        // double discount = (100 - item.getBicycle().getDiscount()) / 100;

        //  price = price.multiply(BigDecimal.valueOf(discount));
        // return price;
        // }
 //   }
      //  private void removeItemFromCart (String id, List < CartItemViewModel > cart){
       //     cart.removeIf(ci -> ci.getProductViewModel().getId().equals(id));
     //   }

        //  private OrderServiceModel prepareOrder(List<CartItemViewModel> cart, String username) {
        //  OrderServiceModel orderServiceModel = new OrderServiceModel();
        //  orderServiceModel.setUser(userService.findUserByUsername(username));

        //   List<OrderItemServiceModel> bicycles = new ArrayList<>();
        //  for (CartItemViewModel item : cart) {
        //    OrderItemServiceModel orderItemServiceModel = mapper.map(item, OrderItemServiceModel.class);
        //   orderItemServiceModel.setPrice(this.getIndividualPriceWithDiscount(item));

        // bicycles.add(orderItemServiceModel);
        //}

        //  orderServiceModel.setBicycles(bicycles);
        // orderServiceModel.setTotalPrice(this.calcTotal(cart));

        //  return orderServiceModel;
        //  }


    //}

  //  private void initCart(HttpSession session) {
    //    if (session.getAttribute("shopping-cart") == null) {
         //   session.setAttribute("shopping-cart", new ArrayList<>());
     //   }
 //   }
//}
