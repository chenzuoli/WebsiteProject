package top.wetech.czl.controller;

import com.jfinal.core.Controller;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import top.wetech.czl.model.Article;
import top.wetech.czl.util.StringUtil;

import java.util.List;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: Chen
 * Date: 2018/2/3
 * Time: 13:26
 * Description: 控制器
 */

public class SystemController extends Controller {
    private Logger logger = Logger.getLogger(SystemController.class);

    /**
     * 查询文章详情
     */
    public void article() {
        String articleId = getPara("articleid");
        Article article = Article.dao.queryArticle(articleId);
        renderJson(article);
    }

    /**
     * 查询热点文章，前10篇
     */
    public void hotArticle() {
        JSONObject returnJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            String sql = "select mid, uid, title, articleid, content, tags, clicks, fans, createtime, updatetime from articles order by clicks desc limit 10";
            List<Article> articles = Article.dao.find(sql);
            addArticle(jsonArray, articles);
            returnJson.put("statCode", "709");
            returnJson.put("articles", jsonArray);
        } catch (Exception e){
            logger.error("query hotest articles exception: " + e);
            returnJson.put("statCode", "750");
        }
        renderJson(returnJson);
    }

    /**
     * 根据传递的参数pageNum来获取该页的文章信息
     */
    public void hotArticleNext() {
        String pageNumStr = getPara("pageNum");
        String sql = "select mid, uid, title, articleid, content, tags, clicks, fans, createtime, updatetime from articles order by clicks desc limit ?, ?";
        JSONObject returnJson = hotArticle(sql, null, pageNumStr);
        renderJson(returnJson);
    }

    /**
     * 根据文章分类来获取热门文章，前10篇
     */
    public void hotArticleByCategory(){
        String category = getPara("category");
        String sql = "select mid, uid, title, articleid, content, tags, clicks, fans, createtime, updatetime from articles where mid = ? order by clicks desc limit 10";
        JSONObject returnJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            List<Article> articles = Article.dao.find(sql, category);
            addArticle(jsonArray, articles);
            returnJson.put("statCode", "709");
            returnJson.put("articles", jsonArray);
        } catch (Exception e){
            logger.error("query hotest articles by category exception: " + e);
            returnJson.put("statCode", "750");
        }
        renderJson(returnJson);
    }

    /**
     * 根据传递的参数pageNum来获取该页的文章信息
     */
    public void hotArticleByCategoryNext() {
        String category = getPara("category");
        String pageNumStr = getPara("pageNum");
        String sql = "select mid, uid, title, articleid, content, tags, clicks, fans, createtime, updatetime from articles where mid = ? order by clicks desc limit ?, ?";
        JSONObject returnJson = hotArticle(sql, category, pageNumStr);
        renderJson(returnJson);
    }

    private JSONObject hotArticle(String sql, String category, String pageNumStr) {
        JSONObject returnJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        int startNum = 0;
        int endNum = 0;
        try {
            if (pageNumStr == null || pageNumStr.trim().length() == 0 || !pageNumStr.matches("\\d+")) {
                startNum = 0;
                endNum = 10;
                returnJson.put("statCode", "750");
            } else {
                int pageNum = Integer.parseInt(pageNumStr);
                startNum = (pageNum-1) * 10;
                endNum = (pageNum) * 10;
                returnJson.put("statCode", "709");
            }
            List<Article> articles;
            if (category == null){
                articles = Article.dao.find(sql, startNum, endNum);
            } else {
                articles = Article.dao.find(sql, category, startNum, endNum);
            }
            addArticle(jsonArray, articles);
            returnJson.put("articles", jsonArray);
        } catch (Exception e) {
            logger.error("query articles at page " + pageNumStr + " exception: " + e);
        }
        return returnJson;
    }

    private void addArticle(JSONArray jsonArray, List<Article> articles) {
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            String[] attrNames = article.getAttrNames();
            JSONObject jsonObject = new JSONObject();
            for (int j = 0; j < attrNames.length; j++) {
                jsonObject.put(attrNames[j], article.get(attrNames[j]));
                Object createtime = article.get("createtime");
                Object updatetime = article.get("updatetime");
                jsonObject.put("createtime", createtime == null ? null : StringUtil.formatY_M_D_HMS.format(createtime));
                jsonObject.put("updatetime", updatetime == null ? null : StringUtil.formatY_M_D_HMS.format(updatetime));
            }
            jsonArray.add(jsonObject);
        }
    }

}
