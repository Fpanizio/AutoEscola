package panizio.DrivingSchool.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import panizio.DrivingSchool.enums.*;

import java.time.LocalDate;

@Data
public class FuncionarioDTO {

    // Dados Pessoais
    @NotBlank(message = "O nome completo é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nomeCompleto; // Alterado para camelCase

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "O CPF deve estar no formato XXX.XXX.XXX-XX.")
    private String cpf;

    @NotBlank(message = "O RG é obrigatório.")
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}$", message = "O RG deve estar no formato XX.XXX.XXX-X.")
    private String rg;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dataNascimento; // Alterado para camelCase

    @NotNull(message = "O sexo é obrigatório.")
    private SexoEnum sexo;

    @NotNull(message = "O estado civil é obrigatório.")
    private EstadoCivilEnum estadoCivil;

    @NotBlank(message = "A nacionalidade é obrigatória.")
    @Size(min = 3, max = 50, message = "A nacionalidade deve ter entre 3 e 50 caracteres.")
    private String nacionalidade;

    @NotBlank(message = "A naturalidade é obrigatória.")
    @Size(min = 3, max = 50, message = "A naturalidade deve ter entre 3 e 50 caracteres.")
    private String naturalidade;

    // Contato
    @NotBlank(message = "O logradouro é obrigatório.")
    @Size(min = 3, max = 100, message = "O logradouro deve ter entre 3 e 100 caracteres.")
    private String logradouro;

    @NotBlank(message = "O número do endereço é obrigatório.")
    @Size(min = 1, max = 10, message = "O número do endereço deve ter até 10 caracteres.")
    private String numeroEndereco; // Alterado para camelCase

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(min = 3, max = 50, message = "O bairro deve ter entre 3 e 50 caracteres.")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória.")
    @Size(min = 3, max = 50, message = "A cidade deve ter entre 3 e 50 caracteres.")
    private String cidade;

    @NotBlank(message = "A UF é obrigatória.")
    @Size(min = 2, max = 2, message = "A UF deve ter exatamente 2 caracteres.")
    private String uf;

    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "O CEP deve estar no formato XXXXX-XXX.")
    private String cep;

    @Size(max = 50, message = "O complemento deve ter até 50 caracteres.")
    private String complemento;

    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "O telefone deve estar no formato +XX (XX) XXXXX-XXXX.")
    private String telefone;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido (exemplo: nome@dominio.com).")
    private String email;

    @NotNull(message = "A escolaridade é obrigatória.")
    private GrauEscolaridadeEnum escolaridade;

    private CategoriaCNHEnum categoriaCnh;

    @Pattern(regexp = "^\\d{11}$", message = "A CNH deve conter exatamente 11 dígitos numéricos.")
    private String numeroCnh;

    @Future(message = "A validade da CNH deve ser uma data futura.")
    private LocalDate validadeCnh; // Alterado para camelCase

    // Contato de Emergência
    @NotBlank(message = "O nome do contato de emergência é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome do contato de emergência deve ter entre 3 e 100 caracteres.")
    private String contatoEmergenciaNome; // Alterado para camelCase

    @NotBlank(message = "O parentesco do contato de emergência é obrigatório.")
    @Size(min = 3, max = 20, message = "O parentesco deve ter entre 3 e 20 caracteres.")
    private String contatoEmergenciaParentesco; // Alterado para camelCase

    @NotBlank(message = "O telefone de emergência é obrigatório.")
    @Pattern(regexp = "^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "O telefone de emergência deve estar no formato +XX (XX) XXXXX-XXXX.")
    private String contatoEmergenciaTelefone; // Alterado para camelCase

    // Documentação
    @NotBlank(message = "O PIS/PASEP é obrigatório.")
    @Pattern(regexp = "^\\d{3}\\.\\d{5}\\.\\d{2}-\\d{1}$", message = "O PIS/PASEP deve estar no formato XXX.XXXXX.XX-X.")
    private String pisPasep; // Alterado para camelCase

    @Pattern(regexp = "^\\d{7}-\\d{2}\\.\\d{4}-\\d{2}$", message = "A CTPS deve estar no formato XXXXXXX-XX.XXXX-XX.")
    @Size(max = 18, message = "A CTPS deve ter no máximo 18 caracteres.")
    private String ctps;

    @Pattern(regexp = "^\\d{4} \\d{4} \\d{4}$", message = "O título de eleitor deve estar no formato XXXX XXXX XXXX.")
    @Size(max = 14, message = "O título de eleitor deve ter no máximo 14 caracteres.")
    private String tituloEleitor; // Alterado para camelCase

    private String observacoes;
}