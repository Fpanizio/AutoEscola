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

import panizio.DrivingSchool.dto.OfertaDTO;
import panizio.DrivingSchool.model.OfertaModel;
import panizio.DrivingSchool.service.OfertasService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasController {
  @Autowired
  private OfertasService ofertasService;

  @Autowired
  private ModelMapper modelMapper;

  private OfertaDTO convertToDTO(OfertaModel model) {
    return modelMapper.map(model, OfertaDTO.class);
  }

  private OfertaModel convertToModel(OfertaDTO dto) {
    OfertaModel model = modelMapper.map(dto, OfertaModel.class);
    model.setId(null);
    return model;
  }

  // Implementar os métodos do controller
  @GetMapping
  public ResponseEntity<List<OfertaDTO>> getAllOfertas() {
    List<OfertaModel> models = ofertasService.getAllOfertas();
    List<OfertaDTO> dtos = models.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OfertaDTO> getById(@PathVariable Long id) {
    OfertaModel model = ofertasService.getOfertaById(id);
    return ResponseEntity.ok(convertToDTO(model));
  }

  @GetMapping("/categoria/{categoria}")
  public ResponseEntity<OfertaDTO> getByCategoria(@PathVariable String categoria) {
    OfertaModel model = ofertasService.getOfertaByCategoria(categoria);
    return ResponseEntity.ok(convertToDTO(model));
  }

  @GetMapping("/byName/{name}")
  public ResponseEntity<OfertaDTO> getByName(@PathVariable String name) {
    OfertaModel model = ofertasService.getOfertaByName(name);
    return ResponseEntity.ok(convertToDTO(model));
  }

  @PostMapping
  public ResponseEntity<OfertaDTO> createOferta(@Valid @RequestBody OfertaDTO ofertaDTO) {
    OfertaModel model = convertToModel(ofertaDTO);
    OfertaModel saveModel = ofertasService.postOferta(model);
    return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(saveModel));
  }

  @PutMapping("/{id}")
  public ResponseEntity<OfertaDTO> updateOferta(@PathVariable Long id, @Valid @RequestBody OfertaDTO ofertaDTO) {
    OfertaModel model = convertToModel(ofertaDTO);
    model.setId(id); // Garantir que estamos atualizando a oferta correta
    OfertaModel saveModel = ofertasService.postOferta(model);
    return ResponseEntity.ok(convertToDTO(saveModel));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOferta(@PathVariable Long id) {
    ofertasService.deleteOferta(id);
    return ResponseEntity.noContent().build();
  }
}
