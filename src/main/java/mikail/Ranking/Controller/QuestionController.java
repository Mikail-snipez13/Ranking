package mikail.Ranking.Controller;

import lombok.NonNull;
import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Security.MyUserPrincipal;
import mikail.Ranking.Service.QuestionService;
import mikail.Ranking.Service.RankingService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @Autowired
    private RankingService rankingService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody String data, Authentication auth) {
        JSONObject json = new JSONObject(data);
        String text = null;
        Long rankingId = null;

        if (auth == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            text = json.getString("text");
            rankingId = json.getLong("rankingId");
        } catch (JSONException ignored) {
            return ResponseEntity.badRequest().build();
        }

        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();
        Ranking ranking = rankingService.getById(rankingId);

        if (ranking == null) {
            return ResponseEntity.notFound().build();
        }

        if (!Objects.equals(ranking.getUserId(), principal.getUser().getId())) {
            return ResponseEntity.status(403).build();
        }

        service.create(text, rankingId);
        return ResponseEntity.ok().body("successfully created");
    }

    @GetMapping("/get/allFromRanking/{rankingId}")
    public ResponseEntity<List<Question>> getAllByRankingId(@NonNull @PathVariable Long rankingId) {
        return ResponseEntity.ok().body(service.getAllByRankingId(rankingId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Question> get(@PathVariable final Long id, Authentication auth) {

        if (auth == null) {
            return ResponseEntity.badRequest().build();
        }

        Question question = service.getById(id);

        if (question == null) {
            return ResponseEntity.notFound().build();
        }

        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();
        Ranking ranking = rankingService.getById(question.getRankingId());

        if (ranking == null) {
            return ResponseEntity.notFound().build();
        }

        if (!Objects.equals(ranking.getUserId(), principal.getUser().getId())) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok().body(question);
    }

    @PatchMapping("/update/{id}")
    @Transactional
    public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody String data, Authentication auth) {
        JSONObject json = new JSONObject(data);
        String text = null;

        if (auth == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            text = json.getString("text");
        } catch (JSONException ignored) {
            return ResponseEntity.badRequest().build();
        }

        Question question = service.getById(id);

        if (question == null) {
            return ResponseEntity.notFound().build();
        }

        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();
        Ranking ranking = rankingService.getById(question.getRankingId());

        if (ranking == null) {
            return ResponseEntity.notFound().build();
        }

        if (!Objects.equals(ranking.getUserId(), principal.getUser().getId())) {
            return ResponseEntity.status(403).build();
        }

        question.setText(text);
        return ResponseEntity.ok().body(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, Authentication auth) {

        if (auth == null) {
            return ResponseEntity.badRequest().build();
        }

        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();

        Question question = service.getById(id);
        if (question == null) {
            return ResponseEntity.notFound().build();
        }

        Ranking ranking = rankingService.getById(question.getRankingId());
        if (ranking == null) {
            return ResponseEntity.notFound().build();
        }

        if (!Objects.equals(ranking.getUserId(), principal.getUser().getId())) {
            return ResponseEntity.status(403).build();
        }

        service.delete(id);
        return ResponseEntity.ok().body("deleted successfully");
    }
}
