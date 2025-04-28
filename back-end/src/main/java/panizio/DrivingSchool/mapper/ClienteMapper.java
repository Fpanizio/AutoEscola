package panizio.DrivingSchool.mapper;

import org.springframework.stereotype.Component;

import panizio.DrivingSchool.dto.ClienteDTO;
import panizio.DrivingSchool.model.ClienteModel;

@Component
public class ClienteMapper {

  public ClienteDTO toDTO(ClienteModel model) {
    if (model == null) {
      return null;
    }
    ClienteDTO dto = new ClienteDTO();
    dto.setNomeCompleto(model.getNomeCompleto());
    dto.setDataNascimento(model.getDataNascimento());
    dto.setNaturalidade(model.getNaturalidade());
    dto.setNacionalidade(model.getNacionalidade());
    dto.setEscolaridade(model.getEscolaridade());
    dto.setEstadoCivil(model.getEstadoCivil());
    dto.setSexo(model.getSexo());
    dto.setNomePai(model.getNomePai());
    dto.setNomeMae(model.getNomeMae());
    dto.setLogradouro(model.getLogradouro());
    dto.setNumeroEndereco(model.getNumeroEndereco());
    dto.setBairro(model.getBairro());
    dto.setCidade(model.getCidade());
    dto.setCep(model.getCep());
    dto.setComplemento(model.getComplemento());
    dto.setTelefone(model.getTelefone());
    dto.setEmail(model.getEmail());
    dto.setCpf(model.getCpf());
    dto.setRg(model.getRg());
    dto.setOrgaoEmissor(model.getOrgaoEmissor());
    dto.setUfEmissor(model.getUfEmissor());
    dto.setContatoEmergenciaNome(model.getContatoEmergenciaNome());
    dto.setContatoEmergenciaParentesco(model.getContatoEmergenciaParentesco());
    dto.setContatoEmergenciaTelefone(model.getContatoEmergenciaTelefone());
    dto.setObservacoes(model.getObservacoes());
    return dto;
  }

  public ClienteModel toModel(ClienteDTO dto) {
    if (dto == null) {
      return null;
    }
    ClienteModel model = new ClienteModel();
    model.setNomeCompleto(dto.getNomeCompleto());
    model.setDataNascimento(dto.getDataNascimento());
    model.setNaturalidade(dto.getNaturalidade());
    model.setNacionalidade(dto.getNacionalidade());
    model.setEscolaridade(dto.getEscolaridade());
    model.setEstadoCivil(dto.getEstadoCivil());
    model.setSexo(dto.getSexo());
    model.setNomePai(dto.getNomePai());
    model.setNomeMae(dto.getNomeMae());
    model.setLogradouro(dto.getLogradouro());
    model.setNumeroEndereco(dto.getNumeroEndereco());
    model.setBairro(dto.getBairro());
    model.setCidade(dto.getCidade());
    model.setCep(dto.getCep());
    model.setComplemento(dto.getComplemento());
    model.setTelefone(dto.getTelefone());
    model.setEmail(dto.getEmail());
    model.setCpf(dto.getCpf());
    model.setRg(dto.getRg());
    model.setOrgaoEmissor(dto.getOrgaoEmissor());
    model.setUfEmissor(dto.getUfEmissor());
    model.setContatoEmergenciaNome(dto.getContatoEmergenciaNome());
    model.setContatoEmergenciaParentesco(dto.getContatoEmergenciaParentesco());
    model.setContatoEmergenciaTelefone(dto.getContatoEmergenciaTelefone());
    model.setObservacoes(dto.getObservacoes());
    return model;
  }
}