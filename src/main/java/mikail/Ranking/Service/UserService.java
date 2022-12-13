package mikail.Ranking.Service;

import lombok.RequiredArgsConstructor;
import mikail.Ranking.Entity.RankingUser;
import mikail.Ranking.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final RankingService rankingService;


    public void create(String name, String nickname, String email, String password) {

        // create user only if the user doesn't exist
        RankingUser user = repo.findByNickname(nickname);
        if (user == null) {
            repo.save(
                    RankingUser.builder()
                            .name(name)
                            .nickname(nickname)
                            .email(email)
                            .password(password)
                            .build());
        }
    }

    public RankingUser getById(Long id) {
        Optional<RankingUser> user = repo.findById(id);
        return user.orElse(null);
    }

    public RankingUser getByNickname(String nickname) {
        return repo.findByNickname(nickname);
    }

    public void delete(Long userId) {
        Optional<RankingUser> user = repo.findById(userId);
        user.ifPresent(user1 -> {
            rankingService.deleteAllByUserId(userId);
            repo.delete(user1);
        });
    }
}
