package panizio.DrivingSchool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import panizio.DrivingSchool.model.FuncionarioModel;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {

  Optional<FuncionarioModel> findByCpf(String cpf);

  boolean existsByCpf(String cpf);

  boolean existsByRg(String rg);

  boolean existsByNumeroCnh(String numeroCnh);
}