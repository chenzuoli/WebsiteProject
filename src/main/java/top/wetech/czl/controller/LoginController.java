package top.wetech.czl.controller;

import com.jfinal.core.Controller;
import top.wetech.czl.model.User;

import java.util.List;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: Chen
 * Date: 2018/2/3
 * Time: 14:43
 * Description: 登录控制器
 */

public class LoginController extends Controller {
    private User user = new User();

    public void index(){
        render("/login.html");
//        List<User> users = User.dao.findByNameAndPassword(getPara("name"), getPara("password"));
//        if (users.size() > 0){
//            // 找到用户
//            setSessionAttr("userInfo", users.get(0));
//            render("/ok.jsp");
//        } else {
//            render("/no.jsp");
//        }
    }

    public void login(){
        String userid = getPara("userid");
        String password = getPara("password");
        List<User> users = user.findByNameAndPassword(userid, password);
        User user = null;
        if (users.size() > 0){
            user = users.get(0);
        }
        renderJson(user);
    }

}
