package kg.peaksoft.peaksoftlmsm1.db.entity.testEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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
    @Column(name = "idActive")
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

    public void setQuestions(List<Question> questionList) {
        if (questions == null) {
            questions = questionList;
        }
        this.questions = questionList;
    }

}