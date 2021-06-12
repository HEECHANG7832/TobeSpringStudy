import org.example.TobeSpringStudy.controller.User;
import org.example.TobeSpringStudy.controller.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {

    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);
        User user1= new User("!234", "!234", "!234");
        User user2 = new User("!234", "!234", "!234");

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
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);

        User user1= new User("!234", "!234", "!234");
        User user2 = new User("!234", "!234", "!234");
        User user3 = new User("!234", "!234", "!234");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));

    }
}
