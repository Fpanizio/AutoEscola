package panizio.DrivingSchool.enums;

import panizio.DrivingSchool.exception.NotFoundData;

public enum CategoriaEnum {
  AULA("AULA"),
  EXAME("EXAME"),
  HABILITACAO("HABILITAÇÃO"),
  RENOVAÇÃO("RENOVAÇÃO"),
  RECICLAGEM("RECICLAGEM"),
  SIMULADO("SIMULADO");

  private final String descricao;

  CategoriaEnum(String descricao) {
    this.descricao = descricao;
  }

  @Override
  public String toString() {
    return descricao;
  }

  public static CategoriaEnum fromString(String descricao) {
    if (descricao == null) {
      throw new NotFoundData("A descrição não pode ser nula.");
    }
    if (descricao.trim().isEmpty()) {
      throw new NotFoundData("A descrição não pode estar vazia.");
    }
    for (CategoriaEnum grau : CategoriaEnum.values()) {
      if (grau.descricao.equalsIgnoreCase(descricao)) {
        return grau;
      }
    }
    throw new NotFoundData("Categoria inválida: " + descricao);
  }
}
