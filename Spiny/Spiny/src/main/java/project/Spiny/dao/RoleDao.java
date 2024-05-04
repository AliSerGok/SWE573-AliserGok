package project.Spiny.dao;


import project.Spiny.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
