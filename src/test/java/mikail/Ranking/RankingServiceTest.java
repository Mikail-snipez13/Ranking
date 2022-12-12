package mikail.Ranking;

import mikail.Ranking.Service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RankingServiceTest {

    @Autowired
    private RankingService rankingService;

    @Test
    public void createRanking() {
        rankingService.create("One Piece", 1L);
        rankingService.create("One Piece", 1L);
    }
}
