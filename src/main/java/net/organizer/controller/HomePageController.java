package net.organizer.controller;

import net.organizer.dto.AuthUser;
import net.organizer.dto.Task;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikolay on 15.12.2015.
 */
@Controller
public class HomePageController extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(HomePageController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage(Model model) {
        AuthUser authUser = userDao.getAuthUser(getLogin());
        List<Task> tasks = taskDao.getByAuthor(authUser.getId());
        model.addAttribute("tasks", tasks);
        model.addAttribute("authUser", authUser);
        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        addAuthenticatedUserToModel(model);
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
                AuthUser authUser = userDao.getAuthUser(getLogin());
                task.setAuthorId(authUser.getId());
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
            addAuthenticatedUserToModel(model);
        } catch (Exception e) {
            LOGGER.error("Error edit task");
        }
        return "addTask";
    }
}
