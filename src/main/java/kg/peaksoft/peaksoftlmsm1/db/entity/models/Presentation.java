package kg.peaksoft.peaksoftlmsm1.db.entity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "presentation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presentation_gen")
    @SequenceGenerator(name = "presentation_gen",sequenceName = "presentation_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private String file;

    @JsonIgnore
    @OneToOne
    private Lesson lesson;

}
