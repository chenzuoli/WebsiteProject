package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Model;
import top.wetech.czl.util.StringUtil;

import java.util.Date;

/**
 * user: chenzuoli
 * company: 北京通付盾数据科技有限公司
 * date: 2018/4/29
 * time: 17:15
 * description: 回复model类
 */
public class Replies extends Model<Replies> {
    private int id;
    private String cid;
    private String uid;
    private String comment;
    private int like;
    private int trample;
    private Date createtime;

    public static final Replies dao = new Replies();

    private Replies() {
    }

    public Replies(int id, String cid, String uid, String comment, int like, int trample, Date createtime) {
        this.id = id;
        this.cid = cid;
        this.uid = uid;
        this.comment = comment;
        this.like = like;
        this.trample = trample;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getTrample() {
        return trample;
    }

    public void setTrample(int trample) {
        this.trample = trample;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\"=" + id +
                ", \"cid\"=\"" + cid + "\"" +
                ", \"uid\"=\"" + uid + "\"" +
                ", \"comment\"=\"" + comment + "\"" +
                ", \"like\"=" + like +
                ", \"trample\"=" + trample +
                ", \"createtime\"=\"" + StringUtil.formatY_M_D_HMS.format(createtime) + "\"" +
                "}";
    }
}
