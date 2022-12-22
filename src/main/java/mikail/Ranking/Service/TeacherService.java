package mikail.Ranking.Service;

import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repo;

    public void create(final String name, final Long rankingId) {

        Optional<Teacher> teacher = Optional.ofNullable(repo.findExact(name, rankingId));
        if(teacher.isPresent()) {return;}

        repo.save(new Teacher(name, rankingId));
    }

    public void deleteAllByRankingId(Long rankingId) {
        List<Teacher> teachers = repo.findAllByRankingId(rankingId);
        repo.deleteAll(teachers);
    }
}
