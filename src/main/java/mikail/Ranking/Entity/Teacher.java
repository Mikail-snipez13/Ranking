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
    private String name;
    private Long rankingId;

    public Teacher(String name, Long rankingId) {
        this.name = name;
        this.rankingId = rankingId;
    }
}
