package mikail.Ranking.Service;

import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repo;

    public Question create(String text, Long rankingId) {
        return new Question(text, rankingId);
    }

    @Transactional
    public void updateText(Long questionId, String text) throws NullPointerException{
        Optional<Question> question = repo.findById(questionId);
        question.ifPresent(quest -> {
            quest.setText(text);
        });
    }

}
