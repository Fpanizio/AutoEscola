package panizio.DrivingSchool.validation;

import panizio.DrivingSchool.model.ClienteModel;
import panizio.DrivingSchool.model.FuncionarioModel;

import java.util.Objects;

public class EnumValidator {

    /**
     * Valida se um campo baseado em enum não é nulo.
     *
     * @param valor     O valor do enum a ser validado.
     * @param nomeCampo O nome do campo (para mensagens de erro).
     * @throws IllegalArgumentException Se o valor for nulo.
     */
    private static void validarEnumNaoNulo(Object valor, String nomeCampo) {
        if (Objects.isNull(valor)) {
            throw new IllegalArgumentException("O campo '" + nomeCampo + "' não pode ser nulo.");
        }
    }

    /**
     * Valida todos os campos baseados em enum de um FuncionarioModel.
     *
     * @param funcionario O FuncionarioModel a ser validado.
     * @throws IllegalArgumentException Se algum campo for nulo.
     */
    public static void validarEnumsFuncionario(FuncionarioModel funcionario) {
        validarEnumNaoNulo(funcionario.getSexo(), "sexo");
        validarEnumNaoNulo(funcionario.getEstadoCivil(), "estadoCivil");
        validarEnumNaoNulo(funcionario.getEscolaridade(), "escolaridade");
        validarEnumNaoNulo(funcionario.getUfEmissor(), "ufEmissor");
        validarEnumNaoNulo(funcionario.getOrgaoEmissor(), "orgaoEmissor");
    }

    /**
     * Valida todos os campos baseados em enum de um ClienteModel.
     *
     * @param cliente O ClienteModel a ser validado.
     * @throws IllegalArgumentException Se algum campo for nulo.
     */
    public static void validarEnumsCliente(ClienteModel cliente) {
        validarEnumNaoNulo(cliente.getSexo(), "sexo");
        validarEnumNaoNulo(cliente.getEstadoCivil(), "estadoCivil");
        validarEnumNaoNulo(cliente.getEscolaridade(), "escolaridade");
        validarEnumNaoNulo(cliente.getUfEmissor(), "ufEmissor");
        validarEnumNaoNulo(cliente.getOrgaoEmissor(), "orgaoEmissor");
    }
}