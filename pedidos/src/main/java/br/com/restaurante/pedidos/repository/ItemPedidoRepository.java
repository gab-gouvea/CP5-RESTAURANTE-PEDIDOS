package br.com.restaurante.pedidos.repository;

import br.com.restaurante.pedidos.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findByPedidoId(Long pedidoId);
    
    List<ItemPedido> findByItemId(Long itemId);
    
    List<ItemPedido> findByPedidoIdAndItemId(Long pedidoId, Long itemId);
}