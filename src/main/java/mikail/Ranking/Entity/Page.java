package mikail.Ranking.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Table(name = "page")
@NoArgsConstructor
public class Page {

    @Id
    private String name;
    private int views;

    public void increaseViews() {
        this.views++;
    }
}
