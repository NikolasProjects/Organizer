package net.organizer.dao;

import net.organizer.dto.Role;
import net.organizer.dto.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nikolay on 14.01.2016.
 */
public class UserDao {
    private static final Logger LOGGER = Logger.getLogger(TaskDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String INSERT = "INSERT INTO users (username, password, name, enabled, role_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ROLES = "SELECT * FROM user_roles";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

    public User getUserByUsername(String login) {
        User user = jdbcTemplate.queryForObject(SELECT_BY_USERNAME, new UserRowMapper(), login);
        return user;
    }

    public User getUserById(Integer id) {
        User user = jdbcTemplate.queryForObject(SELECT_BY_ID, new UserRowMapper(), id);
        return user;
    }

    public void addUser(User user) {
        jdbcTemplate.update(INSERT, user.getLogin(), user.getPassword(), user.getName(), user.getEnabled(), user.getRoleId());
    }

    public List<Role> getRoles() {
        List<Role> roles = jdbcTemplate.query(SELECT_ROLES, new RoleRowMapper());
        return roles;
    }

    public List<User> getUsers(){
        List<User> users = jdbcTemplate.query(SELECT_ALL, new UserRowMapper());
        return users;
    }

    public void delete(Integer id) throws Exception {
        jdbcTemplate.update(DELETE_USER_BY_ID, id);
    }

    public static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setEnabled(resultSet.getBoolean("enabled"));
            user.setRoleId(resultSet.getInt("role_id"));
            return user;
        }
    }

    public static class RoleRowMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setId(resultSet.getInt("id"));
            role.setAuthority(resultSet.getString("authority"));
            return role;
        }
    }
}
