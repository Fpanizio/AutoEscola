package panizio.DrivingSchool.model;

import jakarta.persistence.*;
import lombok.Data;
import panizio.DrivingSchool.enums.CategoriaEnum;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "services")
public class OfertaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", length = 50)
    private CategoriaEnum categoria;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive = true;

}