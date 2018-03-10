package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * @Discription:
 * @User: 陈作立
 * @Date: 2018/3/8 0008
 * @Time: 0:10
 * @Ps:
 */
public class Article extends Model<Article>{

    public static final Article dao = new Article();

    public void saveArticle(String content, String mid, String uid, String tag){
        set("content", content).set("mid", mid).set("uid", uid).set("tags", tag).save();
    }

}
