import Strategy.AddStatement;
import Strategy.DeleteAllStatement;
import Strategy.StatementStrategy;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException{
        StatementStrategy st = new AddStatement(user);
        jdbcContextWithStatementStratege(st);
    }

    public User get(String id) throws ClassNotFoundException, SQLException{
        Connection c = dataSource.getConnection();
    }

    public void deleteAll() throws SQLException{
        StatementStrategy st = new DeleteAllStatement();
        jdbcContextWithStatementStratege(st);
    }

    public void jdbcContextWithStatementStratege(StatementStrategy stmt) throws SQLException{
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

    }

    public int getCount() throws SQLException{
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }
}
