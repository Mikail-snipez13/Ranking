package mikail.Ranking.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RankingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String[] roles;


//    public RankingUser(String nickname, String password) {
//        this.nickname = nickname;
//        this.password = password;
//    }
//
//    public RankingUser(String name, String nickname, String email, String password) {
//        this.name = name;
//        this.nickname = nickname;
//        this.email = email;
//        this.password = password;
//        this.roles = new ArrayList<>();
//    }
//
    public void addRole(String role) {
        if (this.roles == null) {
            String[] roles = new String[1];
            roles[0] = role;
            this.roles = roles;
        }
        else {
            List<String> roles = Arrays.asList(this.roles);
            roles.add(role);
            this.roles = roles.toArray(new String[0]);
        }
    }
}
