package panizio.DrivingSchool.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import panizio.DrivingSchool.enums.CategoriaEnum;
import panizio.DrivingSchool.exception.DuplicateData;
import panizio.DrivingSchool.exception.NotFoundData;
import panizio.DrivingSchool.model.OfertaModel;
import panizio.DrivingSchool.repository.OfertaRepository;

@Service
public class OfertasService {

  @Autowired
  private OfertaRepository ofertaRepository;

  public OfertasService(OfertaRepository ofertaRepository) {
    this.ofertaRepository = ofertaRepository;
  }

  public List<OfertaModel> getAllOfertas() {
    return ofertaRepository.findAll();
  }

  public OfertaModel getOfertaById(Long id) {
    return ofertaRepository.findById(id)
        .orElseThrow(() -> new NotFoundData("Oferta com ID " + id + " não encontrada"));
  }

  public OfertaModel getOfertaByCategoria(String categoriaInput) {
    // Normaliza o input
    String categoriaNormalized = categoriaInput.trim().toUpperCase();

    try {
      CategoriaEnum categoria = CategoriaEnum.valueOf(categoriaNormalized);
      return ofertaRepository.findByCategoria(categoria)
          .orElseThrow(() -> new NotFoundData(
              String.format("Nenhuma oferta encontrada para categoria '%s'", categoriaInput)));
    } catch (IllegalArgumentException e) {
      throw new NotFoundData(
          String.format("Categoria '%s' inválida. Categorias válidas: %s",
              categoriaInput,
              Arrays.toString(CategoriaEnum.values())));
    }
  }

  public OfertaModel getOfertaByName(String name) {
    return ofertaRepository.findByName(name)
        .orElseThrow(() -> new NotFoundData("Oferta com nome " + name + " não encontrada"));
  }

  public OfertaModel postOferta(OfertaModel oferta) {
    Map<String, String> camposDuplicados = new HashMap<>();

    if (ofertaRepository.findByName(oferta.getName()).isPresent()) {
      camposDuplicados.put("Nome da oferta", "Nome da oferta já cadastrado: " + oferta.getName());
    }

    if (!camposDuplicados.isEmpty()) {
      throw new DuplicateData("Recursos duplicados encontrados", camposDuplicados);
    }

    return ofertaRepository.save(oferta);
  }

  public OfertaModel updateOferta(Long id, OfertaModel ofertaUpdate) {
    OfertaModel ofertaExist = ofertaRepository.findById(id)
        .orElseThrow(() -> new NotFoundData("Oferta com ID " + id + " não encontrada"));

    if (ofertaUpdate.getName() != null && !ofertaExist.getName().equals(ofertaUpdate.getName())) {
      if (ofertaRepository.findByName(ofertaUpdate.getName()).isPresent()) {
        throw new DuplicateData("Nome da oferta já cadastrado: " + ofertaUpdate.getName());
      }
    }

    ofertaUpdate.setId(id); // Garantir que estamos atualizando a oferta correta
    return ofertaRepository.save(ofertaUpdate);
  }

  public String deleteOferta(Long id) {
    OfertaModel oferta = ofertaRepository.findById(id)
        .orElseThrow(() -> new NotFoundData("Oferta com ID " + id + " não encontrada"));

    ofertaRepository.delete(oferta);
    return "Oferta com ID " + id + " excluída com sucesso.";
  }
}