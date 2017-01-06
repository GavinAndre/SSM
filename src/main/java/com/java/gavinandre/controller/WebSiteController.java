package com.java.gavinandre.controller;

import com.java.gavinandre.pojo.WebSite;
import com.java.gavinandre.service.IWebSiteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/webSite")
public class WebSiteController {
    @Resource
    private IWebSiteService webSiteService;

    //	@RequestMapping("/userInfo")
//	public String userInfo(HttpServletRequest request,Model model){
//		int userId = Integer.parseInt(request.getParameter("id"));
//		WebSite webSite = this.userService.getUserById(userId);
//		model.addAttribute("user", user);
//		return "userInfo";
//	}
    @RequestMapping("/webSiteInfo")
    public String webSiteInfo(@RequestParam int id, Model model) {
//		int userId = Integer.parseInt(request.getParameter("id"));
        WebSite webSite = this.webSiteService.getUserById(id);
        model.addAttribute("webSite", webSite);
        return "webSiteInfo";
    }

    @RequestMapping(path = "/webSiteInfoPath/{id}")
    public String webSiteInfoPath(@PathVariable int id, Model model) {
        WebSite webSite = this.webSiteService.getUserById(id);
        model.addAttribute("webSite", webSite);
        return "webSiteInfo";
    }

    @ResponseBody
    @RequestMapping(path = "/webSiteInfoJson/{id}")
    public WebSite webSiteInfoJson(@PathVariable int id) {
        return this.webSiteService.getUserById(id);
    }

    @ResponseBody
    @RequestMapping("/webSiteInfoRequestJson1")
    public HashMap<String, Object> webSiteInfoRequestJson1(@RequestBody WebSite webSite) {
        HashMap<String, Object> objectObjectMap = new HashMap<>();
        objectObjectMap.put("111", 12312);
        objectObjectMap.put("222", 123121);
        objectObjectMap.put("333", 123122);
        objectObjectMap.put("444", 123123);
        return objectObjectMap;
    }

    @ResponseBody
    @RequestMapping("/webSiteInfoRequestJson2")
    public String webSiteInfoRequestJson2(ServletRequest servletRequest) {

//        WebSite webSite = this.webSiteService.getUserById(id);
        return servletRequest.getParameter("id");
    }
}