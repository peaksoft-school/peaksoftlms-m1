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
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_gen")
    @SequenceGenerator(name = "link_gen", sequenceName = "link_seq", allocationSize = 1)
    private Long id;

    private String text;

    private String link;

    @JsonIgnore
    @OneToOne(mappedBy = "link")
    private Lesson lesson;

}