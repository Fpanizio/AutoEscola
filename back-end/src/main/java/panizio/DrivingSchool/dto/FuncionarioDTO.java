package panizio.DrivingSchool.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import panizio.DrivingSchool.enums.*;
import panizio.DrivingSchool.validation.annotation.*;

import java.time.LocalDate;

@Data
public class FuncionarioDTO {

    // Dados Pessoais
    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 100, message = "O nome completo deve ter entre 3 e 100 caracteres")
    private String nomeCompleto;

    @ValidCPF
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF inválido. Formato esperado: 123.456.789-09")
    private String cpf;

    @NotBlank(message = "RG é obrigatório")
    @ValidRG
    private String rg;

    @NotNull(message = "Data de nascimento é obrigatória")
    @ValidAdult
    @ValidData(formato = "yyyy-MM-dd", message = "A data de nascimento deve estar no formato yyyy-MM-dd (ex: 1990-01-01)")
    private LocalDate dataNascimento;

    @NotNull(message = "Sexo é obrigatório")
    @ValidEnum(enumClass = SexoEnum.class, message = "O sexo deve ser um dos valores válidos: MASCULINO, FEMININO, NAO INFORMADO")
    private SexoEnum sexo;

    @NotNull(message = "Estado civil é obrigatório")
    @ValidEnum(enumClass = EstadoCivilEnum.class, message = "O estado civil deve ser um dos valores válidos: SOLTEIRO, CASADO, DIVORCIADO, VIUVO, SEPARADO, UNIAO_ESTAVEL")
    private EstadoCivilEnum estadoCivil;

    @NotBlank(message = "Nacionalidade é obrigatória")
    @Size(min = 3, max = 50, message = "A nacionalidade deve ter entre 3 e 50 caracteres")
    private String nacionalidade;

    @NotBlank(message = "Naturalidade é obrigatória")
    @Size(min = 3, max = 50, message = "A naturalidade deve ter entre 3 e 50 caracteres")
    private String naturalidade;

    // Contato
    @NotBlank(message = "Logradouro é obrigatório")
    @Size(min = 3, max = 100, message = "O logradouro deve ter entre 3 e 100 caracteres")
    private String logradouro;

    @NotBlank(message = "Número do endereço é obrigatório")
    @Size(min = 1, max = 10, message = "O número do endereço deve ter entre 1 e 10 caracteres")
    private String numeroEndereco;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(min = 3, max = 50, message = "O bairro deve ter entre 3 e 50 caracteres")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(min = 3, max = 50, message = "A cidade deve ter entre 3 e 50 caracteres")
    private String cidade;

    @ValidCEP
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP inválido. Formato esperado: 12345-678")
    private String cep;

    @Size(max = 50, message = "O complemento deve ter no máximo 50 caracteres")
    private String complemento;

    @NotBlank(message = "Telefone é obrigatório")
    @ValidTelefone(message = "Telefone inválido. Formato esperado: +55 (11) 12345-6789")
    private String telefone;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    // Profissionais
    @NotNull(message = "Escolaridade é obrigatória")
    @ValidEnum(enumClass = GrauEscolaridadeEnum.class, message = "O grau de escolaridade deve ser um dos valores válidos: ANALFABETO, ENSINO_FUNDAMENTAL_INCOMPLETO, ENSINO_FUNDAMENTAL_COMPLETO, ENSINO_MEDIO_INCOMPLETO, ENSINO_MEDIO_COMPLETO, ENSINO_SUPERIOR_INCOMPLETO, ENSINO_SUPERIOR_COMPLETO")
    private GrauEscolaridadeEnum escolaridade;

    private CategoriaCnhEnum categoriaCnh;

    @Pattern(regexp = "^\\d{11}$", message = "Número da CNH inválido. Deve conter 11 dígitos")
    private String numeroCnh;

    @Future(message = "A validade da CNH deve ser uma data futura")
    private LocalDate validadeCnh;

    // Contato de Emergência
    @NotBlank(message = "Nome do contato de emergência é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do contato de emergência deve ter entre 3 e 100 caracteres")
    private String contatoEmergenciaNome;

    @NotBlank(message = "Parentesco do contato de emergência é obrigatório")
    @Size(min = 3, max = 20, message = "O parentesco do contato de emergência deve ter entre 3 e 20 caracteres")
    private String contatoEmergenciaParentesco;

    @NotBlank(message = "Telefone do contato de emergência é obrigatório")
    @ValidTelefone(message = "Telefone do contato de emergência inválido. Formato esperado: +55 (11) 12345-6789")
    private String contatoEmergenciaTelefone;

    // Documentação
    @NotBlank(message = "PIS/PASEP é obrigatório")
    @Pattern(regexp = "^\\d{3}\\.\\d{5}\\.\\d{2}-\\d{1}$", message = "PIS/PASEP inválido. Formato esperado: 123.45678.90-1")
    private String pisPasep;

    @Size(max = 18, message = "A CTPS deve ter no máximo 18 caracteres")
    private String ctps;

    @ValidTituloEleitor
    private String tituloEleitor;

    @NotNull(message = "Órgão emissor é obrigatório")
    @ValidEnum(enumClass = OrgaoEmissorEnum.class, message = "O órgão emissor deve ser um dos valores válidos: SSP, DETRAN, PF, PM, CNT, IFP, IPF, MA, MRE")
    private OrgaoEmissorEnum orgaoEmissor;

    @NotNull(message = "UF emissora é obrigatória")
    @ValidEnum(enumClass = UfEnum.class, message = "A UF emissora deve ser um dos valores válidos: AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO")
    private UfEnum ufEmissor;

    private String observacoes;
}