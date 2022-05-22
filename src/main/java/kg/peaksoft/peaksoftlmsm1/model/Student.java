package kg.peaksoft.peaksoftlmsm1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Setter@Getter
public class Student {

    @Id
    private Long id;
}
