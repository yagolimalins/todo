package dev.yagolins.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoRequest {

    @NotBlank(message = "O título é obrigatório")
    private String title;
}
