package project.Spiny.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "is_required")
    private boolean isRequired;

    @Column(name = "input_value")
    private String inputValue;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "template_id")
    private Template template;

   /* @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "postTemp")
    private Template postTemplate;

    */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    @Override
    public String toString() {
        return "DataField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isRequired=" + isRequired +
                ", inputValue='" + inputValue + '\'' +
                ", value='" + value;
    }
}
