package kg.peaksoft.peaksoftlmsm1.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class FilePath {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_gen")
    @SequenceGenerator(name = "file_gen", sequenceName = "file_seq", allocationSize = 1)
    private Long id;

    private String fileName;

    public FilePath(String fileName) {
        this.fileName = fileName;
    }

}