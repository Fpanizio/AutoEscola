package panizio.DrivingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import panizio.DrivingSchool.dto.OfertaDTO;
import panizio.DrivingSchool.mapper.OfertaMapper;
import panizio.DrivingSchool.model.OfertaModel;
import panizio.DrivingSchool.service.OfertasService;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasController {

    @Autowired
    private OfertasService ofertasService;

    @Autowired
    private OfertaMapper ofertaMapper;

    @GetMapping
    public ResponseEntity<List<OfertaDTO>> getAllOfertas() {
        List<OfertaModel> models = ofertasService.getAllOfertas();
        List<OfertaDTO> dtos = models.stream()
                .map(ofertaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaDTO> getById(@PathVariable Long id) {
        OfertaModel model = ofertasService.getOfertaById(id);
        return ResponseEntity.ok(ofertaMapper.toDTO(model));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<OfertaDTO> getByCategoria(@PathVariable String categoria) {
        OfertaModel model = ofertasService.getOfertaByCategoria(categoria);
        return ResponseEntity.ok(ofertaMapper.toDTO(model));
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<OfertaDTO> getByName(@PathVariable String name) {
        OfertaModel model = ofertasService.getOfertaByName(name);
        return ResponseEntity.ok(ofertaMapper.toDTO(model));
    }

    @PostMapping
    public ResponseEntity<OfertaDTO> createOferta(@Valid @RequestBody OfertaDTO ofertaDTO) {
        OfertaModel model = ofertaMapper.toModel(ofertaDTO);
        OfertaModel saveModel = ofertasService.postOferta(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaMapper.toDTO(saveModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfertaDTO> updateOferta(@PathVariable Long id, @Valid @RequestBody OfertaDTO ofertaDTO) {
        OfertaModel model = ofertaMapper.toModel(ofertaDTO);
        model.setId(id); // Garantir que estamos atualizando a oferta correta
        OfertaModel saveModel = ofertasService.updateOferta(id, model);
        return ResponseEntity.ok(ofertaMapper.toDTO(saveModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOferta(@PathVariable Long id) {
        ofertasService.deleteOferta(id);
        return ResponseEntity.ok("Oferta excluída com sucesso.");
    }
}
