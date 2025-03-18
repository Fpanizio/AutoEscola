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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public FuncionarioModel getEmployeesByCpf(String cpf) {
        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            throw new CpfInvalidURL("CPF inválido. O CPF deve conter exatamente 11 dígitos numéricos.");
        }

        return funcionarioRepository.findByCpf(CpfUtils.formatarCpf(cpf))
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public String deleteEmploylees(String cpf) {
        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            throw new CpfInvalidURL("CPF inválido. O CPF deve conter exatamente 11 dígitos numéricos.");
        }

        FuncionarioModel funcionario = funcionarioRepository.findByCpf(CpfUtils.formatarCpf(cpf))
                .orElseThrow(() -> new NotFoundData("Funcionario não encontrado"));

        funcionarioRepository.delete(funcionario);
        return "Funcionario com CPF " + CpfUtils.formatarCpf(cpf) + " excluído com sucesso";
    }

    public FuncionarioModel postEmloylees(FuncionarioModel funcionario) {
        Map<String, String> camposDuplicados = new HashMap<>();

        // Verificar se o CPF já existe
        if (funcionarioRepository.findByCpf(funcionario.getCpf()).isPresent()) {
            camposDuplicados.put("cpf", "CPF já cadastrado: " + funcionario.getCpf());
        }

        // Verificar se o RG já existe
        if (funcionarioRepository.findByRg(funcionario.getRg()).isPresent()) {
            camposDuplicados.put("rg", "RG já cadastrado: " + funcionario.getRg());
        }

        // Verificar se o Email já existe
        if (funcionarioRepository.findByEmail(funcionario.getEmail()).isPresent()) {
            camposDuplicados.put("email", "Email já cadastrado: " + funcionario.getEmail());
        }

        // Verificar se o Numero da CNH já existe
        if (funcionarioRepository.findByNumeroCnh(funcionario.getNumeroCnh()).isPresent()) {
            camposDuplicados.put("Numero da CNH", "Numero da CNH já cadastrado: " + funcionario.getNumeroCnh());
        }

        // Se houver campos duplicados, lançar a exceção
        if (!camposDuplicados.isEmpty()) {
            throw new DuplicateData("Recursos duplicados encontrados", camposDuplicados);
        }

        return funcionarioRepository.save(funcionario);
    }

    public FuncionarioModel UpdateEmployees(String cpf, FuncionarioModel funcionarioUpdate) {
        String formatCPF = CpfUtils.formatarCpf(cpf);

        FuncionarioModel funcionarioExist = funcionarioRepository.findByCpf(formatCPF)
                .orElseThrow(() -> new NotFoundData("funcionário com CPF " + formatCPF + " não encontrado"));

        // Verifica e atualiza CPF
        if (funcionarioUpdate.getCpf() != null && !funcionarioExist.getCpf().equals(funcionarioUpdate.getCpf())) {
            if (funcionarioRepository.findByCpf(funcionarioUpdate.getCpf()).isPresent()) {
                throw new DuplicateData("Já existe um funcionário com o CPF informado",
                        Map.of("cpf", funcionarioUpdate.getCpf()));
            }
            funcionarioExist.setCpf(funcionarioUpdate.getCpf());
        }

        // Verifica e atualiza RG
        if (funcionarioUpdate.getRg() != null && !funcionarioExist.getRg().equals(funcionarioUpdate.getRg())) {
            if (funcionarioRepository.findByRg(funcionarioUpdate.getRg()).isPresent()) {
                throw new DuplicateData("Já existe um funcionário com o RG informado",
                        Map.of("rg", funcionarioUpdate.getRg()));
            }
            funcionarioExist.setRg(funcionarioUpdate.getRg());
        }

        // Verifica e atualiza Email
        if (funcionarioUpdate.getEmail() != null && !funcionarioExist.getEmail().equals(funcionarioUpdate.getEmail())) {
            if (funcionarioRepository.findByEmail(funcionarioUpdate.getEmail()).isPresent()) {
                throw new DuplicateData("Já existe um funcionário com o Email informado",
                        Map.of("email", funcionarioUpdate.getEmail()));
            }
            funcionarioExist.setEmail(funcionarioUpdate.getEmail());
        }

        // Verifica e atualiza Email
        if (funcionarioUpdate.getNumeroCnh() != null
                && !funcionarioExist.getNumeroCnh().equals(funcionarioUpdate.getNumeroCnh())) {
            if (funcionarioRepository.findByNumeroCnh(funcionarioUpdate.getNumeroCnh()).isPresent()) {
                throw new DuplicateData("Já existe um funcionário com o numero da CNH informado",
                        Map.of("Numero da CNH", funcionarioUpdate.getNumeroCnh()));
            }
            funcionarioExist.setEmail(funcionarioUpdate.getEmail());
        }

        // Configuração ModelMapper para ignorar campos únicos
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.typeMap(FuncionarioModel.class, FuncionarioModel.class)
                .addMappings(mapper -> {
                    mapper.skip(FuncionarioModel::setCpf);
                    mapper.skip(FuncionarioModel::setRg);
                    mapper.skip(FuncionarioModel::setEmail);
                    mapper.skip(FuncionarioModel::setNumeroCnh);
                });

        // Aplica as atualizações
        modelMapper.map(funcionarioUpdate, funcionarioExist);

        return funcionarioRepository.save(funcionarioExist);
    }
}