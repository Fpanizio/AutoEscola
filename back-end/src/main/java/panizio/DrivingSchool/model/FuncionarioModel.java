package panizio.DrivingSchool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import panizio.DrivingSchool.enums.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "funcionarios")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Dados Pessoais
    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Formato de CPF inválido (XXX.XXX.XXX-XX)")
    @Column(unique = true, nullable = false, length = 14)
    private String cpf;

    @NotBlank(message = "RG é obrigatório")
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}$", message = "Formato de RG inválido (XX.XXX.XXX-X)")
    @Column(unique = true, nullable = false, length = 12)
    private String rg;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Sexo é obrigatório")
    @Column(nullable = false)
    private SexoEnum sexo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Estado civil é obrigatório")
    @Column(name = "estado_civil", nullable = false)
    private EstadoCivilEnum estadoCivil;

    @NotBlank(message = "Nacionalidade é obrigatória")
    @Size(min = 3, max = 50, message = "Nacionalidade deve ter entre 3 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nacionalidade;

    @NotBlank(message = "Naturalidade é obrigatória")
    @Size(min = 3, max = 50, message = "Naturalidade deve ter entre 3 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String naturalidade;

    // Contato
    @NotBlank(message = "Logradouro é obrigatório")
    @Size(min = 3, max = 100, message = "Logradouro deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String logradouro;

    @NotBlank(message = "Número do endereço é obrigatório")
    @Size(min = 1, max = 10, message = "Número deve ter até 10 caracteres")
    @Column(nullable = false, length = 10)
    private String numeroEndereco;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(min = 3, max = 50, message = "Bairro deve ter entre 3 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(min = 3, max = 50, message = "Cidade deve ter entre 3 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String cidade;

    @NotBlank(message = "UF é obrigatória")
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres")
    @Column(nullable = false, length = 2)
    private String uf;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Formato de CEP inválido (XXXXX-XXX)")
    @Column(nullable = false, length = 10)
    private String cep;

    @Size(max = 50, message = "Complemento deve ter até 50 caracteres")
    @Column(length = 50)
    private String complemento;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Formato de telefone inválido (+XX (XX) XXXXX-XXXX)")
    @Column(nullable = false, length = 20)
    private String telefone;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(nullable = false, length = 100)
    private String email;

    // Profissionais
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Escolaridade é obrigatória")
    @Column(nullable = false)
    private GrauEscolaridadeEnum escolaridade;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_cnh")
    private CategoriaCNHEnum categoriaCnh;

    @Pattern(regexp = "^\\d{11}$", message = "CNH deve ter 11 dígitos")
    @Column(name = "numero_cnh", unique = true, length = 11)
    private String numeroCnh;

    @Future(message = "Validade da CNH deve ser futura")
    @Column(name = "validade_cnh")
    private LocalDate validadeCnh;

    // Contato de Emergência
    @NotBlank(message = "Nome do contato de emergência é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String contatoEmergenciaNome;

    @NotBlank(message = "Parentesco do contato de emergência é obrigatório")
    @Size(min = 3, max = 20, message = "Parentesco deve ter entre 3 e 20 caracteres")
    @Column(nullable = false, length = 20)
    private String contatoEmergenciaParentesco;

    @NotBlank(message = "Telefone de emergência é obrigatório")
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Formato inválido (+XX (XX) XXXXX-XXXX)")
    @Column(nullable = false, length = 20)
    private String contatoEmergenciaTelefone;

    // Documentação
    @NotBlank(message = "PIS/PASEP é obrigatório")
    @Pattern(regexp = "^\\d{3}\\.\\d{5}\\.\\d{2}-\\d{1}$", message = "Formato de PIS/PASEP inválido (XXX.XXXXX.XX-X)")
    @Column(nullable = false, length = 14)
    private String pisPasep;

    @Column(length = 18)
    private String ctps;

    @Column(length = 14)
    private String tituloEleitor;

    @Column
    private String observacoes;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDate dataCadastro = LocalDate.now();
}