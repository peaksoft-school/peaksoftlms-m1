package kg.peaksoft.peaksoftlmsm1.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsm1.db.entity.enums.Specialization;
import kg.peaksoft.peaksoftlmsm1.db.entity.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Course;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_gen", sequenceName = "user_sec", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private LocalDateTime created;
    private boolean isActive;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Role> roles;

    @ManyToMany(mappedBy = "users")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> courses;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Group> groups;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Role role: roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public void setRoles(Role role) {
        if (null == roles){
            roles = new ArrayList<>();
        }
        roles.add(role);
    }

    @JsonBackReference("roles")
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setGroups(Group group) {
        if (this.groups == null) {
            this.groups = new ArrayList<>();
        }
        this.groups.add(group);
    }

    @JsonBackReference("groups")
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
