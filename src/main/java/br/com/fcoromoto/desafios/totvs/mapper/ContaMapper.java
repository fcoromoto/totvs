package br.com.fcoromoto.desafios.totvs.mapper;

import br.com.fcoromoto.desafios.totvs.dto.salvarContaDTO;
import br.com.fcoromoto.desafios.totvs.dto.ContaDTO;
import br.com.fcoromoto.desafios.totvs.model.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ContaMapper {

    Conta fromSalvarContaDtoToEntity(salvarContaDTO dto);

    ContaDTO fromEntityToContaDto(Conta conta);

    void atualizarConta(salvarContaDTO dto, @MappingTarget Conta conta);
}
