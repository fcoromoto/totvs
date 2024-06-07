package br.com.fcoromoto.desafios.totvs.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class salvarContaDTO implements Serializable {

    @NotNull
    private LocalDate dataVencimento;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private String descricao;
}
