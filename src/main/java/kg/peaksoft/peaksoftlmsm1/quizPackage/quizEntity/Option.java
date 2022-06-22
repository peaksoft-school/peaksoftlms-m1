package kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_gen")
    @SequenceGenerator(name = "answer_gen", sequenceName = "answer_seq", allocationSize = 1)
    private Long id;
    private String option;
    private boolean isCorrect;

    @ManyToOne(cascade = {MERGE, REFRESH, PERSIST, DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name="questions_id")
    @JsonIgnore
    private Question question;

}
