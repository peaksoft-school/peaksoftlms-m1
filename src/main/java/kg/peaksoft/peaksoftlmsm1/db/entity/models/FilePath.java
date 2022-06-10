package kg.peaksoft.peaksoftlmsm1.db.entity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Table(name = "files")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilePath {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String path;

}