package br.com.restaurante.pedidos.repository;

import br.com.restaurante.pedidos.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByAtivoTrue();
    
    List<Item> findByCategoria(Item.CategoriaItem categoria);
    
    List<Item> findByCategoriaAndAtivoTrue(Item.CategoriaItem categoria);
    
    Optional<Item> findByIdAndAtivoTrue(Long id);
    
    List<Item> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
}