package dgtic.core.controller;

import dgtic.core.service.perfil.PerfilUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PerfilUsuarioController {

    private final PerfilUsuarioService perfilUsuarioService;

    @GetMapping("/perfil")
    public String verPerfil(Model model) {
        model.addAttribute("perfil", perfilUsuarioService.obtenerPerfilUsuarioLogueado());

        return "perfil/perfil";
    }
}