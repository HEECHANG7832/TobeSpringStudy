import Strategy.AddStatement;
import Strategy.DeleteAllStatement;
import Strategy.StatementStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDao {

    private JdbcContext jdbcContext;
    private JdbcTemplate jdbcTemplate;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(User user) throws ClassNotFoundException, SQLException{
        this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
    }

    public User get(String id) throws ClassNotFoundException, SQLException{
        return this.jdbcTemplate.queryForObject("select * from users where id = ?",
                new Object[] {id},
                new RowMapper<User>(){
                    public User mapRow(ResultSet rs, int rowNum)
                            throws SQLException{
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword("password");
                        return user;
                    }
                });

    }

    public List<User> getAll(){
        return this.jdbcTemplate.query("select * from users order by id",
                new RowMapper<User>(){
                    public User mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                });
    }

    public void deleteAll() throws SQLException{
        this.jdbcTemplate.update("delete from user_defined_type_schema; ");
    }


    /*public void jdbcContextWithStatementStratege(StatementStrategy stmt) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;

        try{
            c = dataSource.getConnection();

            ps = stmt.makePreparedStatement(c);

            ps.executeUpdate();
        }catch(SQLException e){
            throw e;
        }finally{
            if (ps != null) {try {ps.close();} catch (SQLException e){}}
            if (c != null) {try {c.close();} catch (SQLException e){}}
        }

    }*/

    public int getCount() throws SQLException{
        return this.jdbcTemplate.queryForInt("select count(*) from users");
    }
}
