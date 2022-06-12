package kg.peaksoft.peaksoftlmsm1.db.entity.models;

import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FilePath {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;

    public FilePath(String fileName) {
        this.fileName = fileName;
    }

}