package br.com.restaurante.pedidos.controller;

import br.com.restaurante.pedidos.dto.ItemRequestDTO;
import br.com.restaurante.pedidos.dto.ItemResponseDTO;
import br.com.restaurante.pedidos.entity.Item;
import br.com.restaurante.pedidos.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemResponseDTO> criar(@Valid @RequestBody ItemRequestDTO request) {
        ItemResponseDTO response = itemService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> listarTodos() {
        List<ItemResponseDTO> itens = itemService.listarTodos();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<ItemResponseDTO>> listarAtivos() {
        List<ItemResponseDTO> itens = itemService.listarAtivos();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ItemResponseDTO>> listarPorCategoria(@PathVariable Item.CategoriaItem categoria) {
        List<ItemResponseDTO> itens = itemService.listarPorCategoria(categoria);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> buscarPorId(@PathVariable Long id) {
        ItemResponseDTO item = itemService.buscarPorId(id);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ItemRequestDTO request) {
        ItemResponseDTO response = itemService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        itemService.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        itemService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}