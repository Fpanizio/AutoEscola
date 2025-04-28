package panizio.DrivingSchool.mapper;

import org.springframework.stereotype.Component;

import panizio.DrivingSchool.dto.VeiculoDTO;
import panizio.DrivingSchool.model.VeiculoModel;

@Component
public class VeiculoMapper {

  public VeiculoDTO toDTO(VeiculoModel model) {
    if (model == null) {
      return null;
    }
    VeiculoDTO dto = new VeiculoDTO();
    dto.setPlaca(model.getPlaca());
    dto.setRenavam(model.getRenavam());
    dto.setMarca(model.getMarca());
    dto.setModelo(model.getModelo());
    dto.setCategoria(model.getCategoria());
    dto.setData_inicio(model.getData_inicio());
    dto.setData_termino(model.getData_termino());
    dto.setAno_fabricacao(model.getAno_fabricacao());
    dto.setData_limite(model.getData_limite());
    dto.setObservacoes(model.getObservacoes());
    return dto;
  }

  public VeiculoModel toModel(VeiculoDTO dto) {
    if (dto == null) {
      return null;
    }
    VeiculoModel model = new VeiculoModel();
    model.setPlaca(dto.getPlaca());
    model.setRenavam(dto.getRenavam());
    model.setMarca(dto.getMarca());
    model.setModelo(dto.getModelo());
    model.setCategoria(dto.getCategoria());
    model.setData_inicio(dto.getData_inicio());
    model.setData_termino(dto.getData_termino());
    model.setAno_fabricacao(dto.getAno_fabricacao());
    model.setData_limite(dto.getData_limite());
    model.setObservacoes(dto.getObservacoes());
    return model;
  }
}