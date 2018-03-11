package com.autobid.controller;

import com.autobid.model.User;
import com.autobid.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hello")
public class HelloMvcController {

    @Resource
    private IUserService userService;

    /**
     * 使用JSON作为响应内容
     */

   // @CrossOrigin(origins="*",maxAge=3600)
    //@RequestMapping(value="/getUser/{userID}",method= RequestMethod.GET)
    @CrossOrigin(origins="*",maxAge=3600)
    @RequestMapping(value="/getUser/{userID}", produces = "text/json; charset=utf-8")
    @ResponseBody
    public User getUser(@PathVariable int userID) {
/*        User u = new User();
        u.setName("Richard");
        u.setAge(38);
        u.setId(userID);
        return u;*/;
        User user = this.userService.getUserById(userID);
        return user;
    }
}