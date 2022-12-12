package mikail.Ranking.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "votes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne
    private Question question;
    @OneToOne
    private Teacher teacher;


    public Long getId() {
        return id;
    }
}