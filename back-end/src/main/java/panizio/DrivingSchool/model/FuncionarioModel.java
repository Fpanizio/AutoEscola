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
    private Integer id;

    // Dados Pessoais
    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 100, message = "O nome completo deve ter entre 3 e 100 caracteres")
    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF inválido. Formato esperado: 123.456.789-09")
    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;

    @NotBlank(message = "RG é obrigatório")
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}$", message = "RG inválido. Formato esperado: 12.345.678-9")
    @Column(name = "rg", unique = true, nullable = false, length = 12)
    private String rg;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Sexo é obrigatório")
    @Column(name = "sexo", nullable = false)
    private SexoEnum sexo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Estado civil é obrigatório")
    @Column(name = "estado_civil", nullable = false)
    private EstadoCivilEnum estadoCivil;

    @NotBlank(message = "Nacionalidade é obrigatória")
    @Size(min = 3, max = 50, message = "A nacionalidade deve ter entre 3 e 50 caracteres")
    @Column(name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

    @NotBlank(message = "Naturalidade é obrigatória")
    @Size(min = 3, max = 50, message = "A naturalidade deve ter entre 3 e 50 caracteres")
    @Column(name = "naturalidade", nullable = false, length = 50)
    private String naturalidade;

    // Endereço
    @NotBlank(message = "Logradouro é obrigatório")
    @Size(min = 3, max = 100, message = "O logradouro deve ter entre 3 e 100 caracteres")
    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @NotBlank(message = "Número do endereço é obrigatório")
    @Size(min = 1, max = 10, message = "O número do endereço deve ter entre 1 e 10 caracteres")
    @Column(name = "numero_endereco", nullable = false, length = 10)
    private String numeroEndereco;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(min = 3, max = 50, message = "O bairro deve ter entre 3 e 50 caracteres")
    @Column(name = "bairro", nullable = false, length = 50)
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(min = 3, max = 50, message = "A cidade deve ter entre 3 e 50 caracteres")
    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP inválido. Formato esperado: 12345-678")
    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Size(max = 50, message = "O complemento deve ter no máximo 50 caracteres")
    @Column(name = "complemento", length = 50)
    private String complemento;

    // Contato
    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Telefone inválido. Formato esperado: +55 (11) 12345-6789")
    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @NotBlank(message = "Email é obrigatório")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email inválido")
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

    @Pattern(regexp = "^\\d{11}$", message = "Número da CNH inválido. Deve conter 11 dígitos")
    @Column(name = "numero_cnh", unique = true, length = 11)
    private String numeroCnh;

    @Future(message = "A validade da CNH deve ser uma data futura")
    @Column(name = "validade_cnh")
    private LocalDate validadeCnh;

    // Contato de Emergência
    @NotBlank(message = "Nome do contato de emergência é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do contato de emergência deve ter entre 3 e 100 caracteres")
    @Column(name = "contato_emergencia_nome", nullable = false, length = 100)
    private String contatoEmergenciaNome;

    @NotBlank(message = "Parentesco do contato de emergência é obrigatório")
    @Size(min = 3, max = 20, message = "O parentesco do contato de emergência deve ter entre 3 e 20 caracteres")
    @Column(name = "contato_emergencia_parentesco", nullable = false, length = 20)
    private String contatoEmergenciaParentesco;

    @NotBlank(message = "Telefone do contato de emergência é obrigatório")
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Telefone do contato de emergência inválido. Formato esperado: +55 (11) 12345-6789")
    @Column(name = "contato_emergencia_telefone", nullable = false, length = 20)
    private String contatoEmergenciaTelefone;

    // Documentação
    @NotBlank(message = "PIS/PASEP é obrigatório")
    @Pattern(regexp = "^\\d{3}\\.\\d{5}\\.\\d{2}-\\d{1}$", message = "PIS/PASEP inválido. Formato esperado: 123.45678.90-1")
    @Column(name = "pis_pasep", nullable = false, length = 14)
    private String pisPasep;

    @Size(max = 18, message = "A CTPS deve ter no máximo 18 caracteres")
    @Column(name = "ctps", length = 18)
    private String ctps;

    @Column(name = "titulo_eleitor", nullable = true)
    private String tituloEleitor;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Órgão emissor é obrigatório")
    @Column(name = "orgao_emissor", nullable = false)
    private OrgaoEmissorEnum orgaoEmissor;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "UF emissora é obrigatória")
    @Column(name = "uf_emissor", nullable = false)
    private UfEnum ufEmissor;

    @Column(name = "observacoes")
    private String observacoes;
}