package panizio.DrivingSchool.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import panizio.DrivingSchool.enums.*;
import panizio.DrivingSchool.validation.annotation.ValidCEP;
import panizio.DrivingSchool.validation.annotation.ValidCPF;
import panizio.DrivingSchool.validation.annotation.ValidRG;
import panizio.DrivingSchool.validation.annotation.ValidTituloEleitor;

import java.time.LocalDate;

@Data
public class FuncionarioDTO {

    // Dados Pessoais
    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 100)
    private String nomeCompleto;

    @ValidCPF
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")
    private String cpf;

    @NotBlank
    @ValidRG
    private String rg;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private SexoEnum sexo;

    @NotNull
    private EstadoCivilEnum estadoCivil;

    @NotBlank
    @Size(min = 3, max = 50)
    private String nacionalidade;

    @NotBlank
    @Size(min = 3, max = 50)
    private String naturalidade;

    // Contato
    @NotBlank
    @Size(min = 3, max = 100)
    private String logradouro;

    @NotBlank
    @Size(min = 1, max = 10)
    private String numeroEndereco;

    @NotBlank
    @Size(min = 3, max = 50)
    private String bairro;

    @NotBlank
    @Size(min = 3, max = 50)
    private String cidade;

    @ValidCEP
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    private String cep;

    @Size(max = 50)
    private String complemento;

    @NotBlank
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$")
    private String telefone;

    @NotBlank
    @Email
    private String email;

    // Profissionais
    @NotNull
    private GrauEscolaridadeEnum escolaridade;

    private CategoriaCnhEnum categoriaCnh;

    @Pattern(regexp = "^\\d{11}$")
    private String numeroCnh;

    @Future
    private LocalDate validadeCnh;

    // Contato de Emergência
    @NotBlank
    @Size(min = 3, max = 100)
    private String contatoEmergenciaNome;

    @NotBlank
    @Size(min = 3, max = 20)
    private String contatoEmergenciaParentesco;

    @NotBlank
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$")
    private String contatoEmergenciaTelefone;

    // Documentação
    @NotBlank
    @Pattern(regexp = "^\\d{3}\\.\\d{5}\\.\\d{2}-\\d{1}$")
    private String pisPasep;

    @Size(max = 18)
    private String ctps;

    @ValidTituloEleitor
    private String tituloEleitor;

    @NotNull
    private OrgaoEmissorEnum orgaoEmissor;

    @NotNull
    private UfEnum ufEmissor;

    private String observacoes;
}