package mikail.Ranking.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Category {

    public Long id;
    public String title;
    public List<String> best;

}
