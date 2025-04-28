package panizio.DrivingSchool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import panizio.DrivingSchool.exception.DuplicateData;
import panizio.DrivingSchool.exception.NotFoundData;
import panizio.DrivingSchool.model.VeiculoModel;
import panizio.DrivingSchool.repository.VeiculoRepository;
import panizio.DrivingSchool.utils.VeiculosUtils;

@Service
public class VeiculoService {

  private static final Logger logger = LoggerFactory.getLogger(VeiculoService.class);

  @Autowired
  private VeiculoRepository veiculoRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<VeiculoModel> getAllVeiculos() {
    logger.info("Buscando todos os veículos");
    return veiculoRepository.findAll();
  }

  public VeiculoModel getVeiculoByPlaca(String placa) {
    logger.info("Buscando veículo pela placa: {}", placa);
    String placaFormatada = VeiculosUtils.formatarPlaca(placa);

    return veiculoRepository.findByPlaca(placaFormatada)
        .orElseThrow(() -> new NotFoundData("Veículo com placa " + placaFormatada + " não encontrado"));
  }

  public VeiculoModel postVeiculo(VeiculoModel veiculo) {
    logger.info("Criando novo veículo: {}", veiculo.getModelo());
    validarDuplicidade(veiculo);
    return veiculoRepository.save(veiculo);
  }

  public VeiculoModel updateVeiculo(String placa, VeiculoModel veiculoUpdate) {
    logger.info("Atualizando veículo com placa: {}", placa);
    String placaFormatada = VeiculosUtils.formatarPlaca(placa);

    VeiculoModel veiculoExist = veiculoRepository.findByPlaca(placaFormatada)
        .orElseThrow(() -> new NotFoundData("Veículo com placa " + placaFormatada + " não encontrado"));

    validarDuplicidade(veiculoUpdate, veiculoExist);

    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.map(veiculoUpdate, veiculoExist);

    return veiculoRepository.save(veiculoExist);
  }

  public String deleteVeiculo(String placa) {
    logger.info("Excluindo veículo com placa: {}", placa);
    String placaFormatada = VeiculosUtils.formatarPlaca(placa);

    VeiculoModel veiculo = veiculoRepository.findByPlaca(placaFormatada)
        .orElseThrow(() -> new NotFoundData("Veículo com placa " + placaFormatada + " não encontrado"));

    veiculoRepository.delete(veiculo);
    return "Veículo com placa " + placaFormatada + " excluído com sucesso.";
  }

  private void validarDuplicidade(VeiculoModel veiculo) {
    Map<String, String> camposDuplicados = new HashMap<>();

    if (veiculoRepository.findByPlaca(veiculo.getPlaca()).isPresent()) {
      camposDuplicados.put("placa", "Placa já cadastrada: " + veiculo.getPlaca());
    }

    if (veiculoRepository.findByRenavam(veiculo.getRenavam()).isPresent()) {
      camposDuplicados.put("renavam", "Renavam já cadastrado: " + veiculo.getRenavam());
    }

    if (!camposDuplicados.isEmpty()) {
      throw new DuplicateData("Recursos duplicados encontrados", camposDuplicados);
    }
  }

  private void validarDuplicidade(VeiculoModel veiculoUpdate, VeiculoModel veiculoExist) {
    if (!veiculoUpdate.getPlaca().equals(veiculoExist.getPlaca()) &&
        veiculoRepository.findByPlaca(veiculoUpdate.getPlaca()).isPresent()) {
      throw new DuplicateData("Placa já cadastrada: " + veiculoUpdate.getPlaca());
    }

    if (!veiculoUpdate.getRenavam().equals(veiculoExist.getRenavam()) &&
        veiculoRepository.findByRenavam(veiculoUpdate.getRenavam()).isPresent()) {
      throw new DuplicateData("Renavam já cadastrado: " + veiculoUpdate.getRenavam());
    }
  }
}