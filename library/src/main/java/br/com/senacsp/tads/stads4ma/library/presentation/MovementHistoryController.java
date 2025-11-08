package br.com.senacsp.tads.stads4ma.library.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para histórico de movimentações do usuário.
 * - Registra e lista ações CRUD (link criado, atualizado, etc.)
 */
@RestController
@RequestMapping("/history")
public class MovementHistoryController {

    /**
     * @apiNote Lista o histórico de ações do usuário autenticado.
     * @param entity (opcional) filtra por tipo (link, group, user).
     * @return 200 OK com histórico.
     */
    @GetMapping
    public ResponseEntity<?> listHistory(
            @RequestParam(required = false) String entity
    ) {
        // TODO: busca histórico do usuário autenticado
        return ResponseEntity.ok("Histórico de movimentações");
    }
}

