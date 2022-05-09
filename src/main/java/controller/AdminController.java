package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Boolean add(@RequestParam String name, @RequestParam String password, HttpSession session) {
        if (session.getAttribute("admin") != null) {
            var user = new User();
            user.setName(name);
            user.setPassword(password);
            return service.add(user);
        }
        return false;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("admin") != null) {
            return service.delete(id);
        }
        return false;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> list() {
        return service.getAll();
    }
}
