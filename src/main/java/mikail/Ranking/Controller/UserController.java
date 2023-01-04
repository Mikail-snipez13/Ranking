package mikail.Ranking.Controller;

import mikail.Ranking.Entity.RankingUser;
import mikail.Ranking.Entity.Role;
import mikail.Ranking.Security.MyUserPrincipal;
import mikail.Ranking.Service.RoleService;
import mikail.Ranking.Service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<RankingUser> create(@RequestBody String data, Authentication auth) {

        // BAD REQUEST: no authentication
        if (auth == null) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        JSONObject json = new JSONObject(data);
        String name = null;
        String username = null;
        String email = null;
        String password = null;
        try {
            name = json.getString("name");
        } catch (JSONException ignored) {}
        try {
            email = json.getString("email");
        } catch (JSONException ignored) {}
        try {
            username = json.getString("username");
            password = json.getString("password");
        } catch (JSONException ignored)
        // BAD REQUEST: missing required data
        {return ResponseEntity.badRequest().build();}

        // FORBIDDEN: user already exists
        RankingUser user = service.getByUsername(username);
        if (user != null) {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}

        service.create(name, username, email, password);
        service.addRole(username, "USER");
        RankingUser newUser = service.getByUsername(username);
        newUser.setPassword("secret");
        return ResponseEntity.ok().body(newUser);
    }

    @GetMapping("/get/{username}")
    public RankingUser get(@PathVariable String username) {
        return service.getByUsername(username);
    }

    @GetMapping("/get/me")
    public RankingUser getMe(Authentication auth) {
        if (auth != null) {
            MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();
            RankingUser user = principal.getUser();
            user.setPassword("secret");
            return user;
        }
        return null;
    }

//    @GetMapping("/id/{id}")
    @GetMapping("/get/usernameById/{id}")
    public String getId(@PathVariable Long id) {
        return service.getById(id).getUsername();
    }



    @GetMapping("/all")
    public List<RankingUser> getAll() {
        return service.getAll();
    }

    @PatchMapping("/update/{username}")
    @Transactional
    public ResponseEntity<RankingUser> update(@RequestBody String data, @PathVariable String username, Authentication auth) {

        // BAD REQUEST: no authentication
        if (auth == null) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();

        // FORBIDDEN: if not admin or the same user
        boolean isAdmin = false;
        for (GrantedAuthority authority : principal.getAuthorities()) {
            if (authority.getAuthority().equals("ADMIN")) {
                isAdmin = true;
                break;
            }
        }
        if (!(isAdmin || principal.getUsername().equals(username)))
        {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}

        // NOT FOUND: user doesn't exist
        RankingUser user = service.getByUsername(username);
        if (user == null) {return ResponseEntity.notFound().build();}

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
            newNick = json.getString("username");
            if (service.getByUsername(newNick) == null) {
                user.setUsername(newNick);
            }
        } catch (JSONException ignored) {}
        try {
            password = json.getString("password");
            user.setPassword(passwordEncoder.encode(password));
        } catch (JSONException ignored) {}

        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, Authentication auth) {

        // BAD REQUEST: no authentication
        if (auth == null) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        MyUserPrincipal principal = (MyUserPrincipal) auth.getPrincipal();

        // NOT FOUND: user doesn't exist
        RankingUser user = service.getById(id);
        if (user == null) {return ResponseEntity.notFound().build();}

        service.delete(id);
        return ResponseEntity.ok().body("deleted successfully");
    }
}
