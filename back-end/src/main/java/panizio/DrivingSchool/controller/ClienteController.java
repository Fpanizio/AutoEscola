package panizio.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import panizio.DrivingSchool.service.ClienteService;
import panizio.DrivingSchool.dto.ClienteDTO;
import panizio.DrivingSchool.model.ClienteModel;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ModelMapper modelMapper;

  private ClienteDTO convertToDTO(ClienteModel model) {
    return modelMapper.map(model, ClienteDTO.class);
  }

  private ClienteModel convertToModel(ClienteDTO dto) {
    ClienteModel model = modelMapper.map(dto, ClienteModel.class);
    model.setId(null);
    return model;
  }

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> getAllClients() {
    List<ClienteModel> models = clienteService.getAllClients();
    List<ClienteDTO> dtos = models.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{cpf}")
  public ResponseEntity<ClienteDTO> getByCpf(@PathVariable String cpf) {
    ClienteModel model = clienteService.getClientByCpf(cpf);
    return ResponseEntity.ok(convertToDTO(model));
  }

  @PostMapping
  public ResponseEntity<ClienteDTO> createClient(
      @Valid @RequestBody ClienteDTO clienteDTO) {
    ClienteModel model = convertToModel(clienteDTO);
    ClienteModel saveModel = clienteService.postClients(model);
    return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(saveModel));
  }

  @PutMapping("/{cpf}")
  public ResponseEntity<ClienteDTO> updateClient(@PathVariable String cpf,
      @Valid @RequestBody ClienteDTO clienteDTO) {
    ClienteModel model = convertToModel(clienteDTO);
    ClienteModel updateModel = clienteService.updateClient(cpf, model);
    return ResponseEntity.ok(convertToDTO(updateModel));
  }

  @DeleteMapping("/{cpf}")
  public ResponseEntity<?> deleteClient(@PathVariable String cpf) {
    String message = clienteService.deleteClients(cpf);
    return ResponseEntity.ok(message);
  }
}
