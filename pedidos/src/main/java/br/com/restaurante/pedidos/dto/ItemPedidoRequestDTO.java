package br.com.restaurante.pedidos.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ItemPedidoRequestDTO(
        @NotNull
        Long itemId,

        @NotNull
        @Min(value = 1)
        @Max(value = 99)
        Integer quantidade,

        @NotNull
        @DecimalMin(value = "0.01")
        @Digits(integer = 6, fraction = 2)
        BigDecimal precoUnitario,

        @Size(max = 200)
        String observacoesItem
) {
}