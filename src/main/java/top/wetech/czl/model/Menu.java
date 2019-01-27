package top.wetech.czl.model;

import com.jfinal.plugin.activerecord.Model;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * company: wetech.top
 * user: chenzuoli
 * date: 2018/5/20
 * time: 15:19
 * description: 菜单栏Model类
 */

public class Menu extends Model<Menu>{
    public static Menu dao = new Menu();
    private static Logger logger = Logger.getLogger(Menu.class);
    private String mid;
    private String name;

    public Menu() {
    }

    public Menu(String mid, String name) {
        this.mid = mid;
        this.name = name;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "\"mid\"=\"" + mid + '\"' +
                ", \"name\"=\"" + name + '\"' +
                '}';
    }
}
