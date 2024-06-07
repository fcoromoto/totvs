package br.com.fcoromoto.desafios.totvs.dto;

import br.com.fcoromoto.desafios.totvs.model.Situacao;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ContaDTO implements Serializable {

    private Long id;

    private LocalDate dataVencimento;

    private LocalDate dataPagamento;

    private BigDecimal valor;

    private String descricao;

    private Situacao situacao;
}
