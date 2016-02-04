package net.organizer.controller;

import net.organizer.dao.TaskDao;
import net.organizer.dao.UserDao;
import net.organizer.dto.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

/**
 * Created by Nikolay on 14.01.2016.
 */
@Controller
public class BaseController {

    @Autowired
    protected TaskDao taskDao;
    @Autowired
    protected UserDao userDao;

    protected static final String DATE_FORMAT = "dd/MM/yyyy";

    protected String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    protected void addAuthenticatedUserToModel(Model model) {
        AuthUser authUser = userDao.getAuthUser(getLogin());
        model.addAttribute("authUser", authUser);
    }



}
