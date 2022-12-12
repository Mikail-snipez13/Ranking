package mikail.Ranking.Service;

import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repo;

    public void create(final String firstname, final String lastname, Ranking ranking) {

        // TODO: check if the Teacher exists
//        Teacher teacher = repo.findExact(firstname, lastname, ranking.getId());

        repo.save(new Teacher(1L, firstname, lastname, ranking.getId()));
    }
}
