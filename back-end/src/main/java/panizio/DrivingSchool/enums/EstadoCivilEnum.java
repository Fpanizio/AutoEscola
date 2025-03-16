package panizio.DrivingSchool.enums;

import panizio.DrivingSchool.exception.NotFoundData;

public enum EstadoCivilEnum {
    SOLTEIRO("SOLTEIRO"),
    CASADO("CASADO"),
    DIVORCIADO("DIVORCIADO"),
    VIUVO("VIUVO"),
    SEPARADO("SEPARADO"),
    UNIAO_ESTAVEL("UNIÃO ESTAVEL");

    private final String descricao;

    // Construtor
    EstadoCivilEnum(String descricao) {
        this.descricao = descricao;
    }

    // Retorna a descrição formatada
    @Override
    public String toString() {
        return descricao;
    }

    // Método para converter uma string com espaços de volta para o enum
    public static EstadoCivilEnum fromString(String descricao) {
        if (descricao == null) {
            throw new NotFoundData("A descrição não pode ser nula.");
        }
        if (descricao.trim().isEmpty()) {
            throw new NotFoundData("A descrição não pode estar vazia.");
        }
        for (EstadoCivilEnum grau : EstadoCivilEnum.values()) {
            if (grau.descricao.equalsIgnoreCase(descricao)) {
                return grau;
            }
        }
        throw new NotFoundData("Estado civil inválido: " + descricao);
    }
}