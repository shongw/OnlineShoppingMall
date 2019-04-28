package com.aden.os.controller;

import com.aden.os.biz.UserBiz;
import com.aden.os.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/to_registered", method = RequestMethod.GET)
    public String toRegistered(){
        return "user_registered";
    }

    @RequestMapping(value = "/to_login", method = RequestMethod.GET)
    public String toLogin(){
        return "user_login";
    }

    @RequestMapping(value = "/center", method = RequestMethod.GET)
    public String center(HttpSession session){
        if (session.getAttribute("user") == null){
            return "redirect:to_login";
        }
        return "user_center";
    }

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public String registered(User user, HttpSession session){
        userBiz.add(user);
        session.setAttribute("user", user);
        return "redirect:/commodity/homepage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpSession session){
        User user = userBiz.verifyInfo(phone, password);
        session.setAttribute("user", user);
        return "redirect:/commodity/homepage";
    }


}
