package project.Spiny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "dataField",fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<DataFieldType> dataFieldType;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;

    public void addDataFieldType(DataFieldType dataFieldType){
        this.dataFieldType.add(dataFieldType);
    }
}
