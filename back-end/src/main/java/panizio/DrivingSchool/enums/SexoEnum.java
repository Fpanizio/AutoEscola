package panizio.DrivingSchool.enums;

import panizio.DrivingSchool.exception.NotFoundData;

public enum SexoEnum {
    MASCULINO("MASCULINO"),
    FEMININO("FEMININO"),
    NAO_INFORMADO("NAO INFORMADO");

    private final String descricao;

    // Construtor
    SexoEnum(String descricao) {
        this.descricao = descricao;
    }

    // Retorna a descrição formatada
    @Override
    public String toString() {
        return descricao;
    }

    // Método para converter uma string com espaços de volta para o enum
    public static SexoEnum fromString(String descricao) {
        if (descricao == null) {
            throw new NotFoundData("A descrição não pode ser nula.");
        }
        if (descricao.trim().isEmpty()) {
            throw new NotFoundData("A descrição não pode estar vazia.");
        }
        for (SexoEnum grau : SexoEnum.values()) {
            if (grau.descricao.equalsIgnoreCase(descricao)) {
                return grau;
            }
        }
        throw new NotFoundData("Orientação sexual inválido: " + descricao);
    }
}