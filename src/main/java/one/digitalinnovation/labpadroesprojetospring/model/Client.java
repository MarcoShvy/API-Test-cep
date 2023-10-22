package one.digitalinnovation.labpadroesprojetospring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private Address address;

}
