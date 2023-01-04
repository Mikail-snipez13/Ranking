package mikail.Ranking.Controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Entity.Ticket;
import mikail.Ranking.Security.MyUserPrincipal;
import mikail.Ranking.Service.QuestionService;
import mikail.Ranking.Service.RankingService;
import mikail.Ranking.Service.TicketService;
import mikail.Ranking.Service.VoteService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TicketController {

    private final TicketService service;
    private final QuestionService questionService;
    private final RankingService rankingService;
    private final VoteService voteService;

    @GetMapping("/create")
    public ResponseEntity<List<String>> create(
            @NonNull @RequestParam(value = "rankingId") Long rankingId,
            @RequestParam(value = "n", defaultValue = "1") int n,
            Authentication auth
            ) {
        if (auth == null) {
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

        List<String> tickets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tickets.add(service.create(rankingId).getCode());
        }
        return ResponseEntity.ok().body(tickets);
    }

    @GetMapping("/get/allFromRanking/{rankingId}")
    public List<Ticket> getAllByRankingId(@PathVariable Long rankingId) {
        return service.getAllByRankingId(rankingId);
    }

    @GetMapping("/get/{id}")
    public Ticket get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/isValid")
    public boolean isValid(
            @RequestParam(value = "key") String key,
            @RequestParam(value = "rankingId") Long rankingId ) {
        Ticket ticket = service.getByKeyAndRankingId(key, rankingId);
        if (ticket == null) {
            return false;
        }
        return ticket.getValid();
    }

    @PostMapping("/use")
    public String use(
            @RequestParam(value = "rankingId") Long rankingId,
            @RequestParam(value = "code") String code,
            @RequestBody String data
    ) {
        Ticket ticket = service.getByKeyAndRankingId(code, rankingId);
        if (ticket == null) {return "doesn't exists";}
        if (!ticket.getValid()) {return "ticket invalid";}

        // ticket task
        JSONObject json = new JSONObject(data);
        List<Question> questions = questionService.getAllByRankingId(rankingId);
        try {
            // check that doesn't crash
            questions.forEach(question -> {
                Long teacherId = json.getLong(String.valueOf(question.getId()));
            });

            // create votes
            questions.forEach(question -> {
                Long teacherId = json.getLong(String.valueOf(question.getId()));
                voteService.create(question.getId(), teacherId);
            });

            service.useTicket(ticket.getId());
            return "successfully used";
        } catch (JSONException ignored) {
            return "bad request";
        }
    }
}
