package BookShop.test;

import BookShop.utils.JdbcUtils;
import org.junit.Test;

public class JdbcUtilsTest {

    @Test
    public void test()
    {
        System.out.println(JdbcUtils.getConnection());
    }
}
