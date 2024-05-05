package project.Spiny.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="data_field")
public class DataField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "isRequired")
    private boolean isRequired;

    @ManyToOne
    @JoinColumn(name = "data_field_type_id")
    private DataFieldType dataFieldType;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;
}
