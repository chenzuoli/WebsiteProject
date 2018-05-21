package top.wetech.czl.util;

import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import top.wetech.czl.controller.IndexController;
import top.wetech.czl.controller.LoginController;
import top.wetech.czl.controller.RegisterController;
import top.wetech.czl.controller.SystemController;
import top.wetech.czl.model.*;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: Chen
 * Date: 2018/2/3
 * Time: 13:27
 * Description: 配置器
 */

public class JFinalCfg extends JFinalConfig {
    public static C3p0Plugin createC3P0Plugin() {
        return new C3p0Plugin(PropKit.get("jdbcUrl").trim(), PropKit.get("user").trim(), PropKit.get("password").trim());
    }

    public void configConstant(Constants constants) {
        PropKit.use("config.properties");
        constants.setDevMode(PropKit.getBoolean("devMode", false));
        constants.setViewType(ViewType.JSP);
    }

    public void configRoute(Routes routes) {
        routes.add("/", IndexController.class);
        routes.add("/login", LoginController.class);
        routes.add("/system", SystemController.class);
        routes.add("/register", RegisterController.class);
    }

    public void configPlugin(Plugins plugins) {
        C3p0Plugin C3p0Plugin = createC3P0Plugin(); //c3p0连接池插件
        plugins.add(C3p0Plugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin); //数据库操作插件
        plugins.add(arp);
        arp.addMapping("users", "id", User.class); //该方法建立了数据库表名到 Model 的映射关系,下面的示例为默认主键是id
        arp.addMapping("articles", "id", Article.class);
        arp.addMapping("records", "id", Records.class);
        arp.addMapping("comments", "id", Comments.class);
        arp.addMapping("replies", "id", Replies.class);
        arp.addMapping("fans", "id", Fans.class);
        arp.addMapping("menu", "mid", Menu.class);
    }

    public void configInterceptor(Interceptors interceptors) {

    }

    public void configHandler(Handlers handlers) {

    }
}
