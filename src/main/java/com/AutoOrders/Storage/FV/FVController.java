package com.AutoOrders.Storage.FV;

import com.AutoOrders.Storage.FvDetails.FvDetails;
import com.AutoOrders.Storage.FvDetails.FvDetailsService;
import com.AutoOrders.Storage.orders.Orders;
import com.AutoOrders.Storage.storage.PosNotFoundExeption;
import com.AutoOrders.Storage.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FVController {

    @Autowired
    private FVService service;

    @Autowired
    private FvDetailsService fvDetailsService;


    @GetMapping("/FV")
        public String showFVList(Model model) {
        List<FV> listFV = (List<FV>) service.listAll();
        model.addAttribute("listFV", listFV);
            return "FV";
        }

    @GetMapping("/FV/new")
    public String showNewForm(Model model){
        List<FvDetails> listFvDetails = (List<FvDetails>) fvDetailsService.listAll();
        model.addAttribute("FV", new FV());
        model.addAttribute("listFvDetails", listFvDetails);
        model.addAttribute("pageTitle", "Dodaj nową fakturę");
        return "FV_form";
    }

    @PostMapping("/FV/save")
    public String saveFV(FV fv, RedirectAttributes att){
        service.save(fv);
        att.addFlashAttribute("message", "Faktura została dodana");
        return "redirect:/FV";
    }

    @GetMapping("/FV/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes att){
        try {
            FV fv = service.get(id);
            model.addAttribute("FV", fv);
            List<FvDetails> listFvDetails = (List<FvDetails>) fvDetailsService.listAll();
            model.addAttribute("listFvDetails", listFvDetails);
            model.addAttribute("pageTitle", "Edytuj fakturę");
            return "FV_form";
        }
        catch (PosNotFoundExeption e) {
            att.addFlashAttribute("message", e.getMessage());
            return "redirect:/FV";
        }
    }

}
