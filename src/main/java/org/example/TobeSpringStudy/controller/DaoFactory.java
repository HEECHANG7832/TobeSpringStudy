package org.example.TobeSpringStudy.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao{
        return new UserDao(connectionMaker());
    }

    @Bean
    public UserDao userDao{
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        return new LocalConnectionMaker();
    }
}
