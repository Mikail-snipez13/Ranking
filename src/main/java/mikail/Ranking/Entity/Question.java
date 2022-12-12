package mikail.Ranking.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String text;
    private Long rankingId;

    public Question(String text, Long rankingId) {
        this.text = text;
        this.rankingId = rankingId;
    }

    public void setText(String text) {
        this.text = text;
    }
}
