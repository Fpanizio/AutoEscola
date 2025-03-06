package panizio.DrivingSchool.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import panizio.DrivingSchool.enums.GrauEscolaridadeEnum;

@Converter(autoApply = true)
public class GrauEscolaridadeConverter implements AttributeConverter<GrauEscolaridadeEnum, String> {

    @Override
    public String convertToDatabaseColumn(GrauEscolaridadeEnum grau) {
        return (grau != null) ? grau.getDescricao() : null; // Usa a descrição do enum
    }

    @Override
    public GrauEscolaridadeEnum convertToEntityAttribute(String dbData) {
        return (dbData != null) ? GrauEscolaridadeEnum.fromDescricao(dbData) : null;
    }
}