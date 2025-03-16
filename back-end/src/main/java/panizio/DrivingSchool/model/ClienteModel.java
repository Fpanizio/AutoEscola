package panizio.DrivingSchool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import panizio.DrivingSchool.enums.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "clientes")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Dados Pessoais
    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "naturalidade", nullable = false, length = 50)
    private String naturalidade;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "escolaridade", length = 50)
    private GrauEscolaridadeEnum escolaridade;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", nullable = false)
    private EstadoCivilEnum estadoCivil;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private SexoEnum sexo;

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "nome_pai", nullable = false, length = 100)
    private String nomePai;

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "nome_mae", nullable = false, length = 100)
    private String nomeMae;

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
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // Documentação
    @NotBlank
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")
    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;

    @NotBlank
    @Column(name = "rg", unique = true, nullable = false)
    private String rg;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "orgao_emissor", nullable = false)
    private OrgaoEmissorEnum orgaoEmissor;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "uf_emissor", nullable = false)
    private UfEnum ufEmissor;

    // Emergência
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

    @Column(name = "observacoes")
    private String observacoes;
}