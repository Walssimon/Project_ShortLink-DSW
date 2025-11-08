package br.com.senacsp.tads.stads4ma.library.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para redirecionamento de links encurtados.
 * - Registra click no link.
 * - Redireciona o usuário para a URL original.
 */
@RestController
@RequestMapping("/")
public class RedirectController {

    /**
     * @apiNote Registra click no link e redireciona o usuário.
     * @param shortCode Código curto do link.
     * @return Redirecionamento HTTP 302 para a URL original.
     */
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirectLink(@PathVariable String shortCode) {
        // TODO: busca link pelo shortCode, registra click e redireciona
        return ResponseEntity.ok("redirect de usuario e registro de click");
    }
}

