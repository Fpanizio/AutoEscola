package panizio.DrivingSchool.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import panizio.DrivingSchool.enums.*;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Dados Pessoais
  @NotBlank(message = "O nome é obrigatório")
  @Size(min = 3, max = 100)
  @Column(nullable = false, length = 100)
  private String nome_completo;

  @NotBlank(message = "O CPF é obrigatório")
  @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Formato: XXX.XXX.XXX-XX")
  @Column(unique = true, nullable = false, length = 14)
  private String cpf;

  @NotBlank(message = "O RG é obrigatório")
  @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}$", message = "Formato: XX.XXX.XXX-X")
  @Column(unique = true, nullable = false, length = 12)
  private String rg;

  @NotNull(message = "A data de nascimento é obrigatória")
  @Column(nullable = false)
  private LocalDate data_nascimento;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SexoEnum sexo;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado_civil", nullable = false)
  private EstadoCivilEnum estadoCivil;

  @NotBlank(message = "A nacionalidade é obrigatória")
  @Size(min = 3, max = 50)
  @Column(nullable = false, length = 50)
  private String nacionalidade;

  @NotBlank(message = "A naturalidade é obrigatória")
  @Size(min = 3, max = 50)
  @Column(nullable = false, length = 50)
  private String naturalidade;

  // Contato
  @NotBlank
  @Size(min = 3, max = 100)
  @Column(nullable = false, length = 100)
  private String logradouro;

  @NotBlank
  @Size(min = 1, max = 10)
  @Column(nullable = false, length = 10)
  private String numero_endereco;

  @NotBlank
  @Size(min = 3, max = 50)
  @Column(nullable = false, length = 50)
  private String bairro;

  @NotBlank
  @Size(min = 3, max = 50)
  @Column(nullable = false, length = 50)
  private String cidade;

  @NotBlank
  @Size(min = 2, max = 2)
  @Column(nullable = false, length = 2)
  private String uf;

  @NotBlank
  @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Formato: XXXXX-XXX")
  @Column(nullable = false, length = 10)
  private String cep;

  @Size(max = 50)
  @Column(length = 50)
  private String complemento;

  @NotBlank
  @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Formato: +XX (XX) XXXXX-XXXX")
  @Column(nullable = false, length = 20)
  private String telefone;

  @NotBlank
  @Email
  @Column(nullable = false, length = 100)
  private String email;

  // Profissionais
  @NotNull
  @Column(nullable = false)
  private LocalDate data_admissao;

  @Column
  private LocalDate data_demissao;

  @NotNull
  @DecimalMin("0.01")
  @Digits(integer = 8, fraction = 2)
  @Column(nullable = false)
  private BigDecimal salario;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SituacaoEnum situacao;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private GrauEscolaridadeEnum escolaridade;

  @Enumerated(EnumType.STRING)
  @Column
  private CategoriaCNHEnum categoria_cnh;

  @Pattern(regexp = "^\\d{14}$", message = "CNH deve ter 14 dígitos")
  @Column(unique = true, length = 14)
  private String numero_cnh;

  @Future
  @Column
  private LocalDate validade_cnh;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private GrauEscolaridadeEnum categoria_ensino;

  // Contato de Emergência
  @NotBlank
  @Size(min = 3, max = 100)
  @Column(nullable = false, length = 100)
  private String contato_emergencia_nome;

  @NotBlank
  @Size(min = 3, max = 20)
  @Column(nullable = false, length = 20)
  private String contato_emergencia_parentesco;

  @NotBlank
  @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Formato: +XX (XX) XXXXX-XXXX")
  @Column(nullable = false, length = 20)
  private String contato_emergencia_telefone;

  // Documentação
  @NotBlank
  @Pattern(regexp = "^\\d{3}\\.\\d{5}\\.\\d{2}-\\d{1}$", message = "Formato: XXX.XXXXX.XX-X")
  @Column(nullable = false, length = 14)
  private String pis_pasep;

  @Pattern(regexp = "^\\d{7}-\\d{2}\\.\\d{4}-\\d{2}$", message = "Formato: XXXXXXX-XX.XXXX-XX")
  @Column(length = 14)
  private String ctps;

  @Pattern(regexp = "^\\d{4} \\d{4} \\d{4}$", message = "Formato: XXXX XXXX XXXX")
  @Column(length = 14)
  private String titulo_eleitor;

  @Column
  private String observacoes;

  @Column(updatable = false)
  private LocalDate data_cadastro = LocalDate.now();
}