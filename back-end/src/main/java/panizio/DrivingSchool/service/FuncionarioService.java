package panizio.DrivingSchool.service;

import panizio.DrivingSchool.exception.notFoundEmployleesException;
import panizio.DrivingSchool.model.FuncionarioModel;
import panizio.DrivingSchool.repository.FuncionarioRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioModel> getAllEmployees() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioModel getAllEmployeesByCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public String deleteEmploylees(String cpf) {
        Optional<FuncionarioModel> funcionarioOpt = funcionarioRepository.findByCpf(cpf);

        if (funcionarioOpt.isPresent()) {
            funcionarioRepository.deleteById(funcionarioOpt.get().getId());
            return "Funcionário com CPF " + cpf + " excluído com sucesso.";
        } else {
            throw new notFoundEmployleesException(cpf);
        }
    }

    public FuncionarioModel postEmloylees(FuncionarioModel funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public FuncionarioModel UpdateEmployees(String cpf, FuncionarioModel funcionarioAtualizado) {
        Optional<FuncionarioModel> funcionarioExistenteOpt = funcionarioRepository.findByCpf(cpf);

        if (funcionarioExistenteOpt.isPresent()) {
            FuncionarioModel funcionarioExistente = funcionarioExistenteOpt.get();

            // Copia as propriedades de funcionarioAtualizado para funcionarioExistente,
            // ignorando campos sensíveis
            BeanUtils.copyProperties(funcionarioAtualizado, funcionarioExistente, "id", "cpf", "data_cadastro");

            return funcionarioRepository.save(funcionarioExistente);
        } else {
            throw new notFoundEmployleesException(cpf);
        }
    }
}