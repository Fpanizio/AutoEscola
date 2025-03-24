package panizio.DrivingSchool.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import panizio.DrivingSchool.enums.CategoriaEnum;
import panizio.DrivingSchool.validation.annotation.ValidEnum;

@Data
public class OfertaDTO {
  private Long id;

  @NotBlank(message = "O nome do serviço é obrigatório")
  @Size(max = 100, message = "O nome deve ter até 100 caracteres")
  private String name;

  @NotNull
  @ValidEnum(enumClass = CategoriaEnum.class, message = "A categoria deve ser uma das opções válidas: AULA, EXAME, HABILITACAO, RENOVACAO, RECICLAGEM, SIMULADO")
  private CategoriaEnum categoria;

  @Size(max = 500, message = "A descrição deve ter até 500 caracteres")
  private String description;

  @NotNull(message = "O preço é obrigatório")
  @Positive(message = "O preço deve ser maior que zero")
  private BigDecimal price;

  @NotNull(message = "O status ativo é obrigatório")
  private Boolean isActive;
}
