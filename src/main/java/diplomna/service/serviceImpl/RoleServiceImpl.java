package diplomna.service.serviceImpl;

import diplomna.error.exception.UserIsNotExistException;
import diplomna.model.entity.User;
import diplomna.model.entity.UserRole;
import diplomna.model.service.SaveNewRolesServiceModel;
import diplomna.repository.RoleRepository;
import diplomna.repository.UserRepository;
import diplomna.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void seedRolesToDb() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new UserRole("ADMIN"));
            this.roleRepository.saveAndFlush(new UserRole("USER"));
        }
    }

}
