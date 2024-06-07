package br.com.fcoromoto.desafios.totvs.service;

import br.com.fcoromoto.desafios.totvs.model.Conta;
import br.com.fcoromoto.desafios.totvs.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public Page<Conta> buscarTodas(Pageable pageable) {
        return contaRepository.findAll(pageable);
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }


    public Conta salvarConta(Conta Conta) {
        return contaRepository.save(Conta);
    }

    public void excluirConta(Long id) {
        contaRepository.deleteById(id);
    }
}
