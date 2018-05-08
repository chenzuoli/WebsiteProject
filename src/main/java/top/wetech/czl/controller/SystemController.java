package top.wetech.czl.controller;

import com.jfinal.core.Controller;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import top.wetech.czl.model.Article;
import top.wetech.czl.model.Comments;
import top.wetech.czl.model.Replies;
import top.wetech.czl.util.StringUtil;

import java.util.ArrayList;
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
     * 查询文章详情：文章详情
     */
    public void article() {
        String articleId = getPara("articleid");
        Article article = Article.dao.queryArticle(articleId);
        JSONObject jsonObject = new JSONObject();
        if (article != null) {
            jsonObject.put("statCode", "709");
            articleToJsonObject(article, jsonObject);
        } else {
            jsonObject.put("statCode", "750");
        }
        renderJson(jsonObject);
    }

    /**
     * 查询热点文章，前10篇
     */
    public void hotArticle() {
        JSONObject returnJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            String sql = "select mid, uid, title, articleid, content, tags, clicks, fans, thumbsup, thumbsdown, createtime, updatetime from articles order by clicks desc limit 10";
            List<Article> articles = Article.dao.find(sql);
            addArticle(jsonArray, articles);
            returnJson.put("statCode", "709");
            returnJson.put("articles", jsonArray);
        } catch (Exception e) {
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
        String sql = "select mid, uid, title, articleid, content, tags, clicks, fans, thumbsup, thumbsdown, createtime, updatetime from articles order by clicks desc limit ?, ?";
        JSONObject returnJson = hotArticle(sql, null, pageNumStr);
        renderJson(returnJson);
    }

    /**
     * 根据文章分类来获取热门文章，前10篇
     */
    public void hotArticleByCategory() {
        String category = getPara("category");
        String sql = "select mid, uid, title, articleid, content, tags, clicks, fans, thumbsup, thumbsdown, createtime, updatetime from articles where mid = ? order by clicks desc limit 10";
        JSONObject returnJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            List<Article> articles = Article.dao.find(sql, category);
            addArticle(jsonArray, articles);
            returnJson.put("statCode", "709");
            returnJson.put("articles", jsonArray);
        } catch (Exception e) {
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
        String sql = "select mid, uid, title, articleid, content, tags, clicks, fans, thumbsup, thumbsdown, createtime, updatetime from articles where mid = ? order by clicks desc limit ?, ?";
        JSONObject returnJson = hotArticle(sql, category, pageNumStr);
        renderJson(returnJson);
    }

    /**
     * 为文章点赞、踩，需传递参数文章id及点赞还是踩
     * 1.articleid
     * 2.thumb：+代表点赞，-代表踩
     */
    public void articleThrumbUpDown() {
        String articleid = getPara("articleid");
        String thumb = getPara("thumbs");
        boolean isChanged = Article.dao.changeThumbs(articleid, thumb);
        JSONObject returnJson = new JSONObject();
        if (isChanged) {
            returnJson.put("statCode", "709");
        } else {
            returnJson.put("statCode", "760");
        }
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
                startNum = (pageNum - 1) * 10;
                endNum = (pageNum) * 10;
                returnJson.put("statCode", "709");
            }
            List<Article> articles;
            if (category == null) {
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
            JSONObject jsonObject = new JSONObject();
            articleToJsonObject(article, jsonObject);
            jsonArray.add(jsonObject);
        }
    }

    private void articleToJsonObject(Article article, JSONObject jsonObject) {
        String[] attrNames = article.getAttrNames();
        for (int j = 0; j < attrNames.length; j++) {
            jsonObject.put(attrNames[j], article.get(attrNames[j]));
            Object createtime = article.get("createtime");
            Object updatetime = article.get("updatetime");
            jsonObject.put("createtime", createtime == null ? null : StringUtil.formatY_M_D_HMS.format(createtime));
            jsonObject.put("updatetime", updatetime == null ? null : StringUtil.formatY_M_D_HMS.format(updatetime));
        }
    }

    private void commentToJsonObject(Comments comment, JSONObject jsonObject) {
        String[] attrNames = comment.getAttrNames();
        for (int j = 0; j < attrNames.length; j++) {
            jsonObject.put(attrNames[j], comment.get(attrNames[j]));
            Object createtime = comment.get("createtime");
            Object updatetime = comment.get("updatetime");
            jsonObject.put("createtime", createtime == null ? null : StringUtil.formatY_M_D_HMS.format(createtime));
            jsonObject.put("updatetime", updatetime == null ? null : StringUtil.formatY_M_D_HMS.format(updatetime));
        }
    }

    private void replyToJsonObject(Replies reply, JSONObject jsonObject){
        String[] attrNames = reply.getAttrNames();
        for (int j = 0; j < attrNames.length; j++) {
            jsonObject.put(attrNames[j], reply.get(attrNames[j]));
            Object createtime = reply.get("createtime");
            Object updatetime = reply.get("updatetime");
            jsonObject.put("createtime", createtime == null ? null : StringUtil.formatY_M_D_HMS.format(createtime));
            jsonObject.put("updatetime", updatetime == null ? null : StringUtil.formatY_M_D_HMS.format(updatetime));
        }
    }

    /**
     * description: 查询文章详情、评论点赞踩详情
     * datetime: 2018/5/6 19:10
     * params: []
     * returns: void
     */
    public void articleDetails() {
        String articleId = getPara("articleid");
        JSONObject returnJson = new JSONObject();
        getArticle(articleId, returnJson);
        getComments(articleId, returnJson);
        renderJson(returnJson);
    }

    private void getArticle(String articleId, JSONObject returnJson) {
        try {
            JSONObject articleJson = new JSONObject();
            String queryArticleSql = "select mid, uid, title, articleid, content, tags, clicks, fans, thumbsup, thumbsdown, createtime, updatetime from articles where articleid = ?";
            List<Article> articles = Article.dao.find(queryArticleSql, articleId);
            if (articles.size() > 0) {
                returnJson.put("statCode", "709");
                Article article = articles.get(0);
                articleToJsonObject(article, articleJson);
                returnJson.put("article", articleJson);
            } else {
                returnJson.put("statCode", "750");
                returnJson.put("article", articleJson);
            }
        } catch (Exception e){
            logger.error("get article info exception: " + e);
        }
    }

    private void getComments(String articleId, JSONObject returnJson) {
        try {
            JSONArray commentsJson = new JSONArray();
            if ("709".equals(returnJson.optString("statCode"))){
                String queryCommentSql = "select id, aid, uid, comment, 'like', trample, createtime from comments where aid = ? limit 10";
                List<Comments> comments = Comments.dao.find(queryCommentSql, articleId);
                if (comments.size() > 0) {
                    returnJson.put("statCode", "709");
                    comments.forEach(comment -> {
                        JSONObject jsonObject = new JSONObject();
                        commentToJsonObject(comment, jsonObject);
                        JSONArray replies = getReplies(comment);
                        jsonObject.put("replies", replies);
                        commentsJson.add(jsonObject);
                    });
                } else {
                    returnJson.put("statCode", "750");
                }
            }
            returnJson.put("comments", commentsJson);
        } catch (Exception e){
            logger.error("get comments exception: " + e + " at article id is " + articleId);
        }
    }

    private JSONArray getReplies(Comments comment){
        JSONArray jsonArray = new JSONArray();
        Object id = comment.get("id");
        try {
            if (id != null){
                String sql = "select id, cid, uid, comment, 'like', trample, createtime from replies where cid = ?";
                List<Replies> replies = Replies.dao.find(sql, id.toString());
                replies.forEach(reply -> {
                    JSONObject jsonObject = new JSONObject();
                    replyToJsonObject(reply, jsonObject);
                    jsonArray.add(jsonObject);
                });
            }
        } catch (Exception e){
            logger.error("get replies exception: " + e + " at comment id is " + id);
        }
        return jsonArray;
    }

}
