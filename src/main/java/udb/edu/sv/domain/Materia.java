package udb.edu.sv.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;
}