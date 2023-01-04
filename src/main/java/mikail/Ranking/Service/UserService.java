package mikail.Ranking.Service;

import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.RankingUser;
import mikail.Ranking.Entity.Role;
import mikail.Ranking.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final RankingService rankingService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public void create(String name, String username, String email, String password) {

        // create user only if the user doesn't exist
        RankingUser user = repo.findByUsername(username);
        if (user == null) {
            repo.save(
                    RankingUser.builder()
                            .name(name)
                            .username(username)
                            .email(email)
                            .password(passwordEncoder.encode(password))
                            .build());
        }
    }

    public void create(String username, String password) {
        // create user only if the user doesn't exist
        RankingUser user = repo.findByUsername(username);
        if (user == null) {
            repo.save(
                    RankingUser.builder()
                            .username(username)
                            .password(passwordEncoder.encode(password))
                            .build());
        }
    }

    @Transactional
    public void addRole(String username, String roleName) {
        // create user only if the user doesn't exist
        RankingUser user = repo.findByUsername(username);
        Role role = roleService.getByName(roleName);
        if (user != null && role != null) {
            if(user.getRoles() == null) {
                user.addRole(role);
                return;
            }
            if(!user.getRoles().contains(role)) {
                user.addRole(role);
            }
        }
    }

    public RankingUser getById(Long id) {
        Optional<RankingUser> user = repo.findById(id);
        return user.orElse(null);
    }

    public RankingUser getByUsername(String username) {
        return repo.findByUsername(username);
    }

    public List<RankingUser> getAll() { return repo.findAll();}

    public void delete(Long userId) {
        Optional<RankingUser> user = repo.findById(userId);
        user.ifPresent(user1 -> {
            rankingService.deleteAllByUserId(userId);
            repo.delete(user1);
        });
    }
}
