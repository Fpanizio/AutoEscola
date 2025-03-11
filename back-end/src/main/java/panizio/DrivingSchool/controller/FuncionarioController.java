package panizio.DrivingSchool.controller;

import panizio.DrivingSchool.dto.FuncionarioDTO;
import panizio.DrivingSchool.exception.CpfAlreadyInUseException;
import panizio.DrivingSchool.exception.RgAlreadyInUseException;
import panizio.DrivingSchool.exception.NumeroCnhAlreadyInUseException;
import panizio.DrivingSchool.exception.notFoundEmployleesException;
import panizio.DrivingSchool.model.FuncionarioModel;
import panizio.DrivingSchool.service.FuncionarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ModelMapper modelMapper;

    // Converte Model para DTO
    private FuncionarioDTO convertToDTO(FuncionarioModel model) {
        return modelMapper.map(model, FuncionarioDTO.class);
    }

    // Converte DTO para Model (ignora id e data_cadastro)
    private FuncionarioModel convertToModel(FuncionarioDTO dto) {
        FuncionarioModel model = modelMapper.map(dto, FuncionarioModel.class);
        model.setId(null); // Garante que o ID seja gerado automaticamente
        model.setDataCadastro(LocalDate.now()); // Define a data de cadastro
        return model;
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> getAllEmployees() {
        List<FuncionarioModel> models = funcionarioService.getAllEmployees();
        List<FuncionarioDTO> dtos = models.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioDTO> getByCpf(@PathVariable String cpf) {
        FuncionarioModel model = funcionarioService.getAllEmployeesByCpf(cpf);
        return ResponseEntity.ok(convertToDTO(model));
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> createEmployee(
            @Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioModel model = convertToModel(funcionarioDTO);
        FuncionarioModel savedModel = funcionarioService.postEmloylees(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedModel));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<FuncionarioDTO> updateEmployee(
            @PathVariable String cpf,
            @Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioModel model = convertToModel(funcionarioDTO);
        FuncionarioModel updatedModel = funcionarioService.UpdateEmployees(cpf, model);
        return ResponseEntity.ok(convertToDTO(updatedModel));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String cpf) {
        String mensagem = funcionarioService.deleteEmploylees(cpf);
        return ResponseEntity.ok().body(Map.of("message", mensagem));
    }

    @ExceptionHandler(CpfAlreadyInUseException.class)
    public ResponseEntity<?> handleCpfAlreadyInUseException(CpfAlreadyInUseException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(RgAlreadyInUseException.class)
    public ResponseEntity<?> handleRgAlreadyInUseException(RgAlreadyInUseException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(NumeroCnhAlreadyInUseException.class)
    public ResponseEntity<?> handleNumeroCnhAlreadyInUseException(NumeroCnhAlreadyInUseException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(notFoundEmployleesException.class)
    public ResponseEntity<?> handleNotFoundEmployleesException(notFoundEmployleesException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", ex.getMessage()));
    }
}