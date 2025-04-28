package panizio.DrivingSchool.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import panizio.DrivingSchool.enums.CategoriaCNHEnum;
import panizio.DrivingSchool.validation.annotation.ValidData;
import panizio.DrivingSchool.validation.annotation.ValidEnum;

@Data
public class VeiculoDTO {
  @NotBlank
  @Size(max = 7)
  private String placa;

  @NotBlank
  @Size(max = 11)
  private String renavam;

  @NotBlank
  @Size(min = 3, max = 50)
  private String marca;

  @NotBlank
  @Size(min = 3, max = 50)
  private String modelo;

  @NotNull
  @ValidEnum(enumClass = CategoriaCNHEnum.class, message = "A categoria deve ser um dos seguintes valores: A, B, C, D, E, AB")
  private CategoriaCNHEnum categoria;

  @NotNull
  @ValidData(formato = "yyyy-MM-dd", message = "A data de inicio de serviço deve estar no formato yyyy-MM-dd (ex: 1990-01-01)")
  private LocalDate data_inicio;

  @NotNull
  @ValidData(formato = "yyyy-MM-dd", message = "A data de termino de serviço deve estar no formato yyyy-MM-dd (ex: 1990-01-01)")
  private LocalDate data_termino;

  @NotNull
  private String ano_fabricacao;

  @NotNull
  private String data_limite;

  private String observacoes;
}
