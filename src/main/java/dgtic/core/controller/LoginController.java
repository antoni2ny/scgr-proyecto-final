package dgtic.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/sin-acceso")
    public String accesoDenegado() {
        return "sin-acceso";
    }
}