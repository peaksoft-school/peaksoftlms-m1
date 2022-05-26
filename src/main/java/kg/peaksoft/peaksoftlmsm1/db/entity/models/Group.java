package kg.peaksoft.peaksoftlmsm1.db.entity.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_gen")
    @SequenceGenerator(name = "group_gen",sequenceName = "group_seq",allocationSize = 1)
    private Long id;
    @Column(name = "group_img")
    private String image;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "start_date")
    private Date startDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "groups")
    private List<User> users;


}
