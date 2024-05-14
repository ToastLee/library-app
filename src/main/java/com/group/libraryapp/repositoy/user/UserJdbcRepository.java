package com.group.libraryapp.repositoy.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public  boolean isUserNotExist(long id) { //특정 필드만 내려주자
        String readSql = "SELECT * FROM user WHERE id = ?"; //데이터베이스에 존재하는지 확인
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateUserName(String name, long id) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    public boolean  isUserNotExist(String name) {
        String readSql = "SELECT * FROM user WHERE name = ?"; //데이터베이스에 존재하는지 확인
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void deleteUser(String name) {
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public void saveUser(String name, Integer age) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse>getUsers() {
        String sql = "SELECT * FROM user";
        //RowMapper 익명클래스
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age); // 쿼리 결과를 받아 객체 반환
        });
    }
}
