package com.Shoppingcart.cartapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Shoppingcart.cartapp.model.Order;
import com.Shoppingcart.cartapp.model.Product;
import com.Shoppingcart.cartapp.service.ShoppingCartService;

@Controller
public class CartController {

    @Autowired
    private ShoppingCartService cartService;

    @GetMapping("/")
    public String viewproduct(Model model) {
        List<Product> products = cartService.getAllList();
        model.addAttribute("products", products);
        return "home";
        // This should return the name of the view template (e.g., products.html)
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable int id) {
        for (Product products : cartService.getAllList()) {
            if (products.getId() == id) {
                cartService.addToCart(products);
                break;
            }
        }
        return "redirect:/"; // Redirect to home page
    }

    @GetMapping("/saved")
    public String viewSavedItems(Model model) {
        model.addAttribute("savedItems", cartService.getSavedItems());
        return "saved";  // returns saved.html page
    }

    @GetMapping("/save-for-later/{id}")
    public String saveForLater(@PathVariable int id) {
        cartService.saveForLater(id);
        return "redirect:/cart";
    }

    @GetMapping("/move-to-cart/{id}")
    public String moveToCart(@PathVariable int id) {
        cartService.moveToCart(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.getPrice());
        model.addAttribute("savedItems", cartService.getSavedItems());
        model.addAttribute("totalPrice", cartService.getPrice());
        return "cart";
    }

    @GetMapping("/remove-from-cart/{id}")
    public String removeFromCart(@PathVariable int id) {
        cartService.RemoveFromCart(id);
        return "redirect:/cart"; // Redirect to the cart page
    }

    @GetMapping("/remove-from-saved/{id}")
    public String removeFromSaved(@PathVariable int id) {
        cartService.removeFromSaved(id);
        return "redirect:/saved";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, RedirectAttributes redirectAttributes) {
        if (cartService.getCartItems().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty. Please add items before checkout.");
            return "redirect:/cart";
        }

        model.addAttribute("total", cartService.getPrice());
        return "checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(@RequestParam String name,
            @RequestParam String address,
            @RequestParam String phone,
            Model model) {

        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        order.setPhone(phone);

        model.addAttribute("order", order);
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.getPrice());

        // Clear cart after placing order
        cartService.getCartItems().clear();

        return "confirmation";
    }

}
