package top.wetech.czl.controller;

import com.jfinal.core.Controller;
import top.wetech.czl.model.User;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: Chen
 * Date: 2018/2/3
 * Time: 14:46
 * Description: 注册控制器
 */

public class RegisterController extends Controller {

    public void index(){
//        String name = getPara("name");
//        String password = getPara("password");
//        if (name == null || password == null){
//            renderText("用户名或密码不能为空。");
//            return;
//        }
//        User.dao.saveUser(name, password);
//        render("/theOK.jsp");
        render("/register.jsp");
    }

}
