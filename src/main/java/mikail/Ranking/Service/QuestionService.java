package mikail.Ranking.Service;

import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repo;

    public void create(String text, Long rankingId) {
        Question question = repo.findByRankingIdAndText(rankingId, text);
        if (question != null) {return;}
        repo.save(new Question(text, rankingId));
    }

    @Transactional
    public void updateText(Long questionId, String text) throws NullPointerException{
        Optional<Question> question = repo.findById(questionId);
        question.ifPresent(quest -> {
            quest.setText(text);
        });
    }

    public List<Question> getAllByRankingId(Long rankingId) {
        return repo.findAllByRankingId(rankingId);
    }

    public Question getById(Long questionId) {
        Optional<Question> question = repo.findById(questionId);
        return question.orElse(null);
    }

    public void delete(Long questionId) {
        Optional<Question> question = repo.findById(questionId);
        question.ifPresent(repo::delete);
    }

    public void deleteAllByRankingId(Long rankingId) {
        List<Question> questions = repo.findAllByRankingId(rankingId);
        repo.deleteAll(questions);
    }
}
