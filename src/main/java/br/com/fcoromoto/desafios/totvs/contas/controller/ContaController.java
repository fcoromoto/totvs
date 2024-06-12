package br.com.fcoromoto.desafios.totvs.contas.controller;

import br.com.fcoromoto.desafios.totvs.contas.dto.salvarContaDTO;
import br.com.fcoromoto.desafios.totvs.contas.dto.ContaDTO;
import br.com.fcoromoto.desafios.totvs.contas.model.Conta;
import br.com.fcoromoto.desafios.totvs.contas.model.Situacao;
import br.com.fcoromoto.desafios.totvs.contas.service.ContaService;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;

@AllArgsConstructor
@RestController
@RequestMapping("/contas")
public class ContaController {

    private static final int DEFAULT_PAGE_NUMBER = 0;

    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<Page<Conta>> buscarTodas(
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "dataVencimento", required = false) LocalDate dataVencimento,
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE)
                                                        @SortDefault.SortDefaults({
                                                                @SortDefault(sort = "id", direction = Sort.Direction.ASC)
                                                        }) Pageable pageable) {
        Page<Conta> contas = contaService.buscarTodas(descricao, dataVencimento, pageable);
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> buscarPorId(@PathVariable Long id) {
        ContaDTO conta = contaService.buscarPorId(id);
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/dataPagamentoInicio/{dataPagamentoInicio}/dataPagamentoFim/{dataPagamentoFim}")
    public ResponseEntity<BigDecimal> consultarTotalPagoPeriodo(@PathVariable LocalDate dataPagamentoInicio,
                                                                @PathVariable LocalDate dataPagamentoFim) {
        BigDecimal totalPagoPeriodo = contaService.consultarTotalPagoPeriodo(dataPagamentoInicio, dataPagamentoFim);
        return ResponseEntity.ok(totalPagoPeriodo);
    }

    @PostMapping
    public ResponseEntity<ContaDTO> cadastrarConta(@Valid @RequestBody salvarContaDTO contaDTO) {
        ContaDTO contaSalva = contaService.cadastrarConta(contaDTO);
        return ResponseEntity.ok(contaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> atualizarConta(@Valid @RequestBody salvarContaDTO contaDTO, @PathVariable Long id) {
        ContaDTO contaSalva = contaService.atualizarConta(contaDTO, id);
        return ResponseEntity.ok(contaSalva);
    }

    @PutMapping("/{id}/situacao/{situacao}")
    public ResponseEntity<ContaDTO> atualizarSituacaoConta(@PathVariable Long id, @PathVariable Situacao situacao) {
        ContaDTO contaSalva = contaService.atualizarSituacaoConta(id, situacao);
        return ResponseEntity.ok(contaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirConta(@PathVariable Long id) {
        contaService.excluirConta(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/importar-contas")
    public ResponseEntity<?> importAccounts(@RequestParam("file") MultipartFile file)
            throws CsvValidationException, IOException {

        contaService.importarContas(file);
        return ResponseEntity.noContent().build();
    }
}
