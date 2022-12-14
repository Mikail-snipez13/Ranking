package mikail.Ranking.Service;

import mikail.Ranking.Entity.Vote;
import mikail.Ranking.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository repo;

    public void create(Long questionId, Long teacherId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        Date date = new Date(System.currentTimeMillis());
        String dateString = dateFormat.format(date);
        repo.save(new Vote(questionId, teacherId, dateString));
    }

    public List<Vote> getAllByQuestionId(Long questionId) {
        return repo.findAllByQuestionId(questionId);
    }
}
