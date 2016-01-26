package net.organizer.controller;

import net.organizer.dto.Role;
import net.organizer.dto.User;
import net.organizer.utils.PasswordEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nikolay on 19.01.2016.
 */
@Controller
public class AdministratorController extends BaseController{

    @RequestMapping(value = "/add/user", method = RequestMethod.GET)
    public String addUserGet(Model model) {
        if (!getRole().equals(Role.ROLE_ADMIN)) {
            return "redirect:/home";
        }
        List<Role> roles = userDao.getRoles();
        model.addAttribute("login", getLogin());
        model.addAttribute("role", getRole());
        model.addAttribute("roles", roles);
        return "addUser";
    }

    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user) {
        if (!getRole().equals(Role.ROLE_ADMIN)) {
            return "redirect:/home";
        }
        String encryptedPassword = PasswordEncryptor.encryptPasswordMD5(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.addUser(user);
        return "redirect:/home";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        if (!getRole().equals(Role.ROLE_ADMIN)) {
            return "redirect:/home";
        }
        List<User> users = userDao.getUsers();
        model.addAttribute("login", getLogin());
        model.addAttribute("users", users);
        model.addAttribute("role", getRole());
        return "admin";
    }

    @RequestMapping(value = "/edit/user", method = RequestMethod.GET)
    public String editUser(Model model, @RequestParam(value = "id") Integer id) {
        if (!getRole().equals(Role.ROLE_ADMIN)) {
            return "redirect:/home";
        }
        User user = userDao.getUserById(id);
        List<Role> roles = userDao.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        model.addAttribute("userRole", getRole());
        return "addUser";
    }

    @RequestMapping(value = "/delete/user", method = RequestMethod.POST)
    @ResponseBody
    public String deletePost(@RequestParam(value = "id") Integer id) {
        try {
            userDao.delete(id);
        } catch (Exception e) {
            return "error";
        }
        return "ok";
    }
}
