package com.miprimermongokate.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index.html")
    public String home() {
        return "index"; // Esto redirigirá a index.html
    }
}
