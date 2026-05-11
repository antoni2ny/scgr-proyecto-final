package dgtic.core.security.jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request,
                                          HttpServletResponse response) {
        String refreshToken = obtenerCookie(request, "refreshToken");
        if (refreshToken == null || !jwtService.validarToken(refreshToken)) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Refresh token inválido o expirado");
        }
        String username = jwtService.obtenerUsername(refreshToken);
        String nuevoAccessToken =
                jwtService.generarAccessToken(username);
        Cookie accessCookie =
                new Cookie("accessToken", nuevoAccessToken);
        accessCookie.setHttpOnly(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(15 * 60);
        response.addCookie(accessCookie);

        return ResponseEntity.ok("Access token renovado");
    }

    private String obtenerCookie(HttpServletRequest request,
                                 String nombre) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(nombre)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}