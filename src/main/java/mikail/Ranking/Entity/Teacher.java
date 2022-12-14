package mikail.Ranking.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Teacher {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstname;
    private String lastname;
    private Long rankingId;

    public Teacher(String firstname, String lastname, Long rankingId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.rankingId = rankingId;
    }
}
