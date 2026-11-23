package com.rexael.sqlidemo.repository;
import com.rexael.sqlidemo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void save(User user) {

        String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

        jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }
    public User findByEmailAndPassword(String email, String password) {

        String sql =
                "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {

                        User user = new User();

                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setCreatedAt(
                                rs.getTimestamp("created_at")
                        );
                        user.setActive(
                                rs.getBoolean("is_active")
                        );

                        return user;
                    },
                    email,
                    password
            );

        } catch (Exception e) {
            return null;
        }
    }
    public User findUserVulnerable(String email, String password) {

        String sql =
                "SELECT * FROM users WHERE email = '" + email +
                        "' AND password = '" + password + "'";

        System.out.println("VULNERABLE SQL: " + sql);

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {

                        User user = new User();

                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setCreatedAt(
                                rs.getTimestamp("created_at")
                        );
                        user.setActive(
                                rs.getBoolean("is_active")
                        );

                        return user;
                    }
            );

        } catch (Exception e) {
            return null;
        }
    }
}
