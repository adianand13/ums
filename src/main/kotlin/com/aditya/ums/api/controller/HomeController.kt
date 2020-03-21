package com.aditya.ums.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/getHome")
    fun getHome(): String{
        return "{\"msg\": \"Hello\"}"
    }
}