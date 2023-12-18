package com.AutoOrders.Storage.FvDetails;

import com.AutoOrders.Storage.FV.FV;
import com.AutoOrders.Storage.FV.FVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

    @Controller
    public class FvDetailsController {

        @Autowired
        private FvDetailsService fvDetailsService;

        @Autowired
        private FVService fvService;

        @GetMapping("/FvDetails")
        public String showFvDetalisList(Model model) {
            Iterable<FvDetails> listFvDetails = fvDetailsService.listAll();
            model.addAttribute("listFvDetails", listFvDetails);
            return "FV";
        }

        @GetMapping("/FvDetails/new")
        public String showNewForm(Model model){
            List<FV> listFV = (List<FV>) fvService.listAll();
            model.addAttribute("FvDetails", new FvDetails());
            model.addAttribute("listFV", listFV);
            model.addAttribute("pageTitle", "Dodaj nową pozycje do faktury");
            return "FvDetails_form";
        }

        @PostMapping("/FvDetails/save")
        public String saveFvDetails(FvDetails fvDetails, RedirectAttributes att){
            fvDetailsService.save(fvDetails);
            att.addFlashAttribute("message", "Pozycja została dodana do faktury");
            return "FV";
        }

    }

