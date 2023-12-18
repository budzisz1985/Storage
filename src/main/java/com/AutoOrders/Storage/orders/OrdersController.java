package com.AutoOrders.Storage.orders;

import com.AutoOrders.Storage.storage.PosNotFoundExeption;
import com.AutoOrders.Storage.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.jar.Attributes;

@Controller
public class OrdersController {
    @Autowired private OrdersService service;

    @GetMapping("/orders")
    public String showOrdersList(Model model, @Param("keyword") String keyword) {
        List<Orders> listOrders = service.listAll(keyword);
        model.addAttribute("listOrders", listOrders);
        model.addAttribute("keyword", keyword);

        return "orders";
    }

    @GetMapping("/orders/print")
    public String showOrdersPrintList(Model model, @Param("keyword") String keyword) {
        List<Orders> listOrders = service.listAll(keyword);
        model.addAttribute("listOrders", listOrders);
        model.addAttribute("keyword", keyword);

        return "orders_print";
    }

    @GetMapping("/orders/new")
    public String showNewForm(Model model){
        model.addAttribute("orders", new Orders());
        model.addAttribute("pageTitle", "Dodaj nową pozycję do zamówienia");
        return "orders_form";
    }

    @PostMapping("/orders/save")
    public String saveOrders(Orders orders, RedirectAttributes att){
        service.save(orders);
        att.addFlashAttribute("message", "Pozycja została dodana do zamówienia");
        return "redirect:/orders";
    }

    @GetMapping("/orders/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes att){
        try {
            Orders orders = service.get(id);
            model.addAttribute("orders", orders);
            model.addAttribute("pageTitle", "Edytuj pozycję");
            return "orders_form";
        }
        catch (PosNotFoundExeption e) {
            att.addFlashAttribute("message", e.getMessage());
            return "redirect:/orders";
        }
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id, RedirectAttributes att) {
        try {
            service.delete(id);
            att.addFlashAttribute("message", "Pozycja została usunięta");
        } catch (PosNotFoundExeption e) {
            att.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/orders";
    }

}
