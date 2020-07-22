package diplomna.service.serviceImpl;

import diplomna.model.entity.UserRole;
import diplomna.repository.RoleRepository;
import diplomna.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void seedRolesToDb() {
        if (this.roleRepository.count() == 0){
            this.roleRepository.saveAndFlush(new UserRole("ADMIN"));
            this.roleRepository.saveAndFlush(new UserRole("USER"));
        }
    }
}
