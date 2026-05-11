package dgtic.core.controller;

import dgtic.core.service.bitacora.BitacoraService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class BitacoraController {

    private final BitacoraService bitacoraService;

    @GetMapping("/bitacora")
    public String listarBitacora(@RequestParam(required = false)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd")
                                 LocalDate fechaInicio,
                                 @RequestParam(required = false)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd")
                                 LocalDate fechaFin,
                                 Model model) {
        if (fechaInicio != null && fechaFin != null) {
            LocalDateTime inicio = fechaInicio.atStartOfDay();
            LocalDateTime fin = fechaFin.atTime(23, 59, 59);
            model.addAttribute("bitacoras",
                    bitacoraService.buscarPorFechas(inicio, fin));
        } else {
            model.addAttribute("bitacoras", bitacoraService.listarBitacora());
        }
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);

        return "bitacora/lista";
    }
}