package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AdminService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/adminLogin")
public class AdminLoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String get() {
        return "adminLogin.html";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> list() {
        return userService.getAll();
    }

    @ResponseBody
    @PostMapping()
    public Boolean login(@RequestParam String name, @RequestParam String password, HttpSession session) {
        var admin = adminService.check(name, password);
        if (admin.isEmpty()) {
            return false;
        }
        session.setAttribute("admin", admin.get());
        session.setMaxInactiveInterval(15 * 60);
        return true;
    }
}
