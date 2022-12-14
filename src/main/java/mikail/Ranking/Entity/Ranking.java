package mikail.Ranking.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private int votes;
    private Long userId;

    public Ranking(String title, Long userId) {
        this.title = title;
        this.userId = userId;
        this.votes = 0;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void increaseVotes() {votes++;}
}
