package udb.edu.sv.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profesor")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;
}