package panizio.DrivingSchool.controller;

import panizio.DrivingSchool.model.FuncionarioModel;
import panizio.DrivingSchool.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioModel> listarTodos() {
        return funcionarioService.listarTodos();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioModel> buscarUsuarioPorCpf(@Valid @PathVariable String cpf) {
        FuncionarioModel Funcionario = funcionarioService.buscarFuncionarioPorCpf(cpf);
        if (Funcionario != null) {
            return new ResponseEntity<>(Funcionario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FuncionarioModel> salvar(@Valid @RequestBody FuncionarioModel funcionario) {
        return ResponseEntity.ok(funcionarioService.salvar(funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        funcionarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}