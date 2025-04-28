package panizio.DrivingSchool.mapper;

import org.springframework.stereotype.Component;

import panizio.DrivingSchool.dto.FuncionarioDTO;
import panizio.DrivingSchool.model.FuncionarioModel;

@Component
public class FuncionarioMapper {

    public FuncionarioDTO toDTO(FuncionarioModel model) {
        if (model == null) {
            return null;
        }
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setNomeCompleto(model.getNomeCompleto());
        dto.setCpf(model.getCpf());
        dto.setRg(model.getRg());
        dto.setDataNascimento(model.getDataNascimento());
        dto.setSexo(model.getSexo());
        dto.setEstadoCivil(model.getEstadoCivil());
        dto.setNacionalidade(model.getNacionalidade());
        dto.setNaturalidade(model.getNaturalidade());
        dto.setLogradouro(model.getLogradouro());
        dto.setNumeroEndereco(model.getNumeroEndereco());
        dto.setBairro(model.getBairro());
        dto.setCidade(model.getCidade());
        dto.setCep(model.getCep());
        dto.setComplemento(model.getComplemento());
        dto.setTelefone(model.getTelefone());
        dto.setEmail(model.getEmail());
        dto.setEscolaridade(model.getEscolaridade());
        dto.setCategoriaCnh(model.getCategoriaCnh());
        dto.setNumeroCnh(model.getNumeroCnh());
        dto.setValidadeCnh(model.getValidadeCnh());
        dto.setContatoEmergenciaNome(model.getContatoEmergenciaNome());
        dto.setContatoEmergenciaParentesco(model.getContatoEmergenciaParentesco());
        dto.setContatoEmergenciaTelefone(model.getContatoEmergenciaTelefone());
        dto.setPisPasep(model.getPisPasep());
        dto.setCtps(model.getCtps());
        dto.setTituloEleitor(model.getTituloEleitor());
        dto.setOrgaoEmissor(model.getOrgaoEmissor());
        dto.setUfEmissor(model.getUfEmissor());
        dto.setObservacoes(model.getObservacoes());
        return dto;
    }

    public FuncionarioModel toModel(FuncionarioDTO dto) {
        if (dto == null) {
            return null;
        }
        FuncionarioModel model = new FuncionarioModel();
        model.setNomeCompleto(dto.getNomeCompleto());
        model.setCpf(dto.getCpf());
        model.setRg(dto.getRg());
        model.setDataNascimento(dto.getDataNascimento());
        model.setSexo(dto.getSexo());
        model.setEstadoCivil(dto.getEstadoCivil());
        model.setNacionalidade(dto.getNacionalidade());
        model.setNaturalidade(dto.getNaturalidade());
        model.setLogradouro(dto.getLogradouro());
        model.setNumeroEndereco(dto.getNumeroEndereco());
        model.setBairro(dto.getBairro());
        model.setCidade(dto.getCidade());
        model.setCep(dto.getCep());
        model.setComplemento(dto.getComplemento());
        model.setTelefone(dto.getTelefone());
        model.setEmail(dto.getEmail());
        model.setEscolaridade(dto.getEscolaridade());
        model.setCategoriaCnh(dto.getCategoriaCnh());
        model.setNumeroCnh(dto.getNumeroCnh());
        model.setValidadeCnh(dto.getValidadeCnh());
        model.setContatoEmergenciaNome(dto.getContatoEmergenciaNome());
        model.setContatoEmergenciaParentesco(dto.getContatoEmergenciaParentesco());
        model.setContatoEmergenciaTelefone(dto.getContatoEmergenciaTelefone());
        model.setPisPasep(dto.getPisPasep());
        model.setCtps(dto.getCtps());
        model.setTituloEleitor(dto.getTituloEleitor());
        model.setOrgaoEmissor(dto.getOrgaoEmissor());
        model.setUfEmissor(dto.getUfEmissor());
        model.setObservacoes(dto.getObservacoes());
        return model;
    }
}