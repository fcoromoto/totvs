package br.com.fcoromoto.desafios.totvs.contas.mapper;

import br.com.fcoromoto.desafios.totvs.contas.dto.salvarContaDTO;
import br.com.fcoromoto.desafios.totvs.contas.dto.ContaDTO;
import br.com.fcoromoto.desafios.totvs.contas.model.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ContaMapper {

    Conta fromSalvarContaDtoToEntity(salvarContaDTO dto);

    ContaDTO fromEntityToContaDto(Conta conta);

    void atualizarConta(salvarContaDTO dto, @MappingTarget Conta conta);
}
