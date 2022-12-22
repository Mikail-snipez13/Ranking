package mikail.Ranking.Controller;

import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Teacher;
import mikail.Ranking.Interface.SimpleService;
import mikail.Ranking.Repository.TeacherRepository;
import mikail.Ranking.Service.TeacherService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TeacherController {
    private final TeacherRepository teacherRepo;
    private final TeacherService service;

    @PostMapping("/create")
    public void create(@RequestBody final String data) {
        JSONObject json = new JSONObject(data);
        String name = json.getString("name");
        Long rankingId = json.getLong("rankingId");

        service.create(name, rankingId);
    }

    @GetMapping("/get/allFromRanking/{id}")
    public List<Teacher> getAllFromRanking(@PathVariable Long id) {
        return teacherRepo.findAllByRankingId(id);
    }

    @GetMapping("/get/{id}")
    public Teacher get(@PathVariable Long id) {
        Optional<Teacher> opt = teacherRepo.findById(id);
        return opt.orElse(null);
    }

    @PatchMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody final String data) {

        JSONObject json = new JSONObject(data);

        Optional<Teacher> teacherOpt = teacherRepo.findById(id);
        if (teacherOpt.isEmpty()) {return;}
        Teacher teacher = teacherOpt.get();
        try {
            String name = json.getString("name");
            teacher.setName(name);
        } catch (JSONException ignored) {}

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
}
