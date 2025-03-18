package panizio.DrivingSchool.service;

import panizio.DrivingSchool.exception.NotFoundData;
import panizio.DrivingSchool.model.FuncionarioModel;
import panizio.DrivingSchool.repository.FuncionarioRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, ModelMapper modelMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.modelMapper = modelMapper;
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
            throw new NotFoundData(cpf);
        }
    }

    public FuncionarioModel postEmloylees(FuncionarioModel funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public FuncionarioModel UpdateEmployees(String cpf, FuncionarioModel funcionarioAtualizado) {
        FuncionarioModel funcionarioExistente = funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundData(cpf));

        // Verificar e atualizar CPF
        if (funcionarioAtualizado.getCpf() != null
                && !funcionarioExistente.getCpf().equals(funcionarioAtualizado.getCpf())) {
            funcionarioRepository.findByCpf(funcionarioAtualizado.getCpf())
                    .ifPresent(existingFuncionario -> {
                        if (!existingFuncionario.getId().equals(funcionarioExistente.getId())) {
                            throw new NotFoundData("CPF já está em uso por outro funcionário");
                        }
                    });
            funcionarioExistente.setCpf(funcionarioAtualizado.getCpf());
        }

        // Verificar e atualizar RG
        if (funcionarioAtualizado.getRg() != null
                && !funcionarioExistente.getRg().equals(funcionarioAtualizado.getRg())) {
            funcionarioRepository.findByRg(funcionarioAtualizado.getRg())
                    .ifPresent(existingFuncionario -> {
                        if (!existingFuncionario.getId().equals(funcionarioExistente.getId())) {
                            throw new NotFoundData("RG já está em uso por outro funcionário");
                        }
                    });
            funcionarioExistente.setRg(funcionarioAtualizado.getRg());
        }

        // Verificar e atualizar CNH
        if (funcionarioAtualizado.getNumeroCnh() != null
                && !funcionarioExistente.getNumeroCnh().equals(funcionarioAtualizado.getNumeroCnh())) {
            funcionarioRepository.findByNumeroCnh(funcionarioAtualizado.getNumeroCnh())
                    .ifPresent(existingFuncionario -> {
                        if (!existingFuncionario.getId().equals(funcionarioExistente.getId())) {
                            throw new NotFoundData(
                                    "Número da CNH já está em uso por outro funcionário");
                        }
                    });
            funcionarioExistente.setNumeroCnh(funcionarioAtualizado.getNumeroCnh());
        }

        // Configurar ModelMapper para ignorar campos únicos
        modelMapper.getConfiguration().setSkipNullEnabled(true); // Opcional: não sobrescreve com null
        modelMapper.typeMap(FuncionarioModel.class, FuncionarioModel.class)
                .addMappings(mapper -> {
                    mapper.skip(FuncionarioModel::setCpf);
                    mapper.skip(FuncionarioModel::setRg);
                    mapper.skip(FuncionarioModel::setNumeroCnh);
                });

        // Mapear apenas os campos não únicos
        modelMapper.map(funcionarioAtualizado, funcionarioExistente);

        return funcionarioRepository.save(funcionarioExistente);
    }
}