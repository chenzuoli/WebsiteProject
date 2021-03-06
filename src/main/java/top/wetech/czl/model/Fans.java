package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Model;
import top.wetech.czl.util.StringUtil;

import java.util.Date;

/**
 * user: chenzuoli
 * company: wetech.top
 * date: 2018/4/29
 * time: 16:58
 * description: 粉丝Model类
 */
public class Fans extends Model<Fans> {
    public static final Fans dao = new Fans();
    private int id;
    private String fid;
    private String aid;
    private Date lovetime;
    private Date leavetime;

    public Fans() {
    }

    public Fans(int id, String fid, String aid, Date lovetime, Date leavetime) {
        this.id = id;
        this.fid = fid;
        this.aid = aid;
        this.lovetime = lovetime;
        this.leavetime = leavetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public Date getLovetime() {
        return lovetime;
    }

    public void setLovetime(Date lovetime) {
        this.lovetime = lovetime;
    }

    public Date getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Date leavetime) {
        this.leavetime = leavetime;
    }

    @Override
    public String toString() {
        String loveTimeStr = "";
        String leaveTimeStr = "";
        if (loveTimeStr != null) {
            loveTimeStr = StringUtil.formatY_M_D_HMS.format(lovetime);
        }
        if (leaveTimeStr != null) {
            leaveTimeStr = StringUtil.formatY_M_D_HMS.format(leavetime);
        }
        return "{" +
                "\"id\":" + id +
                ", \"fid\":\"" + fid + "\"" +
                ", \"aid\":\"" + aid + "\"" +
                ", \"lovetime\":\"" + loveTimeStr + "\"" +
                ", \"leavetime\":\"" + leaveTimeStr + "\"" +
                "}";
    }
}
