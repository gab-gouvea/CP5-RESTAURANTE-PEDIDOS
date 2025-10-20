package br.com.restaurante.pedidos.dto;

import br.com.restaurante.pedidos.entity.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
        Long id,
        String nomeProduto,
        String descricaoProduto,
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal,
        String observacoesItem,
        ItemPedido.CategoriaProduto categoria
) {
    public ItemPedidoResponseDTO(ItemPedido itemPedido) {
        this(
                itemPedido.getId(),
                itemPedido.getNomeProduto(),
                itemPedido.getDescricaoProduto(),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoUnitario(),
                itemPedido.getSubtotal(),
                itemPedido.getObservacoesItem(),
                itemPedido.getCategoria()
        );
    }
}