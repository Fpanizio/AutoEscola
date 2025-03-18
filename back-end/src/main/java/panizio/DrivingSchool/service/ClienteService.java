package panizio.DrivingSchool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import panizio.DrivingSchool.exception.DuplicateData;
import panizio.DrivingSchool.exception.NotFoundData;
import panizio.DrivingSchool.model.ClienteModel;
import panizio.DrivingSchool.repository.ClienteRepository;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ModelMapper modelMapper;

  public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
    this.clienteRepository = clienteRepository;
    this.modelMapper = modelMapper;
  }

  public List<ClienteModel> getAllClients() {
    return clienteRepository.findAll();
  }

  public ClienteModel getAllClientsByCpf(String cpf) {
    return clienteRepository.findByCpf(cpf)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
  }

  public String deleteClients(String cpf) {
    ClienteModel cliente = clienteRepository.findByCpf(cpf)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    clienteRepository.delete(cliente);
    return "Cliente com CPF " + cpf + " excluído com sucesso.";
  }

  public ClienteModel postClients(ClienteModel cliente) {

    Map<String, String> camposDuplicados = new HashMap<>();

    // Verificar se o CPF já existe
    if (clienteRepository.findByRg(cliente.getRg()).isPresent()) {
      camposDuplicados.put("cpf", "CPF já cadastrado: " + cliente.getCpf());
    }

    // Verificar se o RG já existe
    if (clienteRepository.findByRg(cliente.getRg()).isPresent()) {
      camposDuplicados.put("rg", "RG já cadastrado: " + cliente.getRg());
    }

    if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
      camposDuplicados.put("Email", "Email já cadastrado" + cliente.getEmail());
    }

    // Se houver campos duplicados, lançar a exceção
    if (!camposDuplicados.isEmpty()) {
      throw new DuplicateData("Recursos duplicados encontrados", camposDuplicados);
    }

    return clienteRepository.save(cliente);
  }

  public ClienteModel updateClient(String cpf, ClienteModel clienteUpdate) {
    ClienteModel clienteExist = clienteRepository.findByCpf(cpf)
        .orElseThrow(() -> new NotFoundData(cpf));

    // verifica e atualiza CPF
    if (clienteUpdate.getCpf() != null
        && !clienteExist.getCpf().equals(clienteUpdate.getCpf())) {
      clienteRepository.findByCpf(clienteUpdate.getCpf())
          .ifPresent(cliente -> {
            throw new RuntimeException("Já existe um cliente com o CPF informado");
          });
      clienteExist.setCpf(clienteUpdate.getCpf());
    }

    // verifica e atualiza RG
    if (clienteUpdate.getRg() != null
        && !clienteExist.getRg().equals(clienteUpdate.getRg())) {
      clienteRepository.findByRg(clienteUpdate.getRg())
          .ifPresent(cliente -> {
            throw new RuntimeException("Já existe um cliente com o RG informado");
          });
      clienteExist.setRg(clienteUpdate.getRg());
    }

    // configuração ModelMapper para ignorar campos únicos
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.typeMap(ClienteModel.class, ClienteModel.class)
        .addMappings(mapper -> {
          mapper.skip(ClienteModel::setCpf);
          mapper.skip(ClienteModel::setRg);
        });

    modelMapper.map(clienteUpdate, clienteExist);
    return clienteRepository.save(clienteExist);

  }
}
