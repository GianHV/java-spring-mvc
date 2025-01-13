package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String text = this.userService.handleHello();
        model.addAttribute("vien", text);
        model.addAttribute("title", "mai phuong");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User request) {
        System.out.println(request);

        return "hello";
    }

}