package mikail.Ranking;

import mikail.Ranking.Controller.TeacherController;
import mikail.Ranking.Repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeacherEndpoint {

//    @Autowired
//    private TeacherRepository teacherRepo;

    @Autowired
    private TeacherController teacherController;

    @Test
    void createTeachers() {
        String data = "{\"firstname\": \"mikail\", \"lastname\": \"guenduez\"}";
        teacherController.create(data);
    }
}
