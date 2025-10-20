package br.com.restaurante.pedidos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cliente", nullable = false, length = 100)
    private String nomeCliente;

    @Column(name = "telefone_cliente", nullable = false, length = 15)
    private String telefoneCliente;

    @Column(name = "endereco_entrega", length = 200)
    private String enderecoEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPedido status = StatusPedido.PENDENTE;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pedido", nullable = false)
    private TipoPedido tipoPedido;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(name = "observacoes", length = 500)
    private String observacoes;

    @CreatedDate
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_entrega_prevista")
    private LocalDateTime dataEntregaPrevista;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public enum StatusPedido {
        PENDENTE,
        CONFIRMADO,
        PREPARANDO,
        PRONTO,
        SAIU_ENTREGA,
        ENTREGUE,
        CANCELADO
    }

    public enum TipoPedido {
        BALCAO,
        DELIVERY,
        MESA
    }

    public void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean podeSerCancelado() {
        return status == StatusPedido.PENDENTE || status == StatusPedido.CONFIRMADO;
    }

    public boolean podeSerConfirmado() {
        return status == StatusPedido.PENDENTE;
    }

    public boolean podeIniciarPreparo() {
        return status == StatusPedido.CONFIRMADO;
    }
}