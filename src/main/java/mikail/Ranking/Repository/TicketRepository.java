package mikail.Ranking.Repository;

import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM ticket WHERE code = ?1 AND ranking_id = ?2", nativeQuery = true)
    public Ticket getByCodeAndRankingId(String code, Long rankingId);
    public List<Ticket> findAllByRankingId(Long rankingId);
}
