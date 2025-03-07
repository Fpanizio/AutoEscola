package panizio.DrivingSchool.controller;

import panizio.DrivingSchool.model.FuncionarioModel;
import panizio.DrivingSchool.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioModel> getAllEmployees() {
        return funcionarioService.getAllEmployees();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioModel> getAllEmployeesByCpf(@Valid @PathVariable String cpf) {
        FuncionarioModel Funcionario = funcionarioService.getAllEmployeesByCpf(cpf);
        if (Funcionario != null) {
            return new ResponseEntity<>(Funcionario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FuncionarioModel> postEmloylees(@Valid @RequestBody FuncionarioModel funcionario) {
        FuncionarioModel novoFuncionario = funcionarioService.postEmloylees(funcionario);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<FuncionarioModel> updateFuncionario(@PathVariable String cpf,
            @RequestBody FuncionarioModel funcionarioAtualizado) {
        FuncionarioModel funcionario = funcionarioService.UpdateEmployees(cpf, funcionarioAtualizado);
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Map<String, String>> deleteEmploylees(@PathVariable String cpf) {
        String mensagem = funcionarioService.deleteEmploylees(cpf); // Chama o serviço e obtém a mensagem

        // Cria um JSON com a mensagem de sucesso
        Map<String, String> response = new HashMap<>();
        response.put("message", mensagem);

        return ResponseEntity.ok(response); // Retorna o JSON com status 200 OK
    }
}