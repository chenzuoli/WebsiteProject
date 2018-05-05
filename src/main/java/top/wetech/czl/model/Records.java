package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Model;
import top.wetech.czl.util.StringUtil;

import java.util.Date;

/**
 * user: chenzuoli
 * company: 北京通付盾数据科技有限公司
 * date: 2018/4/29
 * time: 17:09
 * description: 访问记录model类
 */
public class Records extends Model<Records>{
    private int id;
    private String uid;
    private String uip;
    private Date observetime;

    public static final Records dao = new Records();

    public Records(int id, String uid, String uip, Date observetime) {
        this.id = id;
        this.uid = uid;
        this.uip = uip;
        this.observetime = observetime;
    }

    public Records() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUip() {
        return uip;
    }

    public void setUip(String uip) {
        this.uip = uip;
    }

    public Date getObservetime() {
        return observetime;
    }

    public void setObservetime(Date observetime) {
        this.observetime = observetime;
    }

    @Override
    public String toString() {
        String observeTimeStr = "";
        if (observeTimeStr != null){
            observeTimeStr = StringUtil.formatY_M_D_HMS.format(observetime);
        }
        return "{" +
                "\"id\":" + id +
                ", \"uid\":\"" + uid + "\"" +
                ", \"uip\":\"" + uip + "\"" +
                ", \"observetime\":\"" + observeTimeStr + "\"" +
                "}";
    }
}
