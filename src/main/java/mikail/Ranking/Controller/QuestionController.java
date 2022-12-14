package mikail.Ranking.Controller;

import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Service.QuestionService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @PostMapping("/create")
    public void create(@RequestBody String data) {
        JSONObject json = new JSONObject(data);

        try {
            String text = json.getString("text");
            Long rankingId = json.getLong("rankingId");

            service.create(text, rankingId);
        } catch (JSONException ignored) {}

    }

    @GetMapping("/get/allFromRanking/{rankingId}")
    public List<Question> getAllByRankingId(@PathVariable Long rankingId) {
        return service.getAllByRankingId(rankingId);
    }

    @GetMapping("/get/{id}")
    public Question get(@PathVariable final Long id) {
        return service.getById(id);
    }

    @PatchMapping("/update/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody String data) {

        try {
            JSONObject json = new JSONObject(data);
            String title = json.getString("text");
            Question question = service.getById(id);
            question.setText(title);
        } catch (JSONException ignored) {}
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
