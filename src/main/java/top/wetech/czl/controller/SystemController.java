package top.wetech.czl.controller;

import com.jfinal.core.Controller;
import top.wetech.czl.model.Article;

import java.util.List;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: Chen
 * Date: 2018/2/3
 * Time: 13:26
 * Description: 控制器
 */

public class SystemController extends Controller{
    private Article article = new Article();

    public void index(){
        render("/login.html");
    }

    /**
     * 查询文章详情
     */
    public void article(){
        String articleId = getPara("articleId");
        Article article = this.article.queryArticle(articleId);
        renderJson(article);
    }

    /**
     * 查询热点文章
     */
    public void hotArticle(){
        String sql = "select mid, uid, title, articleid, content, tags, clicks, fans, createtime, updatetime from articles order by clicks desc limit 1000";
        List<Article> articles = article.find(sql);
        renderJson(articles);
    }

}
