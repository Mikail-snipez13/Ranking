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
        beauty++;
    }

    public void increaseJoker() {
        joker++;
    }

    public void increaseUnprepared() {
        unprepared++;
    }

    public void increaseLate() {
        late++;
    }

    public void increaseSpoiled() {
        spoiled++;
    }

    public void increaseParty() {
        party++;
    }

    public void increaseSmart() {
        smart++;
    }

    public void increaseNoMention() {
        noMention++;
    }

    public void increaseNoInTime() {
        noInTime++;
    }

    public void increaseDishonorable() {
        dishonorable++;
    }
}
