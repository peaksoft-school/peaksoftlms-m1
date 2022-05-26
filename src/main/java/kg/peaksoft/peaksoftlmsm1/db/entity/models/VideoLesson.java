package kg.peaksoft.peaksoftlmsm1.db.entity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "videoLesson")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VideoLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videoLesson_gen")
    @SequenceGenerator(name = "videoLesson_gen",sequenceName = "videoLesson_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private String link;

    @JsonIgnore
    @OneToOne(mappedBy = "videoLesson")
    private Lesson lesson;

}