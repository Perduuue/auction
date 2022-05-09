package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String get() {
        return "login.html";
    }

    @ResponseBody
    @PostMapping()
    public Boolean login(@RequestParam String name, @RequestParam String password, HttpSession session) {
        var user = userService.check(name, password);
        if (user.isEmpty()) {
            return false;
        }
        session.setAttribute("user", user.get());
        session.setMaxInactiveInterval(15 * 60);
        return true;
    }
}
