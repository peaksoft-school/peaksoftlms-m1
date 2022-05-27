package kg.peaksoft.peaksoftlmsm1.db.entity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "course_img")
    private String image;
    @Column(name = "name_course")
    private String nameCourse;
    @Column(name = "start_course")
    private Date startCourse;
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "users_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

}
