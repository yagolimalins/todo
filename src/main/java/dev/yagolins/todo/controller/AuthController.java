package dev.yagolins.todo.controller;

import dev.yagolins.todo.docs.AuthControllerDocs;
import dev.yagolins.todo.dto.request.AuthRequest;
import dev.yagolins.todo.dto.request.RegisterRequest;
import dev.yagolins.todo.dto.response.AuthResponse;
import dev.yagolins.todo.dto.response.MessageResponse;
import dev.yagolins.todo.security.JwtService;
import dev.yagolins.todo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(
            @RequestBody RegisterRequest request
    ) {
        userService.register(request.getUsername(), request.getPassword());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Usuário criado com sucesso"));
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(request.getUsername());

        return new AuthResponse(token);
    }
}


