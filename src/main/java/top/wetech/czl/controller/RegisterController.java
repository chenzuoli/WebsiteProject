package top.wetech.czl.controller;

import com.jfinal.core.Controller;
import net.sf.json.JSONObject;
import top.wetech.czl.model.User;

import java.util.List;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: Chen
 * Date: 2018/2/3
 * Time: 14:46
 * Description: 注册控制器
 */

public class RegisterController extends Controller {

    public void index(){
        String uid = getPara("uid");
        String password = getPara("password");
        List<User> users = User.dao.findById(uid);
        JSONObject returnJson = new JSONObject();
        if (users.size()>0){
            returnJson.put("statCode", "720");
            renderJson(returnJson);
            return;
        }
        boolean isSaved = User.dao.saveUser(uid, password);
        if (isSaved) {
            returnJson.put("statCode", "709");
        } else {
            returnJson.put("statCode", "730");
        }
        renderJson(returnJson);
    }

}
