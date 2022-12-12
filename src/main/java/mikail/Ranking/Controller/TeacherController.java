package mikail.Ranking.Controller;

import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Interface.SimpleJSONEntityCreator;
import mikail.Ranking.Repository.TeacherRepository;
import org.json.JSONException;
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
    public void create(@RequestBody final String data) {
        JSONObject json = new JSONObject(data);
        String firstname = json.getString("firstname");
        String lastname = json.getString("lastname");
        Long rankingId = json.getLong("rankingId");

        teacherRepo.save(new Teacher(1L, firstname, lastname, rankingId));
    }

    @GetMapping("/get/{id}")
    public Teacher get(@PathVariable Long id) {
        Optional<Teacher> opt = teacherRepo.findById(id);
        return opt.orElse(null);
    }

    @PutMapping("/update")
    public void update(@RequestBody final String data) {

        JSONObject json = new JSONObject(data);
        long id;
        try {
            id = json.getLong("id");
        }catch (JSONException e){ return;}

        Optional<Teacher> teacherOpt = teacherRepo.findById(id);
        if (teacherOpt.isEmpty()) {return;}
        Teacher teacher = teacherOpt.get();
        Optional<String> firstname = Optional.ofNullable(json.getString("firstname"));
        Optional<String> lastname = Optional.ofNullable(json.getString("lastname"));
        firstname.ifPresent(teacher::setFirstname);
        lastname.ifPresent(teacher::setLastname);

        teacherRepo.save(teacher);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable final Long id) throws NullPointerException{
        Optional<Teacher> teacher = teacherRepo.findById(id);
        if (teacher.isPresent()) {
            teacherRepo.delete(teacher.get());
        }
        else {
            throw new NullPointerException();
        }
    }

   @GetMapping("/hello")
   public String hello() {
        return "Hello";
   }
}
