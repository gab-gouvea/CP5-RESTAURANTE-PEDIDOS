package br.com.restaurante.pedidos.dto;

import br.com.restaurante.pedidos.entity.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ItemResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Item.CategoriaItem categoria,
        Boolean ativo,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
    public ItemResponseDTO(Item item) {
        this(
                item.getId(),
                item.getNome(),
                item.getDescricao(),
                item.getPreco(),
                item.getCategoria(),
                item.getAtivo(),
                item.getDataCriacao(),
                item.getDataAtualizacao()
        );
    }
}