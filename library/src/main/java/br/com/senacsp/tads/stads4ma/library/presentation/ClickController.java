package br.com.senacsp.tads.stads4ma.library.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador para registro e consulta de cliques nos links.
 */
@RestController
@RequestMapping("/links")
public class ClickController {

    /**
     * @apiNote Lista todos os cliques de um link (visível para criador ou membros do grupo).
     * @param linkId ID do link.
     * @return 200 OK com lista de cliques.
     */
    @GetMapping("/{linkId}/clicks")
    public ResponseEntity<?> listClicks(@PathVariable UUID linkId) {
        // TODO: busca cliques do link e valida permissões
        return ResponseEntity.ok("Lista de cliques do link");
    }
}

