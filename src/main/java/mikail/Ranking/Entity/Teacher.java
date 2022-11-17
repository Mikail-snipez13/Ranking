package mikail.Ranking.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "teacher")
@Setter
@Getter
@NoArgsConstructor
public class Teacher {

    @Id
    @Column(name = "id", nullable = false)
    @Getter(AccessLevel.NONE)
    private String id;
    private String firstname;
    private String lastname;
}
