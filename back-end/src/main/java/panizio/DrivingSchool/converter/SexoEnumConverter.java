package panizio.DrivingSchool.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import panizio.DrivingSchool.enums.SexoEnum;

@Converter(autoApply = true) // Aplica automaticamente a todas as entidades
public class SexoEnumConverter implements AttributeConverter<SexoEnum, String> {

    @Override
    public String convertToDatabaseColumn(SexoEnum sexo) {
        if (sexo == null) return null;
        switch (sexo) {
            case MASCULINO: return "Masculino";
            case FEMININO: return "Feminino";
            case NAO_INFORMADO: return "Não Informado";
            default: throw new IllegalArgumentException("Sexo inválido: " + sexo);
        }
    }

    @Override
    public SexoEnum convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        switch (dbValue) {
            case "Masculino": return SexoEnum.MASCULINO;
            case "Feminino": return SexoEnum.FEMININO;
            case "Não Informado": return SexoEnum.NAO_INFORMADO;
            default: throw new IllegalArgumentException("Sexo inválido: " + dbValue);
        }
    }
}