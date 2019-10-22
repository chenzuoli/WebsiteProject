package top.wetech.czl.controller;

import com.jfinal.core.Controller;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import top.wetech.czl.model.User;

import java.util.List;

/**
 * company: wetech.top
 * user: chenzuoli
 * date: 2019/1/25 21:25
 * description:
 */
public class UserController extends Controller {
    private static Logger logger = Logger.getLogger(UserController.class);

    public void changepwd(){
        String uid = getPara("uid");
        String pwd = getPara("pwd");
        if (pwd == null) pwd = "";
        List<User> users = User.dao.findById(uid);
        JSONObject returnJson = new JSONObject();
        if (users.size() == 0) {
            returnJson.put("statCode", "720");
            renderJson(returnJson);
            return;
        }
        boolean isupdated = User.dao.changepwd(uid, pwd);
        if (isupdated) {
            returnJson.put("statCode", "709");
        } else {
            returnJson.put("statCode", "730");
        }
        renderJson(returnJson);
    }


    public void changeinfo() {
        String uid = getPara("uid");
        String uname = getPara("uname");
        String mobile = getPara("mobile");
        String email = getPara("email");
        String qq = getPara("qq");
        String wechat = getPara("wechat");
        if (uname == null) uname = "";
        if (mobile == null) mobile = "";
        if (email == null) email = "";
        if (qq == null) qq = "";
        if (wechat == null) wechat = "";
        List<User> users = User.dao.findById(uid);
        JSONObject returnJson = new JSONObject();
        if (users.size() == 0) {
            returnJson.put("statCode", "720");
            renderJson(returnJson);
            return;
        }
        boolean isupdated = User.dao.changeinfo(uid, uname, mobile, email, qq, wechat);
        if (isupdated) {
            returnJson.put("statCode", "709");
        } else {
            returnJson.put("statCode", "730");
        }
        renderJson(returnJson);
    }

}
