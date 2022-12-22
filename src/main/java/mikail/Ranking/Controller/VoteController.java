package mikail.Ranking.Controller;

import mikail.Ranking.Entity.Question;
import mikail.Ranking.Entity.Vote;
import mikail.Ranking.Service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote")
@CrossOrigin("*")
public class VoteController {

    @Autowired
    private VoteService service;

    @GetMapping("/get/allFromQuestion/{questionId}")
    public List<Vote> getAllFromQuestion(@PathVariable Long questionId) {
        return service.getAllByQuestionId(questionId);
    }
}
