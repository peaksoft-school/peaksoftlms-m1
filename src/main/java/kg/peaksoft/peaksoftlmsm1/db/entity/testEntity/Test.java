package kg.peaksoft.peaksoftlmsm1.db.entity.testEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_gen")
    @SequenceGenerator(name = "test_gen", sequenceName = "test_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(cascade = ALL, fetch = FetchType.EAGER, mappedBy = "test")
    @Fetch(FetchMode.SUBSELECT)
    private List<Question> questions;

    @OneToMany(cascade = ALL, mappedBy = "test")
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Result> testResults;

    @JsonIgnore
    @OneToOne(mappedBy = "test")
    private Lesson lesson;

    public void setQuestion(Question question) {
        if (this.questions == null) {
            this.questions = new ArrayList<>();
        }
        this.questions.add(question);
    }

}