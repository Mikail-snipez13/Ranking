package mikail.Ranking.Controller;

import mikail.Ranking.Entity.Question;
import mikail.Ranking.Service.QuestionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    }

    @GetMapping("/get/allFromRanking/{rankingId}")
    public List<Question> getAllByRankingId(@PathVariable Long rankingId) {
        return service.getAllByRankingId(rankingId);
    }

    @GetMapping("/get/{id}")
    public Question get(@PathVariable final Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id) {}

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {}
}
