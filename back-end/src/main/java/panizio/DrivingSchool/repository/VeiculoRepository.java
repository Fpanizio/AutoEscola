package panizio.DrivingSchool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import panizio.DrivingSchool.model.VeiculoModel;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoModel, Integer> {
  Optional<VeiculoModel> findByPlaca(String placa);

  Optional<VeiculoModel> findByRenavam(String renavam);
}
