package br.com.restaurante.pedidos.service;

import br.com.restaurante.pedidos.dto.PedidoRequestDTO;
import br.com.restaurante.pedidos.dto.PedidoResponseDTO;
import br.com.restaurante.pedidos.dto.StatusUpdateDTO;
import br.com.restaurante.pedidos.entity.Item;
import br.com.restaurante.pedidos.entity.ItemPedido;
import br.com.restaurante.pedidos.entity.Pedido;
import br.com.restaurante.pedidos.repository.ItemRepository;
import br.com.restaurante.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public PedidoResponseDTO criar(PedidoRequestDTO request) {
        Pedido pedido = new Pedido();
        pedido.setNomeCliente(request.nomeCliente());
        pedido.setTelefoneCliente(request.telefoneCliente());
        pedido.setEnderecoEntrega(request.enderecoEntrega());
        pedido.setTipoPedido(request.tipoPedido());
        pedido.setObservacoes(request.observacoes());
        pedido.setDataEntregaPrevista(request.dataEntregaPrevista());
        pedido.setStatus(Pedido.StatusPedido.PENDENTE);

        request.itens().forEach(itemRequest -> {
            Item item = itemRepository.findById(itemRequest.itemId())
                    .orElseThrow(() -> new RuntimeException("Item não encontrado: " + itemRequest.itemId()));

            if (!item.getAtivo()) {
                throw new RuntimeException("Item inativo: " + item.getNome());
            }

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setItem(item);
            itemPedido.setQuantidade(itemRequest.quantidade());
            itemPedido.setPrecoUnitario(itemRequest.precoUnitario());
            itemPedido.setObservacoesItem(itemRequest.observacoesItem());

            pedido.getItens().add(itemPedido);
        });

        pedido.calcularValorTotal();
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedidoSalvo);
    }

    public List<PedidoResponseDTO> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoResponseDTO::new)
                .toList();
    }

    public List<PedidoResponseDTO> listarPorStatus(Pedido.StatusPedido status) {
        return pedidoRepository.findByStatus(status)
                .stream()
                .map(PedidoResponseDTO::new)
                .toList();
    }

    public List<PedidoResponseDTO> listarPorTipo(Pedido.TipoPedido tipo) {
        return pedidoRepository.findByTipoPedido(tipo)
                .stream()
                .map(PedidoResponseDTO::new)
                .toList();
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return new PedidoResponseDTO(pedido);
    }

    @Transactional
    public PedidoResponseDTO atualizarStatus(Long id, StatusUpdateDTO statusUpdate) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(statusUpdate.status());
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedidoAtualizado);
    }

    @Transactional
    public PedidoResponseDTO confirmar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (!pedido.podeSerConfirmado()) {
            throw new RuntimeException("Pedido não pode ser confirmado no status atual: " + pedido.getStatus());
        }

        pedido.setStatus(Pedido.StatusPedido.CONFIRMADO);
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedidoAtualizado);
    }

    @Transactional
    public PedidoResponseDTO cancelar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (!pedido.podeSerCancelado()) {
            throw new RuntimeException("Pedido não pode ser cancelado no status atual: " + pedido.getStatus());
        }

        pedido.setStatus(Pedido.StatusPedido.CANCELADO);
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedidoAtualizado);
    }
}