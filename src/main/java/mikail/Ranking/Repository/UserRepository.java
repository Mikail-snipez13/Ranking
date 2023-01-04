package mikail.Ranking.Repository;

import mikail.Ranking.Entity.RankingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RankingUser, Long> {
    public RankingUser findByUsername(String username);
}
