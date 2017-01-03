package com.java.gavinandre.controller;

import com.java.gavinandre.pojo.User;
import com.java.gavinandre.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    //	@RequestMapping("/userInfo")
//	public String userInfo(HttpServletRequest request,Model model){
//		int userId = Integer.parseInt(request.getParameter("id"));
//		User user = this.userService.getUserById(userId);
//		model.addAttribute("user", user);
//		return "userInfo";
//	}
    /*@RequestMapping("/userInfo")
    public String userInfo(@RequestParam int id, Model model) {
//		int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "userInfo";
    }

    @RequestMapping(path = "/userInfoPath/{id}")
    public String userInfoPath(@PathVariable int id, Model model) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "userInfo";
    }*/

    @ResponseBody
    @RequestMapping(path = "/userInfoJson/{id}")
    public User userInfoJson(@PathVariable int id) {
        User user = this.userService.getUserById(id);
        return user;
    }

    @ResponseBody
    @RequestMapping(path = "/userInfoJsonForMap/{id}")
    public HashMap<String, Object> userInfoJsonForMap(@PathVariable int id) {
        HashMap<String, Object> objectObjectMap = new HashMap<String, Object>();
        objectObjectMap.put("111", 12312);
        objectObjectMap.put("222", 123121);
        objectObjectMap.put("333", 123122);
        objectObjectMap.put("444", 123123);
        User user = this.userService.getUserById(id);
        return objectObjectMap;
    }

}