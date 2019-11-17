package com.jiangjie.ohs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AntDesignController {

	/**
     * 配置url通配符，拦截多个地址
     * @return
     */
    @RequestMapping(value = {
            "/",
            "/user",
            "/user/**",
            "/console",
            "/console/**"
    })
    public String fowardIndex() {
        return "index";
    }
}
