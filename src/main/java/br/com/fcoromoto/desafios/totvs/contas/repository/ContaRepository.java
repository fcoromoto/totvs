package br.com.fcoromoto.desafios.totvs.contas.repository;

import br.com.fcoromoto.desafios.totvs.contas.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ContaRepository extends JpaRepository<Conta, Long> , JpaSpecificationExecutor<Conta> {

    Page<Conta> findAll(Pageable pageable);

    @Query("SELECT SUM(valor) FROM Conta c WHERE situacao = 'PAGO' AND dataPagamento >= :dataPagamentoInicio AND dataPagamento <= :dataPagamentoFim")
    BigDecimal consultarTotalPagoPeriodo(@Param("dataPagamentoInicio") LocalDate dataPagamentoInicio,
                                         @Param("dataPagamentoFim") LocalDate dataPagamentoFim);
}
