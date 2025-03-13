package panizio.DrivingSchool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import panizio.DrivingSchool.converter.SexoEnumConverter;
import panizio.DrivingSchool.enums.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "funcionarios")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Dados Pessoais
    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Formato de CPF inválido (XXX.XXX.XXX-XX)")
    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;

    @NotBlank(message = "RG é obrigatório")
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}$", message = "Formato de RG inválido (XX.XXX.XXX-X)")
    @Column(name = "rg", unique = true, nullable = false, length = 12)
    private String rg;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Convert(converter = SexoEnumConverter.class)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Sexo é obrigatório")
    @Column(name = "sexo", nullable = false)
    private SexoEnum sexo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Estado civil é obrigatório")
    @Column(name = "estado_civil", nullable = false)
    private EstadoCivilEnum estadoCivil;

    @NotBlank(message = "Nacionalidade é obrigatória")
    @Size(min = 3, max = 50, message = "Nacionalidade deve ter entre 3 e 50 caracteres")
    @Column(name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

    @NotBlank(message = "Naturalidade é obrigatória")
    @Size(min = 3, max = 50, message = "Naturalidade deve ter entre 3 e 50 caracteres")
    @Column(name = "naturalidade", nullable = false, length = 50)
    private String naturalidade;

    // Contato
    @NotBlank(message = "Logradouro é obrigatório")
    @Size(min = 3, max = 100, message = "Logradouro deve ter entre 3 e 100 caracteres")
    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @NotBlank(message = "Número do endereço é obrigatório")
    @Size(min = 1, max = 10, message = "Número deve ter até 10 caracteres")
    @Column(name = "numero_endereco", nullable = false, length = 10)
    private String numeroEndereco;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(min = 3, max = 50, message = "Bairro deve ter entre 3 e 50 caracteres")
    @Column(name = "bairro", nullable = false, length = 50)
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(min = 3, max = 50, message = "Cidade deve ter entre 3 e 50 caracteres")
    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Formato de CEP inválido (XXXXX-XXX)")
    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @Size(max = 50, message = "Complemento deve ter até 50 caracteres")
    @Column(name = "complemento", length = 50)
    private String complemento;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Formato de telefone inválido (+XX (XX) XXXXX-XXXX)")
    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // Profissionais
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Escolaridade é obrigatória")
    @Column(name = "escolaridade", nullable = false)
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
    @Column(name = "contato_emergencia_nome", nullable = false, length = 100)
    private String contatoEmergenciaNome;

    @NotBlank(message = "Parentesco do contato de emergência é obrigatório")
    @Size(min = 3, max = 20, message = "Parentesco deve ter entre 3 e 20 caracteres")
    @Column(name = "contato_emergencia_parentesco", nullable = false, length = 20)
    private String contatoEmergenciaParentesco;

    @NotBlank(message = "Telefone de emergência é obrigatório")
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Formato inválido (+XX (XX) XXXXX-XXXX)")
    @Column(name = "contato_emergencia_telefone", nullable = false, length = 20)
    private String contatoEmergenciaTelefone;

    // Documentação
    @NotBlank(message = "PIS/PASEP é obrigatório")
    @Pattern(regexp = "^\\d{3}\\.\\d{5}\\.\\d{2}-\\d{1}$", message = "Formato de PIS/PASEP inválido (XXX.XXXXX.XX-X)")
    @Column(name = "pis_pasep", nullable = false, length = 14)
    private String pisPasep;

    @Column(name = "ctps", length = 18)
    private String ctps;

    @Column(name = "titulo_eleitor", length = 14)
    private String tituloEleitor;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Órgão emissor é obrigatório")
    @Column(name = "orgao_emissor", nullable = false, length = 50)
    private orgaoEmissorEnum orgaoEmissor;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "UF do órgão emissor é obrigatório")
    @Column(name = "uf_emissor", nullable = false, length = 2)
    private ufEnum ufEmissor;

    @Column(name = "observacoes")
    private String observacoes;
}