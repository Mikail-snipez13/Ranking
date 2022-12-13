package mikail.Ranking.Repository;

import mikail.Ranking.Entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    public List<Vote> findAllByQuestionId(Long questionId);
}
