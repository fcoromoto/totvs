package br.com.fcoromoto.desafios.totvs.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "conta")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dataVencimento;

    private LocalDate dataPagamento;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @NotNull
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Situacao situacao;
}
