package mikail.Ranking.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
public class Teacher {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "joker")
    private int joker;

    @Column(name = "unprepared")
    private int unprepared;

    @Column(name = "late")
    private int late;

    @Column(name = "spoiled")
    private int spoiled;

    @Column(name = "party")
    private int party;

    @Column(name = "smart")
    private int smart;

    @Column(name = "beauty")
    private int beauty;

    @Column(name = "no_mention")
    private int noMention;

    @Column(name = "no_in_time")
    private int noInTime;

    @Column(name = "dishonorable")
    private int dishonorable;

    public void increaseBeauty() {
        joker++;
    }

    public void increaseSmart() {
        unprepared++;
    }

    public void increaseNice() {
        late++;
    }

    public void increaseSevere() {
        spoiled++;
    }
}
