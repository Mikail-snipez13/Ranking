package mikail.Ranking;

import mikail.Ranking.Entity.Question;
import mikail.Ranking.Service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionServiceTest {

    @Autowired
    private QuestionService service;

    @Test
    void create() {
        Question question = service.create("Hallo", 1L);
        System.out.println(String.format("ID: %d \nTEXT: %s", question.getId(), question.getText()));
    }
}
