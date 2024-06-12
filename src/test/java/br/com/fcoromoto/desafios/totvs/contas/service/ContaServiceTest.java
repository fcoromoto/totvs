package br.com.fcoromoto.desafios.totvs.contas.service;

import br.com.fcoromoto.desafios.totvs.contas.dto.ContaDTO;
import br.com.fcoromoto.desafios.totvs.contas.dto.SalvarContaDTO;
import br.com.fcoromoto.desafios.totvs.contas.mapper.ContaMapper;
import br.com.fcoromoto.desafios.totvs.contas.model.Conta;
import br.com.fcoromoto.desafios.totvs.contas.model.Situacao;
import br.com.fcoromoto.desafios.totvs.contas.repository.ContaRepository;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ContaMapper contaMapper;

    @Test
    void buscarTodas() {
    }

    @Test
    void buscarPorId() {
        when(contaRepository.findById(1L)).thenReturn(java.util.Optional.of(new Conta()));

        contaService.buscarPorId(1L);

        verify(contaRepository, times(1)).findById(eq(1L));
    }

    @Test
    void cadastrarConta() {
        Conta conta = new Conta();
        ContaDTO contaDTO = new ContaDTO();
        SalvarContaDTO salvarContaDTO = new SalvarContaDTO();

        when(contaMapper.fromSalvarContaDtoToEntity(salvarContaDTO)).thenReturn(conta);
        when(contaRepository.save(conta)).thenReturn(conta);
        when(contaMapper.fromEntityToContaDto(conta)).thenReturn(contaDTO);

        ContaDTO result = contaService.cadastrarConta(salvarContaDTO);

        assertNotNull(result);
        verify(contaMapper, times(1)).fromSalvarContaDtoToEntity(salvarContaDTO);
        verify(contaRepository, times(1)).save(conta);
        verify(contaMapper, times(1)).fromEntityToContaDto(conta);
    }

    @Test
    void atualizarConta() {
        Conta conta = new Conta();
        ContaDTO contaDTO = new ContaDTO();
        SalvarContaDTO salvarContaDTO = new SalvarContaDTO();

        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(conta));
        doNothing().when(contaMapper).atualizarConta(salvarContaDTO, conta);
        when(contaRepository.save(conta)).thenReturn(conta);
        when(contaMapper.fromEntityToContaDto(conta)).thenReturn(contaDTO);

        ContaDTO result = contaService.atualizarConta(salvarContaDTO, 1L);

        assertNotNull(result);
        verify(contaRepository, times(1)).findById(1L);
        verify(contaMapper, times(1)).atualizarConta(salvarContaDTO, conta);
        verify(contaRepository, times(1)).save(conta);
        verify(contaMapper, times(1)).fromEntityToContaDto(conta);
    }

    @Test
    void atualizarSituacaoConta() {
        Conta conta = new Conta();
        ContaDTO contaDTO = new ContaDTO();

        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(conta));
        when(contaRepository.save(conta)).thenReturn(conta);
        when(contaMapper.fromEntityToContaDto(conta)).thenReturn(contaDTO);

        ContaDTO result = contaService.atualizarSituacaoConta(1L, Situacao.PAGO);

        assertNotNull(result);
        assertEquals(Situacao.PAGO, conta.getSituacao());
        verify(contaRepository, times(1)).findById(1L);
        verify(contaRepository, times(1)).save(conta);
        verify(contaMapper, times(1)).fromEntityToContaDto(conta);
    }

    @Test
    void excluirConta() {
        doNothing().when(contaRepository).deleteById(anyLong());

        contaService.excluirConta(1L);

        verify(contaRepository, times(1)).deleteById(1L);
    }

    @Test
    void consultarTotalPagoPeriodo() {
        BigDecimal total = BigDecimal.TEN;
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = LocalDate.now().plusDays(1);

        when(contaRepository.consultarTotalPagoPeriodo(dataInicio, dataFim)).thenReturn(total);

        BigDecimal result = contaService.consultarTotalPagoPeriodo(dataInicio, dataFim);

        assertNotNull(result);
        assertEquals(total, result);
        verify(contaRepository, times(1)).consultarTotalPagoPeriodo(dataInicio, dataFim);
    }
}