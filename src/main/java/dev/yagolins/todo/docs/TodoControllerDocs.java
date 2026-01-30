package dev.yagolins.todo.docs;

import dev.yagolins.todo.dto.request.TodoRequest;
import dev.yagolins.todo.dto.request.UpdateTodoTitleRequest;
import dev.yagolins.todo.entity.Todo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Tarefas",
        description = "Endpoints para gerenciamento de tarefas"
)

public interface TodoControllerDocs {

    @Operation(
            summary = "Criar uma nova tarefa",
            description = "Cria uma nova tarefa com título informado e status inicial como não concluída"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
    })
    ResponseEntity<Todo> create(
            @RequestBody(
                    description = "Dados da tarefa a ser criada",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TodoRequest.class))
            )
            TodoRequest request
    );

    @Operation(
            summary = "Listar todas as tarefas",
            description = "Retorna todas as tarefas cadastradas"
    )
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    ResponseEntity<List<Todo>> findAll();

    @Operation(
            summary = "Buscar tarefa por ID",
            description = "Retorna os dados de uma tarefa a partir do seu identificador único"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
    })
    ResponseEntity<Todo> findById(
            @Parameter(
                    description = "ID da tarefa",
                    required = true
            )
            UUID id
    );

    @Operation(
            summary = "Atualizar título da tarefa",
            description = "Atualiza apenas o título de uma tarefa existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Título atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    ResponseEntity<Todo> updateTitle(
            @Parameter(
                    description = "ID da tarefa",
                    required = true
            )
            UUID id,
            @RequestBody(
                    description = "Novo título da tarefa",
                    required = true
            )
            UpdateTodoTitleRequest request
    );


    @Operation(
            summary = "Alterar status da tarefa",
            description = "Alterna o status da tarefa entre concluída e não concluída"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status alterado com sucesso"),
    })
    ResponseEntity<Todo> toggleStatus(
            @Parameter(
                    description = "ID da tarefa",
                    required = true
            )
            UUID id
    );

    @Operation(
            summary = "Remover tarefa",
            description = "Remove uma tarefa existente pelo seu identificador"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tarefa removida com sucesso"),
    })
    ResponseEntity<Void> delete(
            @Parameter(
                    description = "ID da tarefa",
                    required = true
            )
            UUID id
    );
}
