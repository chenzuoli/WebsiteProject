package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Model;
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
    private int id;
    private String mid;
    private String uid;
    private String title;
    private String articleid;
    private String content;
    private String tags;
    private int clicks;
    private int fans;
    private Date createtime;
    private Date updatetime;

    public static final Article dao = new Article();

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
     * 查询文章内容
     *
     * @param articleId 文章id
     * @return 文章类
     */
    public Article queryArticle(String articleId) {
        Article article = null;
        String queryArticleSql = "select mid, uid, title, articleid, content, tags, clicks, fans, createtime, updatetime from articles where articleid = ?";
        List<Article> articles = find(queryArticleSql, articleId);
        if (articles.size() > 0){
            article = articles.get(0);
        }
        return article;
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

    public Article(int id, String mid, String uid, String title, String articleid, String content, String tags, int clicks, int fans, Date createtime, Date updatetime) {
        this.id = id;
        this.mid = mid;
        this.uid = uid;
        this.title = title;
        this.articleid = articleid;
        this.content = content;
        this.tags = tags;
        this.clicks = clicks;
        this.fans = fans;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    private Article() {
    }

    @Override
    public String toString() {
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
                ", \"createtime\":\"" + StringUtil.formatY_M_D_HMS.format(createtime) + "\"" +
                ", \"updatetime\":\"" + StringUtil.formatY_M_D_HMS.format(updatetime) + "\"" +
                "}";
    }
}
