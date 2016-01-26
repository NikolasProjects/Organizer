package net.organizer.controller;

import net.organizer.dao.TaskDao;
import net.organizer.dao.UserDao;
import net.organizer.dto.Task;
import net.organizer.dto.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikolay on 15.12.2015.
 */
@Controller
public class HomePageController extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(HomePageController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage(Model model, HttpServletRequest request) {
        LOGGER.info("Home Page");
        List<Task> tasks = taskDao.getAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("login", getLogin());
        model.addAttribute("role", getRole());
        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        return "addTask";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "targetDate") String targetDateStr,
            @RequestParam(value = "creationDate", required = false) String creationDateStr
    ) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Task task = new Task();
        task.setName(name);
        try {
            task.setTargetDate(simpleDateFormat.parse(targetDateStr));

            if (id != null) {
                task.setId(id);
                task.setCreationDate(simpleDateFormat.parse(creationDateStr));

                taskDao.update(task);
            } else {
                task.setCreationDate(new Date());
                taskDao.add(task);
            }

        } catch (ParseException e) {
            LOGGER.error("Error /add");
        }

        return "redirect:/home";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deletePost(@RequestParam(value = "id") Integer id) {
        try {
            taskDao.delete(id);
        } catch (Exception e) {
            return "error";
        }
        return "ok";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String updateTask(Model model, @PathVariable Integer id) {
        try {
            Task task = taskDao.getById(id);
            model.addAttribute("task", task);
        } catch (Exception e) {
            LOGGER.error("Error edit task");
        }
        return "addTask";
    }
}
