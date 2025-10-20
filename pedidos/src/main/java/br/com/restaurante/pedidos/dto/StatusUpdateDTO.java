package br.com.restaurante.pedidos.dto;

import br.com.restaurante.pedidos.entity.Pedido;
import jakarta.validation.constraints.NotNull;

public record StatusUpdateDTO(
        @NotNull
        Pedido.StatusPedido status
) {
}