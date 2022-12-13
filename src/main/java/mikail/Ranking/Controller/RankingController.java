package mikail.Ranking.Controller;

import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Service.RankingService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService service;

    @PostMapping("/create")
    public void create(@RequestBody String data){
        JSONObject json = new JSONObject(data);
        String title = json.getString("title");
        Long userId = json.getLong("userId");

        service.create(title, userId);
    }

    @GetMapping("/get/allFromUser/{id}")
    public List<Ranking> getFromUser(@PathVariable Long id) {
        return service.findAllFromUser(id);
    }

    @GetMapping("/get/{id}")
    public Ranking get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody String data) {
        try {
            JSONObject json = new JSONObject(data);
            String title = json.getString("title");
            Ranking ranking = service.getById(id);
            ranking.setTitle(title);
        } catch (JSONException ignored) {}
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
