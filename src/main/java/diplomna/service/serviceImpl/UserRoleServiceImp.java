package diplomna.service.serviceImpl;

import diplomna.model.bindingmodel.SaveNewRolesBindingModel;
import diplomna.model.entity.User;
import diplomna.model.entity.UserRole;
import diplomna.model.service.SaveNewRolesServiceModel;
import diplomna.model.service.UserRoleServiceModel;
import diplomna.repository.RoleRepository;
import diplomna.repository.UserRepository;
import diplomna.service.UserRoleService;
import diplomna.web.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImp implements UserRoleService {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final Tools tools;
    private final UserRepository userRepository;

    public UserRoleServiceImp(ModelMapper modelMapper, RoleRepository roleRepository, Tools tools, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.tools = tools;
        this.userRepository = userRepository;
    }

    @Override
    public void saveNewRoleToUser(SaveNewRolesServiceModel snrbm) {
        User user = this.userRepository.findById(snrbm.getUserId()).orElse(null);
        //some error
        user.getAuthorities().clear();
        UserRole userRole = this.roleRepository.findByAuthority(snrbm.getNewRole()).orElse(null);
        user.getAuthorities().add(userRole);
        this.userRepository.saveAndFlush(user);
    }
}
