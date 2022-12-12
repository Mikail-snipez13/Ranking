package mikail.Ranking.Service;

import mikail.Ranking.Entity.User;
import mikail.Ranking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(String name, String nickname, String email, String password) {

        // create user only if the user doesn't exists
        Optional<User> user = Optional.ofNullable(userRepository.findByNickname(nickname));
        if (user.isEmpty()) {
            userRepository.save(
                    User.builder()
                            .name(name)
                            .nickname(nickname)
                            .email(email)
                            .password(password)
                            .build());
        }
    }
}
