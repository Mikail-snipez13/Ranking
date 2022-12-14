package mikail.Ranking.Entity;

import lombok.*;

import javax.persistence.*;
import javax.swing.text.DateFormatter;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
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
    private Long questionId;
    private Long teacherId;
    private String timestamp;

    public Vote(Long questionId, Long teacherId, String date) {
        this.questionId = questionId;
        this.teacherId = teacherId;
        this.timestamp = date;
    }
}