package mikail.Ranking.Entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String code;
    private Boolean valid;
    private Long rankingId;

    public Ticket(String code, Long rankingId) {
        this.code = code;
        this.valid = true;
        this.rankingId = rankingId;
    }
}