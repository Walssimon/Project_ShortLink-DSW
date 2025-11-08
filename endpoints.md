------------------------------
AuthController.java
------------------------------

package com.shortlink.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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
        // validação do plano e criação do usuário
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
    }
}

------------------------------
LinkController.java
------------------------------

package com.shortlink.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

/**
 * Controlador responsável pelos CRUDs de Links.
 * - Cada link pertence a um criador (user_id)
 * - Pode ou não pertencer a um grupo (group_id opcional)
 */
@RestController
@RequestMapping("/links")
public class LinkController {

    /**
     * @apiNote Lista todos os links do usuário autenticado.
     * @param groupId (opcional) filtra links de um grupo específico.
     * @param active (opcional) filtra por status ativo/inativo.
     * @return 200 OK com lista de links.
     */

    @GetMapping
    public ResponseEntity<?> listLinks(
            @RequestParam(required = false) String groupId,
            @RequestParam(required = false) Boolean active
    ) {
        // busca os links conforme filtros e permissões
        return ResponseEntity.ok("Lista de links do usuário");
    }

    /**
     * @apiNote Cria um novo link (respeitando limite do plano).
     * @param request Contém URL original e (opcionalmente) o grupo.
     * @return 201 Created com o link encurtado.
     */
    @PostMapping
    public ResponseEntity<?> createLink(@RequestBody LinkRequest request) {
        // valida plano, gera shortCode e salva
        return ResponseEntity.status(HttpStatus.CREATED).body("Link criado com sucesso");
    }

    /**
     * @apiNote Atualiza um link existente do usuário autenticado.
     * @param id ID do link a ser atualizado.
     * @param request Campos a atualizar (URL original, ativo, expiração).
     * @return 200 OK com link atualizado.
     */
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLink(@PathVariable String id, @RequestBody LinkRequest request) {
        // verifica se link pertence ao usuário
        return ResponseEntity.ok("Link atualizado com sucesso");
    }

    /**
     * @apiNote Deleta um link criado pelo usuário autenticado.
     * @param id ID do link.
     * @return 204 No Content.
     */
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable String id) {
        // verifica propriedade e exclui
        return ResponseEntity.noContent().build();
    }

    /**
     * @apiNote Adiciona um link existente a um grupo.
     * @param id ID do link.
     * @param request Contém o ID do grupo destino.
     * @return 200 OK.
     */
    
    @PostMapping("/{id}/add-to-group")
    public ResponseEntity<?> addToGroup(@PathVariable String id, @RequestBody GroupRequest request) {
        // verifica se user é membro e associa link ao grupo
        return ResponseEntity.ok("Link adicionado ao grupo");
    }

    /**
     * @apiNote Remove um link de um grupo.
     * @param id ID do link.
     * @return 200 OK.
     */
    @DeleteMapping("/{id}/remove-from-group")
    public ResponseEntity<?> removeFromGroup(@PathVariable String id) {
        // remove associação de grupo
        return ResponseEntity.ok("Link removido do grupo");
    }
}


------------------------------
GroupController.java
------------------------------

package com.shortlink.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

/**
 * Controlador responsável pela gestão de grupos.
 * - Criação e listagem de grupos.
 * - Adição e remoção de membros.
 * - Visualização de links do grupo.
 */
@RestController
@RequestMapping("/groups")
public class GroupController {

    /**
     * @apiNote Lista todos os grupos que o usuário autenticado participa.
     * @return 200 OK com lista de grupos.
     */
    @GetMapping
    public ResponseEntity<?> listGroups() {
        return ResponseEntity.ok("Lista de grupos do usuário");
    }

    /**
     * @apiNote Cria um novo grupo.
     * @param request Contém o nome do grupo.
     * @return 201 Created.
     */
    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody GroupRequest request) {
        // cria grupo e adiciona o criador como membro
        return ResponseEntity.status(HttpStatus.CREATED).body("Grupo criado");
    }

    /**
     * @apiNote Adiciona um membro ao grupo.
     * @param groupId ID do grupo.
     * @param request Contém o ID do usuário a ser adicionado.
     * @return 200 OK.
     */
    @PostMapping("/{groupId}/members")
    public ResponseEntity<?> addMember(@PathVariable String groupId, @RequestBody AddMemberRequest request) {
        // valida se o user autenticado pertence ao grupo
        return ResponseEntity.ok("Membro adicionado");
    }

    /**
     * @apiNote Remove um membro do grupo.
     * @param groupId ID do grupo.
     * @param userId ID do usuário a remover.
     * @return 200 OK.
     */
    @DeleteMapping("/{groupId}/members/{userId}")
    public ResponseEntity<?> removeMember(@PathVariable String groupId, @PathVariable String userId) {
        // valida permissões e remove
        return ResponseEntity.ok("Membro removido");
    }

    /**
     * @apiNote Lista os links pertencentes a um grupo.
     * @param groupId ID do grupo.
     * @return 200 OK com lista de links do grupo.
     */
    @GetMapping("/{groupId}/links")
    public ResponseEntity<?> listGroupLinks(@PathVariable String groupId) {
        return ResponseEntity.ok("Links do grupo");
    }
}


------------------------------
MovementHistoryController.java
------------------------------

package com.shortlink.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<?> listHistory(@RequestParam(required = false) String entity) {
        return ResponseEntity.ok("Histórico de movimentações");
    }
}


------------------------------
ClickController.java
------------------------------

package com.shortlink.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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
    public ResponseEntity<?> listClicks(@PathVariable String linkId) {
        return ResponseEntity.ok("Lista de cliques do link");
    }
}

@RestController
@RequestMapping("/")
public class LinkController {

    /**
     * @apiNote Registra click no link.
     * @return redirecionamento do usuario.
    */
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirectLink() {
        return ResponseEntity.ok("redirect de usuario e registro de click");
    }

}

------------------------------
Modelos de Requisição (DTOs)
------------------------------

public record RegisterRequest(String name, String email, String password, Long plan_id) {}
public record LoginRequest(String email, String password) {}
public record LinkRequest(String originalUrl, String group_id) {}
public record GroupRequest(String name) {}
public record AddMemberRequest(String user_id) {}

GET -> https://projeto.shortlink.com/links
POST -> https://projeto.shortlink.com/links
PATCH -> https://projeto.shortlink.com/links/{id}
DELETE -> https://projeto.shortlink.com/links/{id}

POST -> https://projeto.shortlink.com/links/{id}/add-to-group
DELETE -> https://projeto.shortlink.com/links/{id}/remove-from-group

-------------------------

GET -> https://projeto.shortlink.com/groups
POST -> https://projeto.shortlink.com/groups
POST -> https://projeto.shortlink.com/groups/{groupId}/members
DELETE -> https://projeto.shortlink.com/groups/{groupId}/members/{userId}
GET -> https://projeto.shortlink.com/groups/{groupId}/links

-------------------------

GET -> https://projeto.shortlink.com/history

---------------------------

GET -> https://projeto.shortlink.com/links/{linkId}/clicks

-------------------------

GET -> https://projeto.shortlink.com/{shortCode}

--------------------------

