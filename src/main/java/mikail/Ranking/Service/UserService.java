package mikail.Ranking.Service;

import mikail.Ranking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(String name, String nickname, String email, String password) {
        // TODO
    }
}
