package BookShop.web;

import BookShop.pojo.User;
import BookShop.service.UserService;
import BookShop.service.impl.UserServiceImpl;
import BookShop.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /*--------------------------------登陆业务-----------------------------------------*/
    /**
     * 处理登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 调用 userService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));
        // 如果等于 null,说明登录 失败!
        if (loginUser == null) {
        // 把错误信息，和回显的表单项信息，保存到 Request 域中
            req.setAttribute("msg","用户或密码错误！");
            req.setAttribute("username", username);
        // 跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录 成功

            //登陆成功后要把用户名显示在页面上，几乎每个页面都要显示用户名来提示用户已经成功登陆了
            //所以我们要把它放在Session中
            req.getSession().setAttribute("user",loginUser);

            //跳到成功页面 login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /*----------------------------------用户注销业务-------------------------------------------------*/

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、销毁Session
        req.getSession().invalidate();

        //2、重定向到首页
        resp.sendRedirect(req.getContextPath());
    }
    /*---------------------------------注册业务--------------------------------------------*/
    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //因为要更新验证码；所以要立马删除
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);




        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = new User();
        Map<String, String[]> parameterMap = req.getParameterMap();
        for(Map.Entry<String,String[]> entry : parameterMap.entrySet())
        {
            System.out.println(entry.getKey() + "=" + Arrays.asList(entry.getValue()));
        }
        WebUtils.CopyparamToBean(req.getParameterMap(),user);


        // 2、检查 验证码是否正确 === 写死,要求验证码为:abcde
        if (token!=null && token.equalsIgnoreCase(code)) {
                 // 3、检查 用户名是否可用
            if (userService.existUsername(username)) {
                System.out.println("用户名[" + username + "]已存在!");
                    // 把回显信息，保存到 Request 域中
                req.setAttribute("msg", "用户名已存在！！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                // 跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // 可用
                 // 调用 Sservice 保存到数据库
                userService.registUser(new User(null, username, password, email));
                // 跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            // 把回显信息，保存到 Request 域中
            req.setAttribute("msg", "验证码错误！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

/*-----------------------------------------ajax操作----------------------------------------------------*/
    protected void ajaxUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existUsername = userService.existUsername(username);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("existUsername",existUsername);

        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }
}
