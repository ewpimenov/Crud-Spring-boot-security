package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public List<Role> getAllRolesByName() {
        return roleRepository.getAllRolesByName();
    }

    @Override
    @Transactional
    public List<Role> getRolesByName(String[] role) {
        return roleRepository.getRolesByName(role);
    }

}
