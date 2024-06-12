package br.com.fcoromoto.desafios.totvs.contas.mapper;

import br.com.fcoromoto.desafios.totvs.contas.dto.SalvarContaDTO;
import br.com.fcoromoto.desafios.totvs.contas.dto.ContaDTO;
import br.com.fcoromoto.desafios.totvs.contas.model.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ContaMapper {

    Conta fromSalvarContaDtoToEntity(SalvarContaDTO dto);

    ContaDTO fromEntityToContaDto(Conta conta);

    void atualizarConta(SalvarContaDTO dto, @MappingTarget Conta conta);
}
