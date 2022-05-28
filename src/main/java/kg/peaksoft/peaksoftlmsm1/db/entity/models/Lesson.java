package kg.peaksoft.peaksoftlmsm1.db.entity.models;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "videoLesson_id")
    private VideoLesson videoLesson;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "link_id")
    private Link link;

}