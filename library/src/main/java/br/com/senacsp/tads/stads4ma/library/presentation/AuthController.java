package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.models.LoginRequest;
import br.com.senacsp.tads.stads4ma.library.models.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pela autenticação de usuários.
 * - Registra novos usuários.
 * - Realiza login e retorna token JWT.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * @apiNote Cria um novo usuário no sistema.
     * @param request Contém nome, email, senha e plano do usuário.
     * @return 201 Created com dados do usuário recém-criado.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // TODO: validação do plano e criação do usuário
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuário registrado com sucesso!");
    }

    /**
     * @apiNote Realiza login e retorna token JWT.
     * @param request Contém email e senha do usuário.
     * @return 200 OK com token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // TODO: validação de credenciais e geração de token JWT
        return ResponseEntity.ok("Login realizado com sucesso! Token: [JWT_TOKEN]");
    }
}

