package com.java.gavinandre.controller;

import com.java.gavinandre.pojo.ItemsCustom;
import com.java.gavinandre.service.ItemsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gavinandre on 17-1-4.
 */
@Controller
//定义url的根路径，访问时根路径+方法名的url
@RequestMapping("/Items")
public class ItemsController {

    @Resource
    private ItemsService itemsService;

    @RequestMapping("/queryItems")
    public ModelAndView queryItems() throws Exception {
        //调用servie来查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);
        //指定逻辑视图名itemsList
        modelAndView.setViewName("itemsList");

        return modelAndView;
    }

    //商品修改页面提示
    //使用method = RequestMethod.GET来限制使用get方法
    /*@RequestMapping(value = "/editItems",method = RequestMethod.GET)
    public ModelAndView editItems() throws Exception
    {
        ModelAndView modelAndView=new ModelAndView();

        //调用service查询商品的信息
        ItemsCustom itemsCustom=itemsService.findItemsById(1);
        //将模型数据传到jsp
        modelAndView.addObject("itemsCustom",itemsCustom);
        //指定逻辑视图名
        modelAndView.setViewName("editItem");

        return modelAndView;
    }*/

    //方法返回字符串，字符串就是逻辑视图名，Model作用是将数据填充到request域，在页面显示
    @RequestMapping(value = "/editItems", method = RequestMethod.GET)
    public String editItems(Model model, Integer id) throws Exception {

        //将id传到页面
        model.addAttribute("id", id);

        //调用service查询商品的信息
        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        model.addAttribute("itemsCustom", itemsCustom);

        return "editItem";
    }

    //RequestParam参数
    //value:指定参数名为id:  editItems.action?id=1
    //required:设置为true时不传参数则报错
    //defaultValue:不传入参数时的默认值
    /*@RequestMapping(value = "/editItems", method = RequestMethod.GET)
    public void editItems(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "id", required = false, defaultValue = "3")
                                  Integer id) throws Exception {

        //调用service查询商品的信息
        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        request.setAttribute("itemsCustom", itemsCustom);

        //注意如果使用request转向页面，这里需要指定页面的完整路径
        request.getRequestDispatcher("/WEB-INF/jsp/editItem.jsp").forward(request, response);

        //通过这种返回值为void的方法我们容易输出json、xml格式的数据
        // 即通过response指定响应结果，例如响应json
        *//*response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("id=" + id);*//*

    }*/

    //商品提交页面
    /*@RequestMapping("/editItemSubmit")
    public String editItemSubmit(Integer id, ItemsCustom itemsCustom) throws Exception {

        itemsService.updateItems(id, itemsCustom);

        //请求转发,使用forward进行请求转发，request数据可以共享，url地址栏不会
//        return "forward:queryItems.action";

        //使用redirect进行重定向，request数据无法共享，url地址栏会发生变化的。由于我们重定向的页面queryItems.action与本页面editItemSubmit.action在同一根目录下，所以不需要加入根路径
        return "redirect:queryItems.action";
    }*/

    //商品提交页面
    //itemsQueryVo是包装类型的pojo
    @RequestMapping("/editItemSubmit")
    public String editItemSubmit(Integer id, ItemsCustom itemsCustom) throws Exception {

        itemsService.updateItems(id, itemsCustom);

        //请求转发,使用forward进行请求转发，request数据可以共享，url地址栏不会
//        return "forward:queryItems.action";

        //使用redirect进行重定向，request数据无法共享，url地址栏会发生变化的。由于我们重定向的页面queryItems.action与本页面editItemSubmit.action在同一根目录下，所以不需要加入根路径
        return "redirect:queryItems.action";
    }

    //自定义属性编辑器
    //缺点:无法在多个controller共用
    /*@InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {

        //Date.class必须是与controller方法形参pojo属性一致的date类型，这里是java.util.Date
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"), true));
    }*/

}
