package kg.peaksoft.peaksoftlmsm1.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(generator = "role_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_gen", sequenceName = "role_sec", allocationSize = 1)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new LinkedHashSet<>();


    public Role(String name) {
        this.name = name;
    }
}
