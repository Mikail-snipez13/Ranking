package mikail.Ranking.Controller;

import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Interface.SimpleJSONEntityCreator;
import mikail.Ranking.Repository.TeacherRepository;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController implements SimpleJSONEntityCreator<Teacher> {
    private final TeacherRepository teacherRepo;

    @Override
    @PostMapping("/create")
    public void create(@RequestBody String data) {
        System.out.println("new teacher instance");
        JSONObject json = new JSONObject(data);
        String firstname = json.getString("firstname");
        String lastname = json.getString("lastname");

        teacherRepo.save(new Teacher(1L, firstname, lastname));
    }

    @GetMapping("/get")
    public Teacher get(@RequestParam Long id) {
        Optional<Teacher> opt = teacherRepo.findById(id);
        return opt.orElse(null);
    }

   @GetMapping("/hello")
   public String hello() {
        return "Hello";
   }
}
