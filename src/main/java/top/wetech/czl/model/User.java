package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: Chen
 * Date: 2018/2/3
 * Time: 14:31
 * Description: 用户实体类
 */

public class User extends Model<User> {

    public static final User dao = new User();

    /**
     * 查找用户，用于登录
     *
     * @param uid      用户id
     * @param password 密码
     * @return
     */
    public List<User> findByNameAndPassword(String uid, String password) {
        List<User> users = find("select * from users where uid = '" + uid + "' and password = '" + password + "'");
        return users;
    }

    /**
     * 存储用户信息，用于注册
     *
     * @param uid      用户id
     * @param password 密码
     */
    public void saveUser(String uid, String password) {
        set("uid", uid).set("password", password).save();
    }

}
