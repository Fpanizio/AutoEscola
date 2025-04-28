package panizio.DrivingSchool.controller;

import panizio.DrivingSchool.dto.FuncionarioDTO;
import panizio.DrivingSchool.mapper.FuncionarioMapper;
import panizio.DrivingSchool.model.FuncionarioModel;
import panizio.DrivingSchool.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioMapper funcionarioMapper;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> getAllEmployees() {
        List<FuncionarioModel> models = funcionarioService.getAllEmployees();
        List<FuncionarioDTO> dtos = models.stream()
                .map(funcionarioMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioDTO> getByCpf(@PathVariable String cpf) {
        FuncionarioModel model = funcionarioService.getEmployeesByCpf(cpf);
        return ResponseEntity.ok(funcionarioMapper.toDTO(model));
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> createEmployee(
            @Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioModel model = funcionarioMapper.toModel(funcionarioDTO);
        FuncionarioModel savedModel = funcionarioService.postEmployees(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioMapper.toDTO(savedModel));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<FuncionarioDTO> updateEmployee(
            @PathVariable String cpf,
            @Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioModel model = funcionarioMapper.toModel(funcionarioDTO);
        FuncionarioModel updatedModel = funcionarioService.updateEmployees(cpf, model);
        return ResponseEntity.ok(funcionarioMapper.toDTO(updatedModel));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String cpf) {
        String mensagem = funcionarioService.deleteEmployees(cpf);
        return ResponseEntity.ok().body(Map.of("message", mensagem));
    }
}