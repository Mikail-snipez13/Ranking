package mikail.Ranking.Entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String key;
    private Boolean valid;
    private Long rankingId;

    public Ticket(String key, Long rankingId) {
        this.key = key;
        this.valid = true;
        this.rankingId = rankingId;
    }
}