package com.java.gavinandre.controller;

import com.java.gavinandre.controller.validation.ValidGroup1;
import com.java.gavinandre.pojo.ItemsCustom;
import com.java.gavinandre.pojo.ItemsQueryVo;
import com.java.gavinandre.service.ItemsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * Created by gavinandre on 17-1-4.
 */
@Controller
//定义url的根路径，访问时根路径+方法名的url
@RequestMapping("/Items")
public class ItemsController {

    @Resource
    private ItemsService itemsService;

    //更具商品id查看商品信息rest接口
    //@requestMapping中指定restful方式的url中的参数，参数需要用{}包起来
    //@PathVariable将url中的参数和形参进行绑定
    @RequestMapping("/viewItems/{id}")
    public
    @ResponseBody
    ItemsCustom viewItems(@PathVariable("id") Integer id) throws Exception {
        //调用service查询商品的信息
        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        return itemsCustom;
    }

    //单独将商品类型的方法提取出来，将方法返回值填充到request域，在页面提示
    @ModelAttribute("itemsType")
    public Map<String, String> getItemsType() throws Exception {

        HashMap<String, String> itemsType = new HashMap<>();
        itemsType.put("001", "data type");
        itemsType.put("002", "clothes");
        return itemsType;
    }

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

    //批量修改商品查询
    @RequestMapping("/editItemsList")
    public ModelAndView editItemsList() throws Exception {
        //调用servie来查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);
        //指定逻辑视图名itemsList.jsp
        modelAndView.setViewName("editItemsList");

        return modelAndView;
    }

    //批量修改商品的提交

    @RequestMapping("/editItemsListSubmit")
    public String editItemsListSubmit(ItemsQueryVo itemsQueryVo) throws Exception {
        return "success";
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
    @RequestMapping("/editItemSubmit")
    public String editItemSubmit(Model model, Integer id,
                                 @Validated(value = {ValidGroup1.class}) @ModelAttribute(value = "itemsCustom") ItemsCustom itemsCustom,
                                 BindingResult bindingResult,
                                 //上传图片
                                 MultipartFile pictureFile
    ) throws Exception {

        //输出校验错误信息
        //如果参数绑定时出错
        if (bindingResult.hasErrors()) {
            //获取错误
            List<ObjectError> errors = bindingResult.getAllErrors();

            model.addAttribute("errors", errors);

            for (ObjectError error : errors) {
                //输出错误信息
                System.out.println(error.getDefaultMessage());
            }

            //如果校验错误，仍然回到商品修改页面
            return "editItem";

        }

        //进行数据回显
        model.addAttribute("id", id);
//        model.addAttribute("item",itemsCustom);

        //进行图片的上传
        if (pictureFile != null && pictureFile.getOriginalFilename() != null && pictureFile.getOriginalFilename().length() > 0) {
            //图片上传成功后，将图片的地址写到数据库
            String filePath = "/home/gavinandre/Documents/JavaWeb/JavaWebProject/ssm/src/main/resources/pictures/";//它的值要同你设置虚拟目录时涉及的目录路径一致，
            String originalFilename = pictureFile.getOriginalFilename();

            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

            //新文件
            File file = new File(filePath + newFileName);

            //将内存中的文件写入磁盘
            pictureFile.transferTo(file);

            //图片上传成功
            itemsCustom.setPic(newFileName);
        }


        itemsService.updateItems(id, itemsCustom);
        //请求转发
//        return "forward:queryItems.action";

//        return "editItem";
        //重定向
        return "redirect:queryItems.action";
    }

    //自定义属性编辑器
    //缺点:无法在多个controller共用
    /*@InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {

        //Date.class必须是与controller方法形参pojo属性一致的date类型，这里是java.util.Date
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"), true));
    }*/

    //删除商品
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] delete_id) throws Exception {
        //调用serive方法删除商品
        //这里我们就是简单的介绍完成将页面的信息绑定到数组中，所以service的方法你可以自己去完成
        System.out.println("delete_id" + Arrays.toString(delete_id));
        return "success";
    }

}
