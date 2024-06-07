package br.com.fcoromoto.desafios.totvs.repository;

import br.com.fcoromoto.desafios.totvs.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Page<Conta> findAll(Pageable pageable);
}
