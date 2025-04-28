package panizio.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import panizio.DrivingSchool.dto.VeiculoDTO;
import panizio.DrivingSchool.mapper.VeiculoMapper;
import panizio.DrivingSchool.model.VeiculoModel;
import panizio.DrivingSchool.service.VeiculoService;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private VeiculoMapper veiculoMapper;

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> getAllVeiculos() {
        List<VeiculoModel> models = veiculoService.getAllVeiculos();
        List<VeiculoDTO> dtos = models.stream()
                .map(veiculoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<VeiculoDTO> getByPlaca(@PathVariable String placa) {
        VeiculoModel model = veiculoService.getVeiculoByPlaca(placa);
        return ResponseEntity.ok(veiculoMapper.toDTO(model));
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> createVeiculo(@Valid @RequestBody VeiculoDTO veiculoDTO) {
        VeiculoModel model = veiculoMapper.toModel(veiculoDTO);
        VeiculoModel saveModel = veiculoService.postVeiculo(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoMapper.toDTO(saveModel));
    }

    @PutMapping("/{placa}")
    public ResponseEntity<VeiculoDTO> updateVeiculo(@PathVariable String placa, @Valid @RequestBody VeiculoDTO veiculoDTO) {
        VeiculoModel model = veiculoMapper.toModel(veiculoDTO);
        VeiculoModel saveModel = veiculoService.updateVeiculo(placa, model);
        return ResponseEntity.ok(veiculoMapper.toDTO(saveModel));
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable String placa) {
        veiculoService.deleteVeiculo(placa);
        return ResponseEntity.noContent().build();
    }
}
