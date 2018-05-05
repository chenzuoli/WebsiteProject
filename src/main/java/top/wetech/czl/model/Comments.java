package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Model;
import top.wetech.czl.util.StringUtil;

import java.util.Date;

/**
 * user: chenzuoli
 * company: 北京通付盾数据科技有限公司
 * date: 2018/4/29
 * time: 16:44
 * description: 评论model类
 */
public class Comments extends Model<Comments> {
    private int id;
    private String aid;
    private String uid;
    private String comment;
    private int like;
    private int trample;
    private Date createtime;

    public static final Comments dao = new Comments();

    public Comments() {
    }

    public Comments(int id, String aid, String uid, String comment, int like, int trample, Date createtime) {
        this.id = id;
        this.aid = aid;
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

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
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
        String createTimeStr = "";
        if (createtime != null){
            createTimeStr = StringUtil.formatY_M_D_HMS.format(createtime);
        }
        return "{" +
                "\"id\":" + id +
                ", \"aid\":\"" + aid + "\"" +
                ", \"uid\":\"" + uid + "\"" +
                ", \"comment\":\"" + comment + "\"" +
                ", \"like\":" + like +
                ", \"trample\":" + trample +
                ", \"createtime\":\"" + createTimeStr + "\"" +
                "}";
    }
}
