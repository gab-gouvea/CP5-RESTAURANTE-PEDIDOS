package br.com.restaurante.pedidos.dto;

import br.com.restaurante.pedidos.entity.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        String nomeCliente,
        String telefoneCliente,
        String enderecoEntrega,
        Pedido.StatusPedido status,
        Pedido.TipoPedido tipoPedido,
        BigDecimal valorTotal,
        String observacoes,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao,
        LocalDateTime dataEntregaPrevista,
        List<ItemPedidoResponseDTO> itens
) {
    public PedidoResponseDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getNomeCliente(),
                pedido.getTelefoneCliente(),
                pedido.getEnderecoEntrega(),
                pedido.getStatus(),
                pedido.getTipoPedido(),
                pedido.getValorTotal(),
                pedido.getObservacoes(),
                pedido.getDataCriacao(),
                pedido.getDataAtualizacao(),
                pedido.getDataEntregaPrevista(),
                pedido.getItens().stream()
                        .map(ItemPedidoResponseDTO::new)
                        .toList()
        );
    }
}