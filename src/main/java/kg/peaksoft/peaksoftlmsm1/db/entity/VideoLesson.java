package kg.peaksoft.peaksoftlmsm1.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video_lessons")
public class VideoLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "video_lesson_gen")
    @SequenceGenerator(name = "video_lesson_gen", sequenceName = "video_lesson_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    private String link;

    @JsonIgnore
    @OneToOne(mappedBy = "videoLesson")
    private Lesson lesson;

}