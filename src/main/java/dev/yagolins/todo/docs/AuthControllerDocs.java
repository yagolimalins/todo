package dev.yagolins.todo.docs;

import dev.yagolins.todo.dto.request.AuthRequest;
import dev.yagolins.todo.dto.request.RegisterRequest;
import dev.yagolins.todo.dto.response.AuthResponse;
import dev.yagolins.todo.dto.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Auth",
        description = "Endpoints para registro e login de usuários"
)
public interface AuthControllerDocs {

    @Operation(
            summary = "Login",
            description = "Autentica o usuário e retorna um token JWT"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    AuthResponse login(@RequestBody AuthRequest request);

    @Operation(
            summary = "Registro",
            description = "Cria um novo usuário"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Username já existente")
    })
    ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest request);

}
