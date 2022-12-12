package mikail.Ranking.Repository;

import mikail.Ranking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByNickname(String nickname);
}
