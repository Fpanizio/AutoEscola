package panizio.DrivingSchool.enums;

import panizio.DrivingSchool.exception.NotFoundData;

public enum GrauEscolaridadeEnum {
    ANALFABETO("ANALFABETO"),
    ENSINO_FUNDAMENTAL_INCOMPLETO("ENSINO FUNDAMENTAL INCOMPLETO"),
    ENSINO_FUNDAMENTAL_COMPLETO("ENSINO FUNDAMENTAL COMPLETO"),
    ENSINO_MEDIO_INCOMPLETO("ENSINO MEDIO INCOMPLETO"),
    ENSINO_MEDIO_COMPLETO("ENSINO MEDIO COMPLETO"),
    ENSINO_SUPERIOR_INCOMPLETO("ENSINO SUPERIOR INCOMPLETO"),
    ENSINO_SUPERIOR_COMPLETO("ENSINO SUPERIOR COMPLETO");

    private final String descricao;

    // Construtor
    GrauEscolaridadeEnum(String descricao) {
        this.descricao = descricao;
    }

    // Retorna a descrição formatada
    @Override
    public String toString() {
        return descricao;
    }

    // Método para converter uma string com espaços de volta para o enum
    public static GrauEscolaridadeEnum fromString(String descricao) {
        if (descricao == null) {
            throw new NotFoundData("A descrição não pode ser nula.");
        }
        if (descricao.trim().isEmpty()) {
            throw new NotFoundData("A descrição não pode estar vazia.");
        }
        for (GrauEscolaridadeEnum grau : GrauEscolaridadeEnum.values()) {
            if (grau.descricao.equalsIgnoreCase(descricao)) {
                return grau;
            }
        }
        throw new NotFoundData("Grau de escolaridade inválido: " + descricao);
    }
}