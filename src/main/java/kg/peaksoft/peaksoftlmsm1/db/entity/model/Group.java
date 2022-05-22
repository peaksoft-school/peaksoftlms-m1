package kg.peaksoft.peaksoftlmsm1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Setter@Getter
public class Group {

    @Id
    @GeneratedValue(generator = "group_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_gen",sequenceName = "group_seq",allocationSize = 1)
    private Long id;
    @Column(name = "group_name",unique = true)
    private String groupName;
    private LocalDate dateOfStart;
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany
    @JoinColumn(name = "student_id")
    private List<Student> student;
}
