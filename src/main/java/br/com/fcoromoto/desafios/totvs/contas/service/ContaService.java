package br.com.fcoromoto.desafios.totvs.contas.service;

import br.com.fcoromoto.desafios.totvs.contas.dto.SalvarContaDTO;
import br.com.fcoromoto.desafios.totvs.contas.dto.ContaDTO;
import br.com.fcoromoto.desafios.totvs.contas.mapper.ContaMapper;
import br.com.fcoromoto.desafios.totvs.contas.model.Conta;
import br.com.fcoromoto.desafios.totvs.contas.model.Situacao;
import br.com.fcoromoto.desafios.totvs.contas.repository.ContaRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;

    public Page<Conta> buscarTodas(String descricao, LocalDate dataVencimento, Pageable pageable) {
        Specification<Conta> spec = Specification.where(null);

        spec = Specification.where(spec).and((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("situacao"), Situacao.PENDENTE));

        if (descricao != null) {
            spec = Specification.where(spec).and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("descricao"), "%" + descricao + "%"));
        }

        if (dataVencimento != null) {
            spec = Specification.where(spec).and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("dataVencimento"), dataVencimento));
        }

        return contaRepository.findAll(spec, pageable);
    }

    public ContaDTO buscarPorId(Long id) {
        Conta conta = recuperarContaPorId(id);
        return contaMapper.fromEntityToContaDto(conta);
    }

    public ContaDTO cadastrarConta(SalvarContaDTO dto) {
        Conta conta = contaMapper.fromSalvarContaDtoToEntity(dto);
        conta.setSituacao(Situacao.PENDENTE);
        contaRepository.save(conta);
        return contaMapper.fromEntityToContaDto(conta);
    }

    public ContaDTO atualizarConta(SalvarContaDTO dto, Long id) {
        Conta conta = recuperarContaPorId(id);
        contaMapper.atualizarConta(dto, conta);
        contaRepository.save(conta);
        return contaMapper.fromEntityToContaDto(conta);
    }

    public ContaDTO atualizarSituacaoConta(Long id, Situacao situacao) {
        Conta conta = recuperarContaPorId(id);
        conta.setSituacao(situacao);
        if (Situacao.PAGO.equals(situacao)) {
            conta.setDataPagamento(LocalDate.now());
        }
        contaRepository.save(conta);
        return contaMapper.fromEntityToContaDto(conta);
    }

    public void excluirConta(Long id) {
        contaRepository.deleteById(id);
    }

    public BigDecimal consultarTotalPagoPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return contaRepository.consultarTotalPagoPeriodo(dataInicio, dataFim);
    }

    private Conta recuperarContaPorId(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public void importarContas(MultipartFile file) throws CsvValidationException, IOException {
        List<Conta> contas = parseCSVFile(file);
        contaRepository.saveAll(contas);
    }

    public List<Conta> parseCSVFile(MultipartFile arquivo) throws IOException, CsvValidationException {
        List<Conta> contas = new ArrayList<>();

        try (Reader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
            CSVReader csvReader = new CSVReader(reader);

            csvReader.readNext();

            String[] linha;

            while ((linha = csvReader.readNext()) != null) {
                try {

                    Long id = null; // id ignorado do arquivo, será gerado pelo banco
                    LocalDate dataVencimento = LocalDate.parse(linha[1]);
                    LocalDate dataPagamento = StringUtils.hasText(linha[2]) ? LocalDate.parse(linha[2]) : null;
                    BigDecimal valor = new BigDecimal(linha[3]);
                    String descricao = linha[4];
                    Situacao situacao = Situacao.valueOf(linha[5]);


                    Conta conta = Conta.builder()
                            .id(id)
                            .dataVencimento(dataVencimento)
                            .dataPagamento(dataPagamento)
                            .valor(valor)
                            .descricao(descricao)
                            .situacao(situacao)
                            .build();

                    contas.add(conta);
                } catch (NumberFormatException  e) {
                    System.err.println("Error parsing linha: " + Arrays.toString(linha) + " - " + e.getMessage());
                }
            }
        }
        return contas;
    }
}
