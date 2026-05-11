package dgtic.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/principal")
    public String principal() {
        return "principal/principal";
    }
}