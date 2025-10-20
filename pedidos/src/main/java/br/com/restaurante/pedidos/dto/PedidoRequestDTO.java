package br.com.restaurante.pedidos.dto;

import br.com.restaurante.pedidos.entity.Pedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoRequestDTO(
        @NotBlank
        @Size(min = 2, max = 100)
        String nomeCliente,

        @NotBlank
        @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}")
        String telefoneCliente,

        @Size(max = 200)
        String enderecoEntrega,

        @NotNull
        Pedido.TipoPedido tipoPedido,

        @Size(max = 500)
        String observacoes,

        LocalDateTime dataEntregaPrevista,

        @NotEmpty
        @Valid
        List<ItemPedidoRequestDTO> itens
) {
}