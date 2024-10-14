package com.miprimermongokate.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @GetMapping("/quienessomos")
    public String quienessomos() {
        return "quienessomos"; // Este debe ser el nombre del archivo sin la extensión .html
    }
  
}
