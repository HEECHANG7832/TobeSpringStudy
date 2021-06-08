package org.example.TobeSpringStudy.controller;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException{
        Connection c = dataSource.getConnection();
    }

    public User get(String id) throws ClassNotFoundException, SQLException{
        Connection c = dataSource.getConnection();
    }
}
