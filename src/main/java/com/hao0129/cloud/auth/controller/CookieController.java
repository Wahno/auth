package com.hao0129.cloud.auth.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CookieController {
    @RequestMapping("/test/cookie")
    public String cookie(HttpServletRequest request) {
        JSONArray jsonArray = new JSONArray();
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                jsonArray.add(cookie.getName() + " : " + cookie.getValue());
            }
        }
        return jsonArray.toJSONString();
    }
}
