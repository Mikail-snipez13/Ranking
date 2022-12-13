package mikail.Ranking;

import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Repository.QuestionRepository;
import mikail.Ranking.Repository.RankingRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import mikail.Ranking.Service.QuestionService;
import mikail.Ranking.Service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class RankingEntityTest {

	@Autowired
	private QuestionRepository questionRepo;

	@Autowired
	private RankingRepository rankingRepo;

	@Autowired
	private RankingService rankingService;

	@Autowired
	private QuestionService questionService;

	void createRanking() {
		rankingService.create("One Piece", 1L);
		Ranking ranking1 = rankingRepo.findByUserIdAndTitle(1L, "One Piece");

		Question q1 = new Question("Wer würde?", ranking1.getId());
		Question q2 = new Question("Wer ist Zoro?", ranking1.getId());

		questionRepo.save(q1);
		questionRepo.save(q2);


	}

	@Test
	void getRankings() {
		createRanking();

		Ranking ranking = rankingRepo.findByUserIdAndTitle(1L, "One Piece");
		System.out.println(String.format("%d: %s", ranking.getId(), ranking.getTitle()));
		assertThat(ranking != null).isTrue();

		List<Question> questions = questionRepo.findAllByRankingId(ranking.getId());
		System.out.println("----QUESTION----");
		questions.forEach(question -> System.out.println(question.getId() + ": " + question.getText()));
		assertThat(questions.size() > 1).isTrue();
		System.out.println("----NEW QUESTION----");
		Optional<Question> question = questionRepo.findById(questions.get(0).getId());
		question.ifPresent(quest -> questionService.updateText(questions.get(0).getId(), "Ich bin neu!"));

		Optional<Question> newQuestion = questionRepo.findById(questions.get(0).getId());
		newQuestion.ifPresent(question1 -> System.out.println(question1.getId() + ": " + question1.getText()));
	}

}
