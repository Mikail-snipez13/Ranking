package mikail.Ranking.Repository;

import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    public List<Question> findAllByRankingId(Long rankingId);

    @Query(value = "SELECT * FROM question WHERE ranking_id = ?1 and text = ?2", nativeQuery = true)
    public Question findByRankingIdAndText(Long rankingId, String Text);
}
