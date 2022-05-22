package kg.peaksoft.peaksoftlmsm1.db.entity.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_gen")
    @SequenceGenerator(name = "group_gen",sequenceName = "group_seq",allocationSize = 1)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "start_of_group")
    private Date starOfGroup;
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
