package kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsm1.db.entity.enumPackage.EQuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_gen")
    @SequenceGenerator(name = "question_gen", sequenceName = "question_seq", allocationSize = 1)
    private Long id;
    private String questionTitle;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Option> options;

    @Enumerated(EnumType.STRING)
    private EQuestionType questionType;

    @JsonIgnore
    @ManyToOne(cascade = {MERGE, REFRESH, PERSIST, DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    public void setOption(Option option) {
        if (this.options == null) {
            this.options = new ArrayList<>();
        }
        this.options.add(option);
    }

    public void setOptions(List<Option> optionList) {
        if (options == null) {
            options = optionList;
        }
        this.options = optionList;
    }

}
