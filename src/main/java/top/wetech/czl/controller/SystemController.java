package top.wetech.czl.controller;

import com.jfinal.core.Controller;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: Chen
 * Date: 2018/2/3
 * Time: 13:26
 * Description: 控制器
 */

public class SystemController extends Controller{

    public void index(){
        render("/login.html");
    }

}
