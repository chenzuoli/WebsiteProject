package top.wetech.czl.controller;

import com.jfinal.core.Controller;
import net.sf.json.JSONObject;
import top.wetech.czl.model.User;
import top.wetech.czl.util.StringUtil;

import java.util.List;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: Chen
 * Date: 2018/2/3
 * Time: 14:43
 * Description: 登录控制器
 */

public class LoginController extends Controller {

    public void index() {
        String uid = getPara("uid");
        String password = getPara("password");
        List<User> users = User.dao.findByNameAndPassword(uid, password);
        JSONObject returnJson = new JSONObject();
        JSONObject userInfo = new JSONObject();
        if (users.size() > 0) {
            User user = users.get(0);
            String[] attrNames = user.getAttrNames();
            for (String attrName : attrNames) {
                userInfo.put(attrName, user.get(attrName));
            }
            Object createtime = user.get("createtime");
            Object updatetime = user.get("updatetime");
            userInfo.put("createtime", createtime == null ? null : StringUtil.formatY_M_D_HMS.format(createtime));
            userInfo.put("updatetime", updatetime == null ? null : StringUtil.formatY_M_D_HMS.format(updatetime));
            returnJson.put("statCode", "709");
            returnJson.put("userInfo", userInfo);
        } else {
            returnJson.put("statCode", "710");
        }
        renderJson(returnJson);
    }

}
