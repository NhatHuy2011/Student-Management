package mapper;

import java.sql.ResultSet;

import enums.Sex;
import model.Role;
import model.User;

public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs) {
		try {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setFullname(rs.getString("fullname"));
			user.setEmail(rs.getString("email"));
			user.setDob(rs.getDate("dob"));
			user.setSex(Sex.valueOf(rs.getString("sex")));
			user.setAvatar(rs.getString("avatar"));
			try {
				Role role = new Role();
				role.setId(rs.getInt("roleId")); 
				role.setRole(rs.getString("role"));
				role.setDescription(rs.getString("description"));
				user.setRole(role);
			} catch(Exception e) {
				
			}
			return user;
		} catch (Exception e){
			return null;
		}
	}
	
}
