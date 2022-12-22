package mikail.Ranking.Service;

import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Model.RankingResponseModel;
import org.springframework.stereotype.Service;

@Service
public class ModelGenerator {

    public RankingResponseModel create(Long id, String title, int votes, Long userId, int questions) {
        return new RankingResponseModel(id, title, votes, userId, questions);
    }

    public RankingResponseModel create(Ranking ranking, int questions) {
        return new RankingResponseModel(ranking, questions);
    }
}
