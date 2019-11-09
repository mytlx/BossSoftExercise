package listener; /**
 * @author TLX
 * @date 2019.11.8
 * @time 23:48
 */

import com.mytlx.domain.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener()
public class UserListener implements HttpSessionAttributeListener, HttpSessionListener {



    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        System.out.println(session.getId());
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (null != user) {
            System.out.println("增加用户：id：" + session.getId() + ", user = " + user);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println(session.getId());
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (null != user) {
            System.out.println("删除用户：id：" + session.getId() + ", user = " + user);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(5);
    }
}
