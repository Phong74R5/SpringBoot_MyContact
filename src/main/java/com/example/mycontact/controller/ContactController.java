package com.example.mycontact.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.mycontact.entities.Contact;
import com.example.mycontact.service.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/contact")
    public String list(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "list";
    }

    @GetMapping(value = "/contact/search")
    public String search(@RequestParam("name") String name, Model model) {
        if (StringUtils.isEmpty(name)) {
            return "redirect:/contact";
        }
        model.addAttribute("contacts", contactService.search(name));
        return "list";
    }

    @GetMapping(value = "/contact/add")
    public String add(Model model) {
        model.addAttribute("contact", new Contact());
        return "form";
    }

    @PostMapping(value = "/contact/save")
    public String save(@Valid Contact contact, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        contactService.save(contact);
        redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
        return "redirect:/contact";
    }

}
