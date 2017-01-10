package com.java.gavinandre.controller;

import com.java.gavinandre.pojo.ItemsCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gavinandre on 17-1-9.
 */
@Controller
public class JsonTest {

    //请求的json响应json，请求商品信息
    // (该信息为json格式，在页面中通过ajax向写入用户请求的json信息，需要加入@RequestBody注解)，
    // 商品信息用json格式输出商品信息(请求的url实际返回的是itemsCustom对象，
    // 但由于使用了@ResponseBody就将返回的pojo对象转换成了json格式的数据)
    @RequestMapping("/requestJson")
    public
    @ResponseBody
    ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) throws Exception {
        System.out.println("requestJson");
        return itemsCustom;
    }

    //请求key/value(在页面中通过ajax写入用户想要请求的key/value信息，
    // 不需要加@RequestBody注解)，响应json(由于action返回的是itemsCustom对象，
    // 所以需要加入@ResponseBody注解将pojo对象转换为json格式响应给用户)
    @RequestMapping("/responseJson")
    public
    @ResponseBody
    ItemsCustom responseJson(ItemsCustom itemsCustom) throws Exception {
        System.out.println("responseJson");
        return itemsCustom;
    }
}
