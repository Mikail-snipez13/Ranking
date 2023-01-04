package mikail.Ranking.Service;

import mikail.Ranking.Entity.Role;
import mikail.Ranking.Interface.SimpleService;
import mikail.Ranking.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements SimpleService<Role> {

    @Autowired
    private RoleRepository repo;

    @Override
    public void create(String name) {
        Role role = repo.findByName(name);
        if (role == null) {
            repo.save(new Role(null, name));
        }
    }

    @Override
    public Role get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Role getByName(String name) {
        return repo.findByName(name);
    }
}
