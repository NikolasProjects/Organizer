package net.organizer.controller;

import net.organizer.dto.Role;
import net.organizer.dto.User;
import net.organizer.utils.PasswordEncryptor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Nikolay on 19.01.2016.
 */
@Controller
public class AdministratorController extends BaseController{

    private static final Logger LOGGER = Logger.getLogger(AdministratorController.class);

    @RequestMapping(value = "/add/user", method = RequestMethod.GET)
    public String addUserGet(Model model) {
        List<Role> roles = userDao.getRoles();
        model.addAttribute("roles", roles);
        addAuthenticatedUserToModel(model);
        return "addUser";
    }

    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            LOGGER.error(bindingResult.getGlobalError());
        }
        if (user.getId() != null) {
            userDao.update(user);
        } else {
            String encryptedPassword = PasswordEncryptor.encryptPasswordMD5(user.getPassword());
            user.setPassword(encryptedPassword);
            try {
                userDao.addUser(user);
            } catch (IOException e) {
                LOGGER.error("Error saving user");
            }
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<User> users = userDao.getUsers();
        model.addAttribute("users", users);
        addAuthenticatedUserToModel(model);
        return "admin";
    }

    @RequestMapping(value = "/edit/user", method = RequestMethod.GET)
    public String editUser(Model model, @RequestParam(value = "id") Integer id) {
        User user = userDao.getUserById(id);
        List<Role> roles = userDao.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        addAuthenticatedUserToModel(model);
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
