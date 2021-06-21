import org.example.TobeSpringStudy.controller.User;
import org.example.TobeSpringStudy.controller.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
public class UserDaoTest {

    private User user1;
    private User user2;
    private User user3;

    @Autowired
    private UserDao dao;

    @Before
    public void setUP(){
        this.user1= new User("!234", "!234", "!234");
        this.user2 = new User("!234", "!234", "!234");
        this.user3 = new User("!234", "!234", "!234");
    }

    @Test
    public void addAndGet() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), is(User1.getName()));
        assertThat(userget1.getPassword(), is(User1.getPassword()));

        User userget2 = dao.get(user1.getId());
        assertThat(userget2.getName(), is(User1.getName()));
        assertThat(userget2.getPassword(), is(User1.getPassword()));
    }

    @Test
    public void count() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));

    }

    @Test(expected= EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException{
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }
}
