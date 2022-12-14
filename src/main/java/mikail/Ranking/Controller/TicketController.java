package mikail.Ranking.Controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Ticket;
import mikail.Ranking.Service.QuestionService;
import mikail.Ranking.Service.TicketService;
import mikail.Ranking.Service.VoteService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;
    private final QuestionService questionService;
    private final VoteService voteService;

    @GetMapping("/create")
    public List<String> create(
            @NonNull @RequestParam(value = "rankingId") Long rankingId,
            @RequestParam(value = "n", defaultValue = "1") int n
            ) {
        List<String> tickets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tickets.add(service.create(rankingId).getKey());
        }
        return tickets;
    }

    @GetMapping("/get/allFromRanking/{rankingId}")
    public List<Ticket> getAllByRankingId(@PathVariable Long rankingId) {
        return service.getAllByRankingId(rankingId);
    }

    @GetMapping("/get/{id}")
    public Ticket get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/use")
    public String use(
            @RequestParam(value = "rankingId") Long rankingId,
            @RequestParam(value = "key") String key,
            @RequestBody String data
    ) {
        Ticket ticket = service.getByKeyAndRankingId(key, rankingId);
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
