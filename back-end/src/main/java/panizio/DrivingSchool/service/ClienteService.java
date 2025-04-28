package panizio.DrivingSchool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import panizio.DrivingSchool.exception.CpfInvalidURL;
import panizio.DrivingSchool.exception.DuplicateData;
import panizio.DrivingSchool.exception.NotFoundData;
import panizio.DrivingSchool.model.ClienteModel;
import panizio.DrivingSchool.repository.ClienteRepository;
import panizio.DrivingSchool.utils.CpfUtils;

@Service
public class ClienteService {

  private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<ClienteModel> getAllClients() {
    logger.info("Buscando todos os clientes");
    return clienteRepository.findAll();
  }

  public ClienteModel getClientByCpf(String cpf) {
    logger.info("Buscando cliente pelo CPF: {}", cpf);
    validarCpf(cpf);

    return clienteRepository.findByCpf(CpfUtils.formatarCpf(cpf))
        .orElseThrow(() -> new NotFoundData("Cliente com CPF " + cpf + " não encontrado"));
  }

  public ClienteModel postClients(ClienteModel cliente) {
    logger.info("Criando novo cliente: {}", cliente.getNomeCompleto());
    validarDuplicidade(cliente);
    return clienteRepository.save(cliente);
  }

  public ClienteModel updateClient(String cpf, ClienteModel clienteUpdate) {
    logger.info("Atualizando cliente com CPF: {}", cpf);
    validarCpf(cpf);

    ClienteModel clienteExist = clienteRepository.findByCpf(CpfUtils.formatarCpf(cpf))
        .orElseThrow(() -> new NotFoundData("Cliente com CPF " + cpf + " não encontrado"));

    validarDuplicidade(clienteUpdate, clienteExist);

    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.map(clienteUpdate, clienteExist);

    return clienteRepository.save(clienteExist);
  }

  public String deleteClients(String cpf) {
    logger.info("Excluindo cliente com CPF: {}", cpf);
    validarCpf(cpf);

    ClienteModel cliente = clienteRepository.findByCpf(CpfUtils.formatarCpf(cpf))
        .orElseThrow(() -> new NotFoundData("Cliente com CPF " + cpf + " não encontrado"));

    clienteRepository.delete(cliente);
    return "Cliente com CPF " + cpf + " excluído com sucesso.";
  }

  private void validarCpf(String cpf) {
    if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
      throw new CpfInvalidURL("CPF inválido. O CPF deve conter exatamente 11 dígitos numéricos.");
    }
  }

  private void validarDuplicidade(ClienteModel cliente) {
    Map<String, String> camposDuplicados = new HashMap<>();

    if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
      camposDuplicados.put("cpf", "CPF já cadastrado: " + cliente.getCpf());
    }

    if (clienteRepository.findByRg(cliente.getRg()).isPresent()) {
      camposDuplicados.put("rg", "RG já cadastrado: " + cliente.getRg());
    }

    if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
      camposDuplicados.put("email", "Email já cadastrado: " + cliente.getEmail());
    }

    if (!camposDuplicados.isEmpty()) {
      throw new DuplicateData("Recursos duplicados encontrados", camposDuplicados);
    }
  }

  private void validarDuplicidade(ClienteModel clienteUpdate, ClienteModel clienteExist) {
    if (!clienteUpdate.getCpf().equals(clienteExist.getCpf()) &&
        clienteRepository.findByCpf(clienteUpdate.getCpf()).isPresent()) {
      throw new DuplicateData("CPF já cadastrado: " + clienteUpdate.getCpf());
    }

    if (!clienteUpdate.getRg().equals(clienteExist.getRg()) &&
        clienteRepository.findByRg(clienteUpdate.getRg()).isPresent()) {
      throw new DuplicateData("RG já cadastrado: " + clienteUpdate.getRg());
    }

    if (!clienteUpdate.getEmail().equals(clienteExist.getEmail()) &&
        clienteRepository.findByEmail(clienteUpdate.getEmail()).isPresent()) {
      throw new DuplicateData("Email já cadastrado: " + clienteUpdate.getEmail());
    }
  }
}