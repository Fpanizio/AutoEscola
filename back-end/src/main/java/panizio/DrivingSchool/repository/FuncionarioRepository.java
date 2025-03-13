package panizio.DrivingSchool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import panizio.DrivingSchool.model.FuncionarioModel;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Integer> {

  Optional<FuncionarioModel> findByCpf(String cpf);

  Optional<FuncionarioModel> findByRg(String rg);

  Optional<FuncionarioModel> findByNumeroCnh(String numeroCnh);

  boolean existsByCpf(String cpf);

  boolean existsByRg(String rg);

  boolean existsByNumeroCnh(String numeroCnh);
}