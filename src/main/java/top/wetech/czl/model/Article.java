package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import top.wetech.czl.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @User: 陈作立
 * @Date: 2018/3/8 0008
 * @Time: 0:10
 * @Discription: 文章model，提供查询、存储文章功能
 */
public class Article extends Model<Article> {
    public static final Article dao = new Article();
    private int id;
    private String mid;
    private String uid;
    private String title;
    private String articleid;
    private String content;
    private String tags;
    private int clicks;
    private int fans;
    private int thumbsup;
    private int thumbsdown;
    private Date createtime;
    private Date updatetime;
    private Logger logger = Logger.getLogger(Article.class);

    public Article(int id, String mid, String uid, String title, String articleid, String content, String tags, int clicks, int fans, int thumbsup, int thumbsdown, Date createtime, Date updatetime) {
        this.id = id;
        this.mid = mid;
        this.uid = uid;
        this.title = title;
        this.articleid = articleid;
        this.content = content;
        this.tags = tags;
        this.clicks = clicks;
        this.fans = fans;
        this.thumbsup = thumbsup;
        this.thumbsdown = thumbsdown;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Article() {
    }

    /**
     * 保存文章到数据库
     *
     * @param title   文章标题
     * @param tag     文章标签
     * @param uid     文章作者id
     * @param content 文章内容
     */
    public boolean saveArticle(String title, String tag, String uid, String content) {
        boolean isSave = set("content", content).set("title", title).set("uid", uid).set("tags", tag).set("articleid", UUID.randomUUID().toString()).save();
        return isSave;
    }

    /**
    * description: 查询文章内容
    * datetime: 2018/5/6 18:41
    * params: [articleId]
    * returns: top.wetech.czl.model.Article
    */
    public Article queryArticle(String articleId) {
        Article article = null;
        String queryArticleSql = "select mid, uid, title, articleid, content, tags, clicks, fans, thumbsup, thumbsdown, createtime, updatetime from articles where articleid = ?";
        List<Article> articles = find(queryArticleSql, articleId);
        if (articles.size() > 0) {
            article = articles.get(0);
        }
        return article;
    }

    /**
     * description: 更新文章的点赞与踩的次数
     * datetime: 2018/5/6 18:12
     * params: [articleId, thumbs]
     * returns: boolean
     */
    public boolean changeThumbs(String articleId, String thumbs) {
        boolean flag = false;
        try {
            String querySql = "select thumbsup, thumbsdown from articles where articleid = ?";
            List<Article> articles = find(querySql, articleId);
            if (articles.size() > 0) {
                Object thumbsupObj = articles.get(0).get("thumbsup");
                Object thumbsdownObj = articles.get(0).get("thumbsdown");
                if ("+".equals(thumbs) && thumbsupObj != null) {
                    int thumbsup;
                    if (thumbsupObj.toString().matches("\\d")) {
                        thumbsup = Integer.parseInt(thumbsupObj.toString());
                    } else {
                        thumbsup = 1;
                    }
                    String sql = "update articles set thumbsup = ? where articleid = ?";
                    Db.update(sql, (thumbsup + 1), articleId);
                    flag = true;
                } else if ("-".equals(thumbs) && thumbsdownObj != null) {
                    int thumbsdown;
                    if (thumbsdownObj.toString().matches("\\d")) {
                        thumbsdown = Integer.parseInt(thumbsdownObj.toString());
                    } else {
                        thumbsdown = 1;
                    }
                    String sql = "update articles set thumbsdown = ? where articleid = ?";
                    Db.update(sql, (thumbsdown + 1), articleId);
                    flag = true;
                }
            }
        } catch (Exception e) {
            logger.error("change thumbs exception: " + e + " at articleid = " + articleId + ", thumbs is " + thumbs);
        }
        return flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getThumbsup() {
        return thumbsup;
    }

    public void setThumbsup(int thumbsup) {
        this.thumbsup = thumbsup;
    }

    public int getThumbsdown() {
        return thumbsdown;
    }

    public void setThumbsdown(int thumbsdown) {
        this.thumbsdown = thumbsdown;
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
                ", \"mid\":\"" + mid + "\"" +
                ", \"uid\":\"" + uid + "\"" +
                ", \"title\":\"" + title + "\"" +
                ", \"articleid\":\"" + articleid + "\"" +
                ", \"content\":\"" + content + "\"" +
                ", \"tags\":\"" + tags + "\"" +
                ", \"clicks\":" + clicks +
                ", \"fans\":" + fans +
                ", \"thumbsup\":" + thumbsup +
                ", \"thumbsdown\":" + thumbsdown +
                ", \"createtime\":\"" + createTimeStr + "\"" +
                ", \"updatetime\":\"" + updateTimeStr + "\"" +
                "}";
    }
}
