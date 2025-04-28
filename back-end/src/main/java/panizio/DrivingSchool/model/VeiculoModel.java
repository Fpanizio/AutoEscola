package panizio.DrivingSchool.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import panizio.DrivingSchool.enums.CategoriaCNHEnum;

@Data
@Entity
@Table(name = "veiculos")
public class VeiculoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Column(name = "placa")
  private String placa;

  @NotBlank
  @Column(name = "renavam")
  private String renavam;

  @NotBlank
  @Column(name = "marca")
  private String marca;
  
  @NotBlank
  @Column(name = "modelo")
  private String modelo;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "categoria")
  private CategoriaCNHEnum categoria;

  @NotNull
  @Column(name = "data_inicio")
  private LocalDate data_inicio;

  @NotNull
  @Column(name = "data_termino")
  private LocalDate data_termino;

  @NotNull
  @Column(name = "ano_fabricacao")
  private String ano_fabricacao;

  @NotNull
  @Column(name = "data_limite")
  private String data_limite;

  @Column(name = "observacoes")
  private String observacoes;

}
