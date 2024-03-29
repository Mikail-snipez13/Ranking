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
    private String id;

    @Column(name = "used")
    private Boolean used;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}