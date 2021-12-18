package mikail.Ranking.Controller;

import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping(value = "/teacher/all")
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @RequestMapping(value = "/teacher/best")
    public List<Teacher> getBest() {
        List<Teacher> teacherList = teacherRepository.findAll();
        for (Teacher teacher : teacherList) {

        }
        return teacherList;
    }
}
