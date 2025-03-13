package panizio.DrivingSchool.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import panizio.DrivingSchool.enums.*;
import java.time.LocalDate;

@Data
public class ClienteDTO {

    // Dados Pessoais
    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nomeCompleto;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @NotBlank(message = "Naturalidade é obrigatória")
    @Size(min = 3, max = 50, message = "Naturalidade deve ter entre 3 e 50 caracteres")
    private String naturalidade;

    @NotBlank(message = "Nacionalidade é obrigatória")
    @Size(min = 3, max = 50, message = "Nacionalidade deve ter entre 3 e 50 caracteres")
    private String nacionalidade;

    @NotNull(message = "Escolaridade é obrigatória")
    private GrauEscolaridadeEnum escolaridade;

    @NotNull(message = "Estado civil é obrigatório")
    private EstadoCivilEnum estadoCivil;

    @NotNull(message = "Sexo é obrigatório")
    private SexoEnum sexo;

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nomePai;

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nomeMae;

    // Contato
    @NotBlank(message = "Logradouro é obrigatório")
    @Size(min = 3, max = 100, message = "Logradouro deve ter entre 3 e 100 caracteres")
    private String logradouro;

    @NotBlank(message = "Número do endereço é obrigatório")
    @Size(min = 1, max = 10, message = "Número deve ter até 10 caracteres")
    private String numeroEndereco;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(min = 3, max = 50, message = "Bairro deve ter entre 3 e 50 caracteres")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(min = 3, max = 50, message = "Cidade deve ter entre 3 e 50 caracteres")
    private String cidade;

    @NotBlank(message = "UF é obrigatória")
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres")
    private String uf;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Formato de CEP inválido (XXXXX-XXX)")
    private String cep;

    @Size(max = 50, message = "Complemento deve ter até 50 caracteres")
    private String complemento;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Formato de telefone inválido (+XX (XX) XXXXX-XXXX)")
    private String telefone;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    // Documentação
    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Formato de CPF inválido (XXX.XXX.XXX-XX)")
    private String cpf;

    @NotBlank(message = "RG é obrigatório")
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}$", message = "Formato de RG inválido (XX.XXX.XXX-X)")
    private String rg;

    @NotBlank(message = "Órgão emissor é obrigatório")
    @Size(min = 3, max = 50, message = "Órgão emissor deve ter entre 3 e 50 caracteres")
    private String orgaoEmissor;

    @NotBlank(message = "UF do órgão emissor é obrigatório")
    @Size(min = 2, max = 2, message = "UF do órgão emissor deve ter 2 caracteres")
    private String ufEmissor;

    // Contato de Emergência
    @NotBlank(message = "Nome do contato de emergência é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String contatoEmergenciaNome;

    @NotBlank(message = "Parentesco do contato de emergência é obrigatório")
    @Size(min = 3, max = 20, message = "Parentesco deve ter entre 3 e 20 caracteres")
    private String contatoEmergenciaParentesco;

    @NotBlank(message = "Telefone de emergência é obrigatório")
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Formato inválido (+XX (XX) XXXXX-XXXX)")
    private String contatoEmergenciaTelefone;

    private String observacoes;
}