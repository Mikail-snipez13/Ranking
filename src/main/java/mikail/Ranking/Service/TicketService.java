package mikail.Ranking.Service;

import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Entity.Ticket;
import mikail.Ranking.Factory.TicketFactory;
import mikail.Ranking.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repo;
    private final TicketFactory factory;

    public Ticket create(Long rankingId) {
        Ticket ticket = factory.createTicket(repo, rankingId);
        repo.save(ticket);
        return ticket;
    }

    public Ticket getById(Long id) {
        return repo.getById(id);
    }

    public Ticket getByKeyAndRankingId(String key, Long rankingId) {
        return repo.getByKeyAndRankingId(key, rankingId);
    }

    public List<Ticket> getAllByRankingId(Long rankingId) {
        return repo.findAllByRankingId(rankingId);
    }

    @Transactional
    public void useTicket(String key, Long rankingId) {
        Ticket ticket = repo.getByKeyAndRankingId(key, rankingId);
        if (ticket != null) {
            ticket.setValid(false);
        }
    }

    @Transactional
    public void useTicket(Long id) {
        Optional<Ticket> ticket = repo.findById(id);
        ticket.ifPresent(ticket1 -> ticket1.setValid(false));
    }

    public void deleteAllByRankingId(Long rankingId) {
        List<Ticket> tickets = repo.findAllByRankingId(rankingId);
        repo.deleteAll(tickets);
    }
}
