package mikail.Ranking.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Interface.SimpleService;
import mikail.Ranking.Repository.RankingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository repo;
    private final QuestionService questionService;
    private final TeacherService teacherService;
    private final TicketService ticketService;

    public void create(@NonNull final String title, @NonNull final Long userId) {
        Ranking existingRanking = repo.findByUserIdAndTitle(userId, title);
        if (existingRanking == null) {
            Ranking ranking = new Ranking(title, userId);
            repo.save(ranking);
        }
    }

    public List<Ranking> findAllFromUser(Long userId) {
        return repo.findAllByUserId(userId);
    }

    public List<Ranking> getAllByTitle(String title) {
        return repo.findAllByTitle(title);
    }

    public List<Ranking> search(String value) {
        return repo.search(value);
    }

    public Ranking getById(Long rankingId) {
        Optional<Ranking> optionalRanking = repo.findById(rankingId);
        return optionalRanking.orElse(null);
    }

    public void delete(Long rankingId) {
        Optional<Ranking> ranking = repo.findById(rankingId);
        ranking.ifPresent(repo::delete);

        questionService.deleteAllByRankingId(rankingId);
        teacherService.deleteAllByRankingId(rankingId);
        ticketService.deleteAllByRankingId(rankingId);
    }

    @Transactional
    public void increaseVotes(Long rankingId) {
        Optional<Ranking> ranking = repo.findById(rankingId);
        ranking.ifPresent(Ranking::increaseVotes);
    }

    public void deleteAllByUserId(Long userId) {
        List<Ranking> rankings = repo.findAllByUserId(userId);
        rankings.forEach(ranking -> delete(ranking.getId()));
    }
}
