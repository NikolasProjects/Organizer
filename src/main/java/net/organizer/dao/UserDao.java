package net.organizer.dao;

import net.organizer.dto.AuthUser;
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

    private static final String SELECT_ALL = "SELECT * FROM users INNER JOIN user_roles ON users.role_id = user_roles.role_id";
    private static final String INSERT = "INSERT INTO users (username, password, name, enabled, role_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_USERNAME = "SELECT * FROM users INNER JOIN user_roles ON users.role_id = user_roles.role_id WHERE username = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM users INNER JOIN user_roles ON users.role_id = user_roles.role_id WHERE id = ?";
    private static final String SELECT_ROLES = "SELECT * FROM user_roles";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE = "UPDATE users SET password = ?, name = ?, enabled = ?, role_id = ? WHERE id = ?";
    private static final String SELECT_NAME_BY_LOGIN = "SELECT name FROM users WHERE username = ?";
    private static final String SELECT_AUTH_USER = "SELECT u.id, u.name, r.authority FROM users u INNER JOIN user_roles r ON u.role_id = r.role_id WHERE u.username = ?";

    public User getUserByUsername(String login) {
        User user = jdbcTemplate.queryForObject(SELECT_BY_USERNAME, new UserRowMapper(), login);
        return user;
    }

    public User getUserById(Integer id) {
        User user = jdbcTemplate.queryForObject(SELECT_BY_ID, new UserRowMapper(), id);
        return user;
    }

    public void addUser(User user) {
        jdbcTemplate.update(INSERT, user.getLogin(), user.getPassword(), user.getName(), user.getEnabled(), user.getRole().getId());
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

    public void update(User user) {
        jdbcTemplate.update(UPDATE, user.getPassword(), user.getName(), user.getEnabled(), user.getRole().getId(), user.getId());
    }

    public String getNameByLogin(String login) {
        String name = jdbcTemplate.queryForObject(SELECT_NAME_BY_LOGIN, String.class, login);
        return name;
    }

    public AuthUser getAuthUser(String login) {
        AuthUser authUser = jdbcTemplate.queryForObject(SELECT_AUTH_USER, new AuthUserRowMapper(), login);
        return authUser;
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
            Role role = new Role();
            role.setId(resultSet.getInt("role_id"));
            role.setAuthority(resultSet.getString("authority"));
            user.setRole(role);
            return user;
        }
    }

    public static class RoleRowMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setId(resultSet.getInt("role_id"));
            role.setAuthority(resultSet.getString("authority"));
            return role;
        }
    }

    public static class AuthUserRowMapper implements RowMapper<AuthUser> {

        @Override
        public AuthUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AuthUser authUser = new AuthUser();
            authUser.setId(resultSet.getInt("id"));
            authUser.setName(resultSet.getString("name"));
            authUser.setRole(resultSet.getString("authority"));
            return authUser;
        }
    }
}
