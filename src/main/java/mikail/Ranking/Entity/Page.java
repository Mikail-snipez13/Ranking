package mikail.Ranking.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "page")
@NoArgsConstructor
public class Page {

    @Id
    private Long id;

    private int views;

    public void increaseViews() {
        this.views++;
    }
}
