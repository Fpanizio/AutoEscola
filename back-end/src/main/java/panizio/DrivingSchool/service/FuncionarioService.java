package panizio.DrivingSchool.service;

import panizio.DrivingSchool.exception.CpfInvalidURL;
import panizio.DrivingSchool.exception.DuplicateData;
import panizio.DrivingSchool.exception.NotFoundData;
import panizio.DrivingSchool.model.FuncionarioModel;
import panizio.DrivingSchool.repository.FuncionarioRepository;
import panizio.DrivingSchool.utils.CpfUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FuncionarioService {

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioService.class);

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<FuncionarioModel> getAllEmployees() {
        logger.info("Buscando todos os funcionários");
        return funcionarioRepository.findAll();
    }

    public FuncionarioModel getEmployeesByCpf(String cpf) {
        logger.info("Buscando funcionário pelo CPF: {}", cpf);
        validarCpf(cpf);

        return funcionarioRepository.findByCpf(CpfUtils.formatarCpf(cpf))
                .orElseThrow(() -> new NotFoundData("Funcionário com CPF " + cpf + " não encontrado"));
    }

    public String deleteEmployees(String cpf) {
        logger.info("Excluindo funcionário com CPF: {}", cpf);
        validarCpf(cpf);

        FuncionarioModel funcionario = funcionarioRepository.findByCpf(CpfUtils.formatarCpf(cpf))
                .orElseThrow(() -> new NotFoundData("Funcionário com CPF " + cpf + " não encontrado"));

        funcionarioRepository.delete(funcionario);
        return "Funcionário com CPF " + cpf + " excluído com sucesso.";
    }

    public FuncionarioModel postEmployees(FuncionarioModel funcionario) {
        logger.info("Criando novo funcionário: {}", funcionario.getNomeCompleto());
        validarDuplicidade(funcionario);
        return funcionarioRepository.save(funcionario);
    }

    public FuncionarioModel updateEmployees(String cpf, FuncionarioModel funcionarioUpdate) {
        logger.info("Atualizando funcionário com CPF: {}", cpf);
        validarCpf(cpf);

        FuncionarioModel funcionarioExist = funcionarioRepository.findByCpf(CpfUtils.formatarCpf(cpf))
                .orElseThrow(() -> new NotFoundData("Funcionário com CPF " + cpf + " não encontrado"));

        validarDuplicidade(funcionarioUpdate, funcionarioExist);

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(funcionarioUpdate, funcionarioExist);

        return funcionarioRepository.save(funcionarioExist);
    }

    private void validarCpf(String cpf) {
        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            throw new CpfInvalidURL("CPF inválido. O CPF deve conter exatamente 11 dígitos numéricos.");
        }
    }

    private void validarDuplicidade(FuncionarioModel funcionario) {
        Map<String, String> camposDuplicados = new HashMap<>();

        if (funcionarioRepository.findByCpf(funcionario.getCpf()).isPresent()) {
            camposDuplicados.put("cpf", "CPF já cadastrado: " + funcionario.getCpf());
        }

        if (funcionarioRepository.findByRg(funcionario.getRg()).isPresent()) {
            camposDuplicados.put("rg", "RG já cadastrado: " + funcionario.getRg());
        }

        if (funcionarioRepository.findByEmail(funcionario.getEmail()).isPresent()) {
            camposDuplicados.put("email", "Email já cadastrado: " + funcionario.getEmail());
        }

        if (funcionarioRepository.findByNumeroCnh(funcionario.getNumeroCnh()).isPresent()) {
            camposDuplicados.put("numeroCnh", "Número da CNH já cadastrado: " + funcionario.getNumeroCnh());
        }

        if (!camposDuplicados.isEmpty()) {
            throw new DuplicateData("Recursos duplicados encontrados", camposDuplicados);
        }
    }

    private void validarDuplicidade(FuncionarioModel funcionarioUpdate, FuncionarioModel funcionarioExist) {
        if (!funcionarioUpdate.getCpf().equals(funcionarioExist.getCpf()) &&
                funcionarioRepository.findByCpf(funcionarioUpdate.getCpf()).isPresent()) {
            throw new DuplicateData("CPF já cadastrado: " + funcionarioUpdate.getCpf());
        }

        if (!funcionarioUpdate.getRg().equals(funcionarioExist.getRg()) &&
                funcionarioRepository.findByRg(funcionarioUpdate.getRg()).isPresent()) {
            throw new DuplicateData("RG já cadastrado: " + funcionarioUpdate.getRg());
        }

        if (!funcionarioUpdate.getEmail().equals(funcionarioExist.getEmail()) &&
                funcionarioRepository.findByEmail(funcionarioUpdate.getEmail()).isPresent()) {
            throw new DuplicateData("Email já cadastrado: " + funcionarioUpdate.getEmail());
        }

        if (!funcionarioUpdate.getNumeroCnh().equals(funcionarioExist.getNumeroCnh()) &&
                funcionarioRepository.findByNumeroCnh(funcionarioUpdate.getNumeroCnh()).isPresent()) {
            throw new DuplicateData("Número da CNH já cadastrado: " + funcionarioUpdate.getNumeroCnh());
        }
    }
}