package panizio.DrivingSchool.service;

import panizio.DrivingSchool.model.FuncionarioModel;
import panizio.DrivingSchool.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioModel> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioModel buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public FuncionarioModel buscarFuncionarioPorCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
      }

    public void excluir(Long id) {
        funcionarioRepository.deleteById(id);
    }


    public FuncionarioModel salvar(FuncionarioModel funcionario) {
        // Salva o funcionário
        return funcionarioRepository.save(funcionario);
    }
}