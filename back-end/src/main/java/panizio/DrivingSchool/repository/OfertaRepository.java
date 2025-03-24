package panizio.DrivingSchool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import panizio.DrivingSchool.enums.CategoriaEnum;
import panizio.DrivingSchool.model.OfertaModel;

@Repository
public interface OfertaRepository extends JpaRepository<OfertaModel, Long> {

  boolean existsByName(String name);

  Optional<OfertaModel> findByName(String name);

  Optional<OfertaModel> findByCategoria(CategoriaEnum categoria);

}
