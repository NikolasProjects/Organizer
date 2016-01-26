package net.organizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Nikolay on 14.01.2016.
 */
@Controller
public class AuthorisationController {
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginGET() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/loginfailed")
    public String loginFailedGET(Model model) {
        String loginFailed = "Неверный логин или пароль!";
        model.addAttribute("loginFailed", loginFailed);
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logoutGET() {
        return "login";
    }
}
