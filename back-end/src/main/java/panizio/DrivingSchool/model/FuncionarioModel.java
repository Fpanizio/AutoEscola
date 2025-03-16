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
    @Size(min = 3, max = 100)
    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @NotBlank
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")
    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;

    @NotBlank
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}$")
    @Column(name = "rg", unique = true, nullable = false, length = 12)
    private String rg;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "sexo", nullable = false)
    private SexoEnum sexo;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "estado_civil", nullable = false)
    private EstadoCivilEnum estadoCivil;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "naturalidade", nullable = false, length = 50)
    private String naturalidade;

    // Endereço
    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @NotBlank
    @Size(min = 1, max = 10)
    @Column(name = "numero_endereco", nullable = false, length = 10)
    private String numeroEndereco;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "bairro", nullable = false, length = 50)
    private String bairro;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    @NotBlank
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Size(max = 50)
    @Column(name = "complemento", length = 50)
    private String complemento;

    // Contato
    @NotBlank
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$")
    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // Profissionais
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "escolaridade", nullable = false)
    private GrauEscolaridadeEnum escolaridade;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_cnh")
    private CategoriaCnhEnum categoriaCnh;

    @Pattern(regexp = "^\\d{11}$")
    @Column(name = "numero_cnh", unique = true, length = 11)
    private String numeroCnh;

    @Future
    @Column(name = "validade_cnh")
    private LocalDate validadeCnh;

    // Contato de Emergência
    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "contato_emergencia_nome", nullable = false, length = 100)
    private String contatoEmergenciaNome;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "contato_emergencia_parentesco", nullable = false, length = 20)
    private String contatoEmergenciaParentesco;

    @NotBlank
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$")
    @Column(name = "contato_emergencia_telefone", nullable = false, length = 20)
    private String contatoEmergenciaTelefone;

    // Documentação
    @NotBlank
    @Pattern(regexp = "^\\d{3}\\.\\d{5}\\.\\d{2}-\\d{1}$")
    @Column(name = "pis_pasep", nullable = false, length = 14)
    private String pisPasep;

    @Column(name = "ctps", length = 18)
    private String ctps;

    @Column(name = "titulo_eleitor", length = 14)
    private String tituloEleitor;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "orgao_emissor", nullable = false)
    private OrgaoEmissorEnum orgaoEmissor;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "uf_emissor", nullable = false)
    private UfEnum ufEmissor;

    @Column(name = "observacoes")
    private String observacoes;
}