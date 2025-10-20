package br.com.restaurante.pedidos.repository;

import br.com.restaurante.pedidos.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(Pedido.StatusPedido status);
    
    List<Pedido> findByTipoPedido(Pedido.TipoPedido tipoPedido);
    
    List<Pedido> findByNomeClienteContainingIgnoreCase(String nomeCliente);
    
    List<Pedido> findByTelefoneCliente(String telefone);
}