package mikail.Ranking.Controller;

import mikail.Ranking.Entity.RankingUser;
import mikail.Ranking.Service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    public void create(@RequestBody String data) {
        JSONObject json = new JSONObject(data);
        String name = null;
        String nickname = null;
        String email = null;
        String password = null;
        try {
            name = json.getString("name");
        } catch (JSONException ignored) {}
        try {
            nickname = json.getString("nickname");
        } catch (JSONException ignored) {return;}
        try {
            email = json.getString("email");
        } catch (JSONException ignored) {}
        try {
            password = json.getString("password");
        } catch (JSONException ignored) {return;}
        service.create(name, nickname, email, password);
    }

    @GetMapping("/get/{nickname}")
    public RankingUser get(@PathVariable String nickname) {
        return service.getByNickname(nickname);
    }

    @PutMapping("/update/{nickname}")
    @Transactional
    public void update(@RequestBody String data, @PathVariable String nickname) {
        RankingUser user = service.getByNickname(nickname);
        if (user == null) {return;}

        JSONObject json = new JSONObject(data);
        String name = null;
        String email = null;
        String newNick = null;
        String password = null;
        try {
            name = json.getString("name");
            user.setName(name);
        } catch (JSONException ignored) {}
        try {
            email = json.getString("email");
            user.setEmail(email);
        } catch (JSONException ignored) {}
        try {

            // TODO: need else response
            newNick = json.getString("nickname");
            if (service.getByNickname(newNick) == null) {
                user.setNickname(newNick);
            }
        } catch (JSONException ignored) {}
        try {
            password = json.getString("password");
            user.setPassword(password);
        } catch (JSONException ignored) {}
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
