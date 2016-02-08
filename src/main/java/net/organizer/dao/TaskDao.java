package net.organizer.dao;

import net.organizer.dto.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nikolay on 16.12.2015.
 */
public class TaskDao {

    private static final Logger LOGGER = Logger.getLogger(TaskDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_BY_AUTHOR = "SELECT * FROM task WHERE author = ? AND done = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM task WHERE id = ?";
    private static final String INSERT = "INSERT INTO task (name, creationDate, targetDate, author) VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM task WHERE id=?";
    private static final String UPDATE_STATUS = "UPDATE task SET done = true WHERE id = ?";
    private static final String UPDATE = "UPDATE task SET name = ?, targetDate = ? WHERE id=?";

    public List<Task> getByAuthor(Integer authorId, boolean done) {
        List<Task> tasks = jdbcTemplate.query(SELECT_BY_AUTHOR, new TaskRowMapper(), authorId, done);
        return tasks;
    }

    public Task getById(Integer id) {
        Task task = jdbcTemplate.queryForObject(SELECT_BY_ID, new TaskRowMapper(), id);
        return task;
    }

    public void add(Task task) {
        jdbcTemplate.update(INSERT, task.getName(), task.getCreationDate(), task.getTargetDate(), task.getAuthorId());
    }

    public void delete(Integer id) throws Exception {
        jdbcTemplate.update(DELETE, id);
    }

    public void complete(Integer id) throws Exception {
        jdbcTemplate.update(UPDATE_STATUS, id);
    }


    public void update(Task task) {
        jdbcTemplate.update(UPDATE, task.getName(), task.getTargetDate(), task.getId());
    }


    public static class TaskRowMapper implements RowMapper<Task> {

        @Override
        public Task mapRow(ResultSet resultSet, int i) throws SQLException{
            Task task = new Task();
            task.setId(resultSet.getInt("id"));
            task.setName(resultSet.getString("name"));
            task.setCreationDate(resultSet.getDate("creationDate"));
            task.setTargetDate(resultSet.getDate("targetDate"));
            task.setDone(resultSet.getBoolean("done"));
            return task;
        }
    }
}
