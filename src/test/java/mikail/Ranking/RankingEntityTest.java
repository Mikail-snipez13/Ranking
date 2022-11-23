package mikail.Ranking;

import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Repository.QuestionRepository;
import mikail.Ranking.Repository.RankingRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class RankingEntityTest {

	@Autowired
	private QuestionRepository questionRepo;

	@Autowired
	private RankingRepository rankingRepo;

	@Test
	void createRanking() {
		Ranking ranking = Ranking.builder()
				.title("One Piece").build();

		Question q1 = Question.builder().text("Wer würde?").build();
		Question q2 = Question.builder().text("Wer ist Zoro?").build();

		ranking.addQuestion(q1);
		ranking.addQuestion(q2);

		questionRepo.saveAll(ranking.getQuestions());
		rankingRepo.save(ranking);
	}

	@Test
	void getRankings() {
		createRanking();

		List<Ranking> rankings = rankingRepo.findAll();
		assertThat(rankings.size() == 1).isTrue();
	}

}
