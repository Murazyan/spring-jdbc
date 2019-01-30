package com.example.springjdbc.repo;

import com.example.springjdbc.model.User;
import com.example.springjdbc.rowMaper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class UserRepository {

    private  JdbcTemplate jdbcTemplate;


    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public  List<User> getAllUser(){
        List<User> users = jdbcTemplate.query("select id, nickname from event_tracker.user", new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User actor = new User();
                actor.setId(rs.getInt("id"));
                actor.setName(rs.getString("nickname"));
                return actor;
            }
        });
        return users;
    }

    public User getUserById(int id){
        String query = "SELECT * FROM user WHERE ID = ?";
        User user = jdbcTemplate.queryForObject(
                query, new Object[]{id}, new UserRowMapper());
        return user;
    }
}
