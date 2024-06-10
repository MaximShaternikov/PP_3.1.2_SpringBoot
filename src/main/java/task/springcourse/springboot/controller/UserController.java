package task.springcourse.springboot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import task.springcourse.springboot.model.User;
import task.springcourse.springboot.service.UsersService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", usersService.findAll());
        return "pages/users";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "pages/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/new";
        }
        usersService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", usersService.findOne(id));
        return "pages/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") long id, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/edit";
        }
        usersService.update(id, user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        usersService.delete(id);
        return "redirect:/users";
    }
}
