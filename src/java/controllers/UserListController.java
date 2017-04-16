/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.HibernateDemo;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.Helper;

/**
 *
 * @author Ben
 */
@Controller
public class UserListController {

    private Helper helper;

    public void setHelper(Helper helper) {
        this.helper = helper;
    }

    @RequestMapping(value = "/user_list", method = RequestMethod.GET)
    public String listUsers(Model model, @ModelAttribute("editUser") HibernateDemo editUser) {

        if (helper == null) {
            helper = new Helper();
        }

        if (editUser != null) {
            model.addAttribute("user", editUser);
        } else {
            model.addAttribute("user", new HibernateDemo());
        }

        model.addAttribute("listUsers", this.helper.getAllUsers());
        return "index";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertUser(@ModelAttribute("hibernatedemo") HibernateDemo user) {

        this.helper.insert(user);

        return "redirect:/user_list.htm";

    }

    @RequestMapping("/remove/{userId}")
    public String removeUser(@PathVariable("userId") int userId) {

        this.helper.delete(userId);
        return "redirect:/user_list.htm";
    }
}
