package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.Date;
import java.util.UUID;

/**
 * @Discription: 文章model，提供查询、存储文章功能
 * @User: 陈作立
 * @Date: 2018/3/8 0008
 * @Time: 0:10
 * @Ps: jfinal
 */
public class Article extends Model<Article>{

    public static final Article dao = new Article();

    public void saveArticle(String title, String tag, String uid, String content){
        set("content", content).set("title", title).set("uid", uid).set("tags", tag).set("articleid", UUID.randomUUID().toString()).save();
    }

}
