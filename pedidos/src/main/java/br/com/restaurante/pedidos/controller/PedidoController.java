package br.com.restaurante.pedidos.controller;

import br.com.restaurante.pedidos.dto.PedidoRequestDTO;
import br.com.restaurante.pedidos.dto.PedidoResponseDTO;
import br.com.restaurante.pedidos.dto.StatusUpdateDTO;
import br.com.restaurante.pedidos.entity.Pedido;
import br.com.restaurante.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@Valid @RequestBody PedidoRequestDTO request) {
        PedidoResponseDTO response = pedidoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarTodos() {
        List<PedidoResponseDTO> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PedidoResponseDTO>> listarPorStatus(@PathVariable Pedido.StatusPedido status) {
        List<PedidoResponseDTO> pedidos = pedidoService.listarPorStatus(status);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PedidoResponseDTO>> listarPorTipo(@PathVariable Pedido.TipoPedido tipo) {
        List<PedidoResponseDTO> pedidos = pedidoService.listarPorTipo(tipo);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        PedidoResponseDTO pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(@PathVariable Long id, @Valid @RequestBody StatusUpdateDTO statusUpdate) {
        PedidoResponseDTO response = pedidoService.atualizarStatus(id, statusUpdate);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<PedidoResponseDTO> confirmar(@PathVariable Long id) {
        PedidoResponseDTO response = pedidoService.confirmar(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<PedidoResponseDTO> cancelar(@PathVariable Long id) {
        PedidoResponseDTO response = pedidoService.cancelar(id);
        return ResponseEntity.ok(response);
    }
}
