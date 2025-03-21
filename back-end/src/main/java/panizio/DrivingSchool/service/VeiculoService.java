package panizio.DrivingSchool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import panizio.DrivingSchool.exception.DuplicateData;
import panizio.DrivingSchool.exception.NotFoundData;
import panizio.DrivingSchool.model.VeiculoModel;
import panizio.DrivingSchool.repository.VeiculoRepository;

@Service
public class VeiculoService {

  @Autowired
  private VeiculoRepository veiculoRepository;

  @Autowired
  private ModelMapper modelMapper;

  public VeiculoService(VeiculoRepository veiculoRepository, ModelMapper modelMapper) {
    this.veiculoRepository = veiculoRepository;
    this.modelMapper = modelMapper;
  }

  public List<VeiculoModel> getAllVeiculos() {
    return veiculoRepository.findAll();
  }

  public VeiculoModel getVeiculoByPlaca(String placa) {
    return veiculoRepository.findByPlaca(formatarPlaca(placa))
        .orElseThrow(() -> new NotFoundData("Veículo com placa " + formatarPlaca(placa) + " não encontrado"));
  }

  public VeiculoModel postClients(VeiculoModel model) {
    Map<String, String> camposDuplicados = new HashMap<>();

    if (veiculoRepository.findByPlaca(model.getPlaca()).isPresent()) {
      camposDuplicados.put("placa", "Veiculo já cadastrado: " + model.getPlaca());
    }

    if (veiculoRepository.findByRenavam(model.getRenavam()).isPresent()) {
      camposDuplicados.put("Renavam", "Veiculo já cadastrado: " + model.getRenavam());
    }

    if (!camposDuplicados.isEmpty()) {
      throw new IllegalArgumentException("Recursos duplicados encontrados: " + camposDuplicados);
    }

    model.setPlaca(formatarPlaca(model.getPlaca()));

    return veiculoRepository.save(model);
  }

  public VeiculoModel updateVeiculo(String placa, VeiculoModel veiculoUpdate) {
    String formatPlaca = formatarPlaca(placa);

    VeiculoModel veiculoExist = veiculoRepository.findByPlaca(formatPlaca)
        .orElseThrow(() -> new NotFoundData("Veículo com placa " + formatPlaca + " não encontrado"));

    if (veiculoUpdate.getPlaca() != null && !veiculoExist.getPlaca().equals(veiculoUpdate.getPlaca())) {
      if (veiculoRepository.findByPlaca(veiculoUpdate.getPlaca()).isPresent()) {
        throw new DuplicateData("Já existe um carro com a placa informada", Map.of("Placa", veiculoUpdate.getPlaca()));
      }
      veiculoExist.setPlaca(veiculoUpdate.getPlaca());
    }

    if (veiculoUpdate.getRenavam() != null && !veiculoExist.getRenavam().equals(veiculoUpdate.getRenavam())) {
      if (veiculoRepository.findByRenavam(veiculoUpdate.getRenavam()).isPresent()) {
        throw new DuplicateData("Já existe um carro com o Renavam informado",
            Map.of("Renavam", veiculoUpdate.getRenavam()));
      }
      veiculoExist.setRenavam(veiculoUpdate.getRenavam());
    }

    veiculoExist.setPlaca(formatarPlaca(veiculoExist.getPlaca()));

    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.typeMap(VeiculoModel.class, VeiculoModel.class)
        .addMappings(mapper -> {
          mapper.skip(VeiculoModel::setPlaca);
          mapper.skip(VeiculoModel::setRenavam);
        });

    modelMapper.map(veiculoUpdate, veiculoExist);
    return veiculoRepository.save(veiculoExist);
  }

  public String deleteVeiculo(String placa) {
    VeiculoModel veiculo = veiculoRepository.findByPlaca(formatarPlaca(placa))
        .orElseThrow(() -> new NotFoundData("Veículo com placa " + formatarPlaca(placa) + " não encontrado"));

    veiculoRepository.delete(veiculo);
    return "Veículo com placa " + formatarPlaca(placa) + " deletado com sucesso";
  }

  public String formatarPlaca(String placa) {
    if (placa.length() != 7) {
      throw new IllegalArgumentException("Placa inválida. A placa deve conter exatamente 7 caracteres.");
    }

    if (!placa.matches("[A-Z]{3}\\d{4}") && !placa.matches("[A-Z]{3}\\d[A-Z]\\d{2}")) {
      throw new IllegalArgumentException(
          "Placa inválida. A placa deve seguir o formato antigo (LLLNNNN) ou o novo formato (LLLNLNN).");
    }

    // Formata a placa de acordo com o formato
    if (placa.matches("[A-Z]{3}\\d{4}")) {
      // Formato antigo: LLLNNNN -> LLL-NNNN
      return placa.substring(0, 3) + "-" + placa.substring(3);
    } else {
      // Formato novo: LLLNLNN -> já está no formato correto
      return placa;
    }
  }
}
