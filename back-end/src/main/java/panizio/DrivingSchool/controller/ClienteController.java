package panizio.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import panizio.DrivingSchool.service.ClienteService;
import panizio.DrivingSchool.dto.ClienteDTO;
import panizio.DrivingSchool.mapper.ClienteMapper;
import panizio.DrivingSchool.model.ClienteModel;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ClienteMapper clienteMapper;

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> getAllClients() {
    List<ClienteModel> models = clienteService.getAllClients();
    List<ClienteDTO> dtos = models.stream()
        .map(clienteMapper::toDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{cpf}")
  public ResponseEntity<ClienteDTO> getByCpf(@PathVariable String cpf) {
    ClienteModel model = clienteService.getClientByCpf(cpf);
    return ResponseEntity.ok(clienteMapper.toDTO(model));
  }

  @PostMapping
  public ResponseEntity<ClienteDTO> createClient(
      @Valid @RequestBody ClienteDTO clienteDTO) {
    ClienteModel model = clienteMapper.toModel(clienteDTO);
    ClienteModel saveModel = clienteService.postClients(model);
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.toDTO(saveModel));
  }

  @PutMapping("/{cpf}")
  public ResponseEntity<ClienteDTO> updateClient(@PathVariable String cpf,
      @Valid @RequestBody ClienteDTO clienteDTO) {
    ClienteModel model = clienteMapper.toModel(clienteDTO);
    ClienteModel updateModel = clienteService.updateClient(cpf, model);
    return ResponseEntity.ok(clienteMapper.toDTO(updateModel));
  }

  @DeleteMapping("/{cpf}")
  public ResponseEntity<?> deleteClient(@PathVariable String cpf) {
    String message = clienteService.deleteClients(cpf);
    return ResponseEntity.ok(message);
  }
}