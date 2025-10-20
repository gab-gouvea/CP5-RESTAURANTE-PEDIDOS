package br.com.restaurante.pedidos.dto;

import br.com.restaurante.pedidos.entity.Item;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ItemRequestDTO(
        @NotBlank
        @Size(min = 2, max = 100)
        String nome,

        @Size(max = 300)
        String descricao,

        @NotNull
        @DecimalMin(value = "0.01")
        @Digits(integer = 6, fraction = 2)
        BigDecimal preco,

        @NotNull
        Item.CategoriaItem categoria
) {
}