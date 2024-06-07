package br.com.fcoromoto.desafios.totvs.controller;

import br.com.fcoromoto.desafios.totvs.model.Conta;
import br.com.fcoromoto.desafios.totvs.service.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<Page<Conta>> buscarTodas(Pageable pageable) {
        Page<Conta> contas = contaService.buscarTodas(pageable);
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
        Conta conta = contaService.buscarPorId(id);
        return ResponseEntity.ok(conta);
    }

    @PostMapping
    public ResponseEntity<Conta> salvarConta(@RequestBody Conta Conta) {
        Conta savedConta = contaService.salvarConta(Conta);
        return ResponseEntity.ok(savedConta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirConta(@PathVariable Long id) {
        contaService.excluirConta(id);
        return ResponseEntity.noContent().build();
    }
}
