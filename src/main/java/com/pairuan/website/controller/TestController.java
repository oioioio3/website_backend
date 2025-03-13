package com.pairuan.website.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pairuan.website.common.result.R;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public R<Map<String, String>> test() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "Hello World");
        return R.success(data);
    }
} 