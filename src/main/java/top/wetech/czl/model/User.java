package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import top.wetech.czl.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Company: wetech.top
 * User: chenzuoli
 * Date: 2018/2/3
 * Time: 14:31
 * Description: 用户实体类
 */

public class User extends Model<User> {
    public static final User dao = new User();
    private String id;
    private String uid;
    private String uname;
    private String mobile;
    private String email;
    private long qq;
    private String wechat;
    private String password;
    private Date createtime;
    private Date updatetime;

    public User() {
    }

    public User(String id, String uid, String uname, String mobile, String email, long qq, String wechat, String password, Date createtime, Date updatetime) {
        this.id = id;
        this.uid = uid;
        this.uname = uname;
        this.mobile = mobile;
        this.email = email;
        this.qq = qq;
        this.wechat = wechat;
        this.password = password;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    /**
     * 查找用户，用于登录
     *
     * @param uid      用户id
     * @param password 密码
     * @return
     */
    public List<User> findByIdAndPassword(String uid, String password) {
        List<User> users = find("select id, uid, uname, mobile, email, qq, wechat, password, createtime, updatetime from users where uid = ? and password = ?", uid, password);
        return users;
    }

    public List<User> findById(String uid) {
        List<User> users = find("select id, uid, uname, mobile, email, qq, wechat, password, createtime, updatetime from users where uid = ?", uid);
        return users;
    }

    /**
     * 存储用户信息，用于注册
     *
     * @param uid      用户id
     * @param password 密码
     */
    public boolean saveUser(String uname, String uid, String password) {
        boolean isSaved = set("id", UUID.randomUUID().toString()).set("uname", uname).set("uid", uid).set("password", password).save();
        return isSaved;
    }

    public boolean changepwd(String uid, String password) {
        boolean isupdated = false;
        int update = Db.update("update users set password = ?, updatetime = ? where uid = ?", password, StringUtil.getCurTime() ,uid);
        if (update > 0) {
            isupdated = true;
        }
        return isupdated;
    }

    public  boolean changeinfo(String uid, String uname, String mobile, String email, String qq, String wechat) {
        boolean isupdated = false;
        int update = Db.update("update users set uname = ?, mobile = ?, email = ?, qq = ?, wechat = ?, updatetime = ? where uid = ?", uname, mobile, email, qq, wechat, StringUtil.getCurTime(), uid);
        if (update > 0) {
            isupdated = true;
        }
        return isupdated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getQq() {
        return qq;
    }

    public void setQq(long qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        String createTimeStr = "";
        String updateTimeStr = "";
        if (createtime != null) {
            createTimeStr = StringUtil.formatY_M_D_HMS.format(createtime);
        }
        if (updatetime != null) {
            updateTimeStr = StringUtil.formatY_M_D_HMS.format(updatetime);
        }
        return "{" +
                "\"id\":" + id +
                ", \"uid\":\"" + uid + "\"" +
                ", \"uname\":\"" + uname + "\"" +
                ", \"mobile\":\"" + mobile + "\"" +
                ", \"email\":\"" + email + "\"" +
                ", \"qq\":" + qq +
                ", \"wechat\":\"" + wechat + "\"" +
                ", \"password\":\"" + password + "\"" +
                ", \"createtime\":\"" + createTimeStr + "\"" +
                ", \"updatetime\":\"" + updateTimeStr + "\"" +
                "}";
    }
}
