package net.organizer.controller;

import net.organizer.dao.TaskDao;
import net.organizer.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

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

    protected static final String DATE_FORMAT = "DD/MM/yyyy";

    protected String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    protected String getRole() {
        String authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        String role = authority.substring(1, authority.length() - 1);
        return role;
    }

}
