package com.AutoOrders.Storage.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@Component
public class StorageController {
    @Autowired private StorageService service;

    @GetMapping("/storage")
    public String showStorageList(Model model, @Param("keyword") String keyword) {
        List<Storage> listStorage = service.listAll(keyword);
        model.addAttribute("listStorage", listStorage);
        model.addAttribute("keyword", keyword);

        return "storage";
    }


    @GetMapping("/storage/new")
    public String showNewForm(Model model){
        model.addAttribute("storage", new Storage());
        model.addAttribute("pageTitle", "Dodaj nową pozycję");
        return "storage_form";
    }

    @PostMapping("/storage/save")
    public String seveStorage(Storage storage, RedirectAttributes att){
        service.save(storage);
        att.addFlashAttribute("message", "Pozycja została dodana do magazynu");
        return "redirect:/storage";
    }

    @GetMapping("/storage/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model,  RedirectAttributes att){
        try {
            Storage storage = service.get(id);
            model.addAttribute("storage", storage);
            model.addAttribute("pageTitle", "Edytuj pozycję");
            att.addFlashAttribute("message", "Pozycja została edytowana");
            return "storage_form";
        }
        catch (PosNotFoundExeption e) {
            att.addFlashAttribute("message", e.getMessage());
            return "redirect:/storage";
        }
    }

    @GetMapping("/storage/delete/{id}")
    public String deleteStorage(@PathVariable("id") Integer id, RedirectAttributes att) {
        try {
            service.delete(id);
            att.addFlashAttribute("message", "Towar został usunięty");
        } catch (PosNotFoundExeption e) {
            att.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/storage";
    }
}
