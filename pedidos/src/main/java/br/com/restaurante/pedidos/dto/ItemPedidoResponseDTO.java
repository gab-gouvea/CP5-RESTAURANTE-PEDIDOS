package br.com.restaurante.pedidos.dto;

import br.com.restaurante.pedidos.entity.ItemPedido;
import br.com.restaurante.pedidos.entity.Item;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
        Long id,
        String nomeProduto,
        String descricaoProduto,
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal,
        String observacoesItem,
        Item.CategoriaItem categoria
) {
    public ItemPedidoResponseDTO(ItemPedido itemPedido) {
        this(
                itemPedido.getId(),
                itemPedido.getItem().getNome(),
                itemPedido.getItem().getDescricao(),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoUnitario(),
                itemPedido.getSubtotal(),
                itemPedido.getObservacoesItem(),
                itemPedido.getItem().getCategoria()
        );
    }
}