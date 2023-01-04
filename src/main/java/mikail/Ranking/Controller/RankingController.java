package mikail.Ranking.Controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.Ranking;
import mikail.Ranking.Model.RankingResponseModel;
import mikail.Ranking.Security.MyUserPrincipal;
import mikail.Ranking.Service.ModelGenerator;
import mikail.Ranking.Service.QuestionService;
import mikail.Ranking.Service.RankingService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RankingController {

    private final RankingService service;
    private final QuestionService questionService;
    private final ModelGenerator modelGenerator;

    @PostMapping("/create")
    public ResponseEntity<Ranking> create(@NonNull @RequestBody String data, Authentication auth){
        JSONObject json = new JSONObject(data);
        String title = null;
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();

        try {
            title = json.getString("title");
        }catch (JSONException ignored) {
            return ResponseEntity.badRequest().build();
        }

        Ranking ranking = service.findByUserIdAndTitle(principal.getUser().getId(), title);
        if (ranking != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        service.create(title, principal.getUser().getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/allMine")
    public ResponseEntity<List<Ranking>> getFromUser(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();

        return ResponseEntity.ok().body(service.findAllFromUser(principal.getUser().getId()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Ranking> get(@PathVariable Long id, Authentication auth) {

        // no authentication
        if (auth == null) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();
        Ranking ranking = service.getById(id);

        // no ranking found
        if (ranking == null) {return ResponseEntity.notFound().build();}

        // forbidden if not the creator of the ranking
        if (!Objects.equals(ranking.getUserId(), principal.getUser().getId()))
        {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}

        return ResponseEntity.ok().body(ranking);
    }

    @GetMapping("/search/{title}") // permit all
    public List<Ranking> search(@PathVariable String title) {
        return service.search(title);

    }

    @PatchMapping("/update/{id}")
    @Transactional
    public ResponseEntity<Ranking> update(@PathVariable Long id, @RequestBody String data, Authentication auth) {
        JSONObject json = new JSONObject(data);
        String title = null;
        // no authentication
        if (auth == null) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();
        Ranking ranking = service.getById(id);

        // no ranking found
        if (ranking == null) {return ResponseEntity.notFound().build();}

        // forbidden if user isn't the owner of this ranking
        if (!Objects.equals(ranking.getUserId(), principal.getUser().getId()))
        {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}

        try {
            title = json.getString("title");
        } catch (JSONException ignored) {
            return ResponseEntity.badRequest().build();
        }
        ranking.setTitle(title);
        return ResponseEntity.ok().body(ranking);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, Authentication auth) {

        // no authentication
        if (auth == null) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();
        Ranking ranking = service.getById(id);

        // no ranking found
        if (ranking == null) {return ResponseEntity.notFound().build();}

        // forbidden if not the creator of the ranking
        if (!Objects.equals(ranking.getUserId(), principal.getUser().getId()))
        {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}

        service.delete(id);
        return ResponseEntity.ok().body("deleted successfully");
    }
}
