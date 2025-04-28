package panizio.DrivingSchool.mapper;

import org.springframework.stereotype.Component;

import panizio.DrivingSchool.dto.OfertaDTO;
import panizio.DrivingSchool.model.OfertaModel;

@Component
public class OfertaMapper {

    public OfertaDTO toDTO(OfertaModel model) {
        if (model == null) {
            return null;
        }
        OfertaDTO dto = new OfertaDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCategoria(model.getCategoria());
        dto.setDescription(model.getDescription());
        dto.setPrice(model.getPrice());
        dto.setIsActive(model.getIsActive());
        return dto;
    }

    public OfertaModel toModel(OfertaDTO dto) {
        if (dto == null) {
            return null;
        }
        OfertaModel model = new OfertaModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCategoria(dto.getCategoria());
        model.setDescription(dto.getDescription());
        model.setPrice(dto.getPrice());
        model.setIsActive(dto.getIsActive());
        return model;
    }
}