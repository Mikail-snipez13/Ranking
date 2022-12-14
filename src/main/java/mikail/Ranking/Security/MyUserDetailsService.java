package mikail.Ranking.Security;

import mikail.Ranking.Entity.RankingUser;
import mikail.Ranking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        RankingUser user = service.getByNickname(nickname);
        if (user == null) {
            throw new UsernameNotFoundException(nickname);
        }
        return new MyUserPrincipal(user);
    }
}
