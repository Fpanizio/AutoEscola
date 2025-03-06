package panizio.DrivingSchool.enums;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GrauEscolaridadeEnum {
    ANALFABETO("Analfabeto"),
    ENSINO_FUNDAMENTAL_INCOMPLETO("Ensino Fundamental Incompleto"),
    ENSINO_FUNDAMENTAL_COMPLETO("Ensino Fundamental Completo"),
    ENSINO_MEDIO_INCOMPLETO("Ensino Médio Incompleto"),
    ENSINO_MEDIO_COMPLETO("Ensino Médio Completo"),
    ENSINO_SUPERIOR_INCOMPLETO("Ensino Superior Incompleto"),
    ENSINO_SUPERIOR_COMPLETO("Ensino Superior Completo");

    private final String descricao;

    GrauEscolaridadeEnum(String descricao) {
        this.descricao = descricao;
    }

    // Desserialização flexível (aceita nome da constante OU descrição)
    @JsonCreator
    public static GrauEscolaridadeEnum fromValue(String value) {
        return Arrays.stream(values())
            .filter(e -> e.name().equalsIgnoreCase(value) || e.descricao.equalsIgnoreCase(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Valor inválido: " + value));
    }

    // Serialização usa a descrição (igual ao BD)
    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    // Método para conversão JPA (opcional, se necessário)
    public static GrauEscolaridadeEnum fromDescricao(String descricao) {
        return fromValue(descricao);
    }
}