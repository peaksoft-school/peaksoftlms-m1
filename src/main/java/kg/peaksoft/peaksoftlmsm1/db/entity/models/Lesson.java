package kg.peaksoft.peaksoftlmsm1.db.entity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Test;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lesson")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_gen")
    @SequenceGenerator(name = "lesson_gen", sequenceName = "lesson_seq", allocationSize = 1)
    private Long id;
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "videoLesson_id")
    private VideoLesson videoLesson;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_id")
    private Link link;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

}