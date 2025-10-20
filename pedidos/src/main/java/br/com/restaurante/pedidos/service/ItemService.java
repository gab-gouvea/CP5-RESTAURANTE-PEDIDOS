package br.com.restaurante.pedidos.service;

import br.com.restaurante.pedidos.dto.ItemRequestDTO;
import br.com.restaurante.pedidos.dto.ItemResponseDTO;
import br.com.restaurante.pedidos.entity.Item;
import br.com.restaurante.pedidos.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public ItemResponseDTO criar(ItemRequestDTO request) {
        Item item = new Item();
        item.setNome(request.nome());
        item.setDescricao(request.descricao());
        item.setPreco(request.preco());
        item.setCategoria(request.categoria());
        item.setAtivo(true);

        Item itemSalvo = itemRepository.save(item);
        return new ItemResponseDTO(itemSalvo);
    }

    public List<ItemResponseDTO> listarTodos() {
        return itemRepository.findAll()
                .stream()
                .map(ItemResponseDTO::new)
                .toList();
    }

    public List<ItemResponseDTO> listarAtivos() {
        return itemRepository.findByAtivoTrue()
                .stream()
                .map(ItemResponseDTO::new)
                .toList();
    }

    public List<ItemResponseDTO> listarPorCategoria(Item.CategoriaItem categoria) {
        return itemRepository.findByCategoriaAndAtivoTrue(categoria)
                .stream()
                .map(ItemResponseDTO::new)
                .toList();
    }

    public ItemResponseDTO buscarPorId(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        return new ItemResponseDTO(item);
    }

    @Transactional
    public ItemResponseDTO atualizar(Long id, ItemRequestDTO request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        item.setNome(request.nome());
        item.setDescricao(request.descricao());
        item.setPreco(request.preco());
        item.setCategoria(request.categoria());

        Item itemAtualizado = itemRepository.save(item);
        return new ItemResponseDTO(itemAtualizado);
    }

    @Transactional
    public void inativar(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        item.setAtivo(false);
        itemRepository.save(item);
    }

    @Transactional
    public void ativar(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        item.setAtivo(true);
        itemRepository.save(item);
    }

    @Transactional
    public void deletar(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item não encontrado");
        }
        itemRepository.deleteById(id);
    }
}