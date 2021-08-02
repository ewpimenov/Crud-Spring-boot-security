package web.service;

import web.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRolesByName();

    List<Role> getRolesByName(String[] role);
}
