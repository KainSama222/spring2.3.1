package web.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public String showAllUsers(Model model, @ModelAttribute("flashMessage") String flashAttribute) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }

    @GetMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable(value = "id", required = true) long id, Model model) {
        User user = userService.getUserById(id);
        if (null == user) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") @Valid @NotNull User user) {
        userService.addOrEditUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = true, defaultValue = "") long id,
                             RedirectAttributes attributes) {
        userService.deleteUser(id);
        return "redirect:/users";

    }

}
