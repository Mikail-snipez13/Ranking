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

    @Column(name = "beauty")
    private int beauty;

    @Column(name = "smart")
    private int smart;

    @Column(name = "nice")
    private int nice;

    @Column(name = "severe")
    private int severe;

    public void increaseBeauty() {
        beauty++;
    }

    public void increaseSmart() {
        smart++;
    }

    public void increaseNice() {
        nice++;
    }

    public void increaseSevere() {
        severe++;
    }
}
