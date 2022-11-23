package mikail.Ranking.Service;

import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repo;

    public void create(final String firstname, final String lastname) {

        // TODO: check if the Teacher exists

        repo.save(new Teacher(1L, firstname, lastname));
    }
}
