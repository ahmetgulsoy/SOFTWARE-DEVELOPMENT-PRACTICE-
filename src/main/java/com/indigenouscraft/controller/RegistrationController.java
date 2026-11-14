
package com.indigenouscraft.controller;

import com.indigenouscraft.dto.RegistrationRequest;
import com.indigenouscraft.entity.User;
import com.indigenouscraft.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("registrationRequest")
            RegistrationRequest request,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        try {
            userService.registerUser(user);
        } catch (IllegalArgumentException exception) {
            bindingResult.rejectValue(
                    "email",
                    "email.duplicate",
                    exception.getMessage()
            );
            return "register";
        }

        return "redirect:/register?success";
    }
}
