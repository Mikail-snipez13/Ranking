package mikail.Ranking.Entity;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Votes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Category category;
    private int count;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}