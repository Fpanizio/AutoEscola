package panizio.DrivingSchool.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import panizio.DrivingSchool.enums.CategoriaEnum;
import panizio.DrivingSchool.exception.DuplicateData;
import panizio.DrivingSchool.exception.NotFoundData;
import panizio.DrivingSchool.model.OfertaModel;
import panizio.DrivingSchool.repository.OfertaRepository;

@Service
public class OfertasService {

  private static final Logger logger = LoggerFactory.getLogger(OfertasService.class);

  @Autowired
  private OfertaRepository ofertaRepository;

  public List<OfertaModel> getAllOfertas() {
    logger.info("Buscando todas as ofertas");
    return ofertaRepository.findAll();
  }

  public OfertaModel getOfertaById(Long id) {
    logger.info("Buscando oferta pelo ID: {}", id);
    return ofertaRepository.findById(id)
        .orElseThrow(() -> new NotFoundData("Oferta com ID " + id + " não encontrada"));
  }

  public OfertaModel getOfertaByCategoria(String categoriaInput) {
    logger.info("Buscando oferta pela categoria: {}", categoriaInput);
    String categoriaNormalized = categoriaInput.trim().toUpperCase();

    try {
      CategoriaEnum categoriaEnum = CategoriaEnum.valueOf(categoriaNormalized);
      return ofertaRepository.findByCategoria(categoriaEnum)
          .orElseThrow(() -> new NotFoundData("Oferta com categoria " + categoriaEnum + " não encontrada"));
    } catch (IllegalArgumentException e) {
      throw new NotFoundData("Categoria inválida: " + categoriaInput);
    }
  }

  public OfertaModel getOfertaByName(String name) {
    logger.info("Buscando oferta pela categoria: {}", name);

    try {
      return ofertaRepository.findByName(name)
          .orElseThrow(() -> new NotFoundData("Oferta com nome: " + name + ", não encontrada"));
    } catch (IllegalArgumentException e) {
      throw new NotFoundData("Categoria inválida: " + name);
    }
  }

  public OfertaModel postOferta(OfertaModel oferta) {
    logger.info("Criando nova oferta: {}", oferta.getName());
    validarDuplicidade(oferta);
    return ofertaRepository.save(oferta);
  }

  public OfertaModel updateOferta(Long id, OfertaModel ofertaUpdate) {
    logger.info("Atualizando oferta com ID: {}", id);

    OfertaModel ofertaExist = ofertaRepository.findById(id)
        .orElseThrow(() -> new NotFoundData("Oferta com ID " + id + " não encontrada"));

    validarDuplicidade(ofertaUpdate, ofertaExist);

    ofertaExist.setName(ofertaUpdate.getName());
    ofertaExist.setCategoria(ofertaUpdate.getCategoria());
    ofertaExist.setPrice(ofertaUpdate.getPrice());
    // Atualizar outros campos conforme necessário

    return ofertaRepository.save(ofertaExist);
  }

  public String deleteOferta(Long id) {
    logger.info("Excluindo oferta com ID: {}", id);

    OfertaModel oferta = ofertaRepository.findById(id)
        .orElseThrow(() -> new NotFoundData("Oferta com ID " + id + " não encontrada"));

    ofertaRepository.delete(oferta);
    return "Oferta com ID " + id + " excluída com sucesso.";
  }

  private void validarDuplicidade(OfertaModel oferta) {
    if (ofertaRepository.findByName(oferta.getName()).isPresent()) {
      throw new DuplicateData("Oferta com nome já cadastrado: " + oferta.getName());
    }
  }

  private void validarDuplicidade(OfertaModel ofertaUpdate, OfertaModel ofertaExist) {
    if (!ofertaUpdate.getName().equals(ofertaExist.getName()) &&
        ofertaRepository.findByName(ofertaUpdate.getName()).isPresent()) {
      throw new DuplicateData("Oferta com nome já cadastrado: " + ofertaUpdate.getName());
    }
  }
}