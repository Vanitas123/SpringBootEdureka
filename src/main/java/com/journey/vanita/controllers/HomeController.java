package com.journey.vanita.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api(basePath = "/", value = "/", description = "Home Controller")
@Controller
public class HomeController {

  @RequestMapping("/")
  public String home() {
    return "redirect:swagger-ui.html";
  }

}