package panizio.DrivingSchool.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import panizio.DrivingSchool.dto.VeiculoDTO;
import panizio.DrivingSchool.model.VeiculoModel;
import panizio.DrivingSchool.service.VeiculoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
  @Autowired
  private VeiculoService veiculoService;

  @Autowired
  private ModelMapper modelMapper;

  private VeiculoDTO convertToDTO(VeiculoModel model) {
    return modelMapper.map(model, VeiculoDTO.class);
  }

  private VeiculoModel convertToModel(VeiculoDTO dto) {
    VeiculoModel model = modelMapper.map(dto, VeiculoModel.class);
    model.setId(null);
    return model;
  }

  @GetMapping()
  public ResponseEntity<List<VeiculoDTO>> getAllVeiculos() {
    List<VeiculoModel> models = veiculoService.getAllVeiculos();
    List<VeiculoDTO> dtos = models.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{placa}")
  public ResponseEntity<VeiculoDTO> getByPlaca(@PathVariable String placa) {
    VeiculoModel model = veiculoService.getVeiculoByPlaca(placa);
    return ResponseEntity.ok(convertToDTO(model));
  }

  @PostMapping
  public ResponseEntity<VeiculoDTO> createVeiculo(@Valid @RequestBody VeiculoDTO veiculoDTO) {
    VeiculoModel model = convertToModel(veiculoDTO);
    VeiculoModel saveModel = veiculoService.postClients(model);
    return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(saveModel));
  }

  @PutMapping("/{placa}")
  public ResponseEntity<VeiculoDTO> updateVeiculo(@PathVariable String placa,
      @Valid @RequestBody VeiculoDTO veiculoDTO) {
    VeiculoModel model = convertToModel(veiculoDTO);
    VeiculoModel saveModel = veiculoService.updateVeiculo(placa, model);
    return ResponseEntity.ok(convertToDTO(saveModel));
  }

  @DeleteMapping("/{placa}")
  public ResponseEntity<?> deleteVeiculo(@PathVariable String placa) {
    String message = veiculoService.deleteVeiculo(placa);
    return ResponseEntity.ok(message);
  }

}
