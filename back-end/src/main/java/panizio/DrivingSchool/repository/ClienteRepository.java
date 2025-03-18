package panizio.DrivingSchool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import panizio.DrivingSchool.model.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

  Optional<ClienteModel> findByCpf(String cpf);

  Optional<ClienteModel> findByRg(String rg);

  Optional<ClienteModel> findByEmail(String email);

  Optional<ClienteModel> findByTelefone(String telefone);

}