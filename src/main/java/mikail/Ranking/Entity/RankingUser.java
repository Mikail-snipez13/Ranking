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
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();


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
    public void addRole(Role role) {
        if (roles == null) {
            this.roles = new ArrayList<>();
        }
        roles.add(role);
    }

}
