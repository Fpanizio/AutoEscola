package panizio.DrivingSchool.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import panizio.DrivingSchool.enums.*;
import panizio.DrivingSchool.validation.annotation.ValidAdult;
import panizio.DrivingSchool.validation.annotation.ValidCEP;
import panizio.DrivingSchool.validation.annotation.ValidCPF;
import panizio.DrivingSchool.validation.annotation.ValidData;
import panizio.DrivingSchool.validation.annotation.ValidRG;
import panizio.DrivingSchool.validation.annotation.ValidTelefone;

import java.time.LocalDate;

@Data
public class ClienteDTO {

    // Dados Pessoais
    @NotBlank
    @Size(min = 3, max = 100)
    private String nomeCompleto;

    @NotNull
    @ValidAdult
    @ValidData(formato = "yyyy-MM-dd", message = "A data de nascimento deve estar no formato yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotBlank
    @Size(min = 3, max = 50)
    private String naturalidade;

    @NotBlank
    @Size(min = 3, max = 50)
    private String nacionalidade;

    @NotNull
    private GrauEscolaridadeEnum escolaridade;

    @NotNull
    private EstadoCivilEnum estadoCivil;

    @NotNull
    private SexoEnum sexo;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nomePai;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nomeMae;

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
    @ValidTelefone
    private String telefone;

    @NotBlank
    @Email
    private String email;

    // Documentação
    @ValidCPF
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF inválido. Formato esperado: 123.456.789-09")
    private String cpf;

    @NotBlank
    @ValidRG
    private String rg;

    @NotNull
    private OrgaoEmissorEnum orgaoEmissor;

    @NotNull
    private UfEnum ufEmissor;

    // Emergência
    @NotBlank
    @Size(min = 3, max = 100)
    private String contatoEmergenciaNome;

    @NotBlank
    @Size(min = 3, max = 20)
    private String contatoEmergenciaParentesco;

    @NotBlank
    @ValidTelefone
    private String contatoEmergenciaTelefone;

    private String observacoes;
}