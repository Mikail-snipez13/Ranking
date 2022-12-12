package mikail.Ranking.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Repository.RankingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository repo;

    public Ranking create(@NonNull final String title, @NonNull final Long userId) {
        Ranking existingRanking = repo.findByTitle(title);
        if (existingRanking == null) {
            Ranking ranking = new Ranking(title, userId);
            repo.save(ranking);
            return ranking;
        }
        else {
            return existingRanking;
        }
    }

    public List<Ranking> findAllFromUser(Long userId) {
        return repo.findAllByUserId(userId);
    }

    public Ranking getRanking(Long rankingId) {
        Optional<Ranking> optionalRanking = repo.findById(rankingId);
        return optionalRanking.orElse(null);
    }
}
