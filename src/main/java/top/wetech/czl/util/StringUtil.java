package top.wetech.czl.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Company: wetech.top
 * User: 陈作立
 * Date: 2018/4/10
 * Time: 10:24
 * Description: 检验合法日期
 */

public class StringUtil {
    public static SimpleDateFormat formatYMDSlash = new SimpleDateFormat("yyyy/MM/dd");
    public static SimpleDateFormat formatY_M_D = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat formatY_M = new SimpleDateFormat("yyyy-MM");
    public static SimpleDateFormat formatY = new SimpleDateFormat("yyyy");
    public static SimpleDateFormat formatYMD = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat formatYM = new SimpleDateFormat("yyyyMM");
    public static SimpleDateFormat formatY_M_D_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat formatYMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat formatY_M_D_HMSMILLS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static String ymdSlashRegex = "\\d{4}/\\d{1,2}/\\d{1,2}";
    public static String y_m_d = "\\d{4}-\\d{1,2}-\\d{1,2}";
    public static String y_m = "\\d{4}-\\d{1,2}";
    public static String y = "\\d{4}";
    public static String ymd = "\\d{6,8}";
    public static String ym = "\\d{5,6}";
    public static String y_m_d_h_m_s = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";
    public static String ymdhms = "\\d{9,14}";
    public static String y_m_d_h_m_s_S = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}";
    public static String dateTimeMillsRegex = "\\d+-[01]?\\d-[0123]\\d\\s\\d?\\d:\\d?\\d:\\d?\\d\\.\\d?\\d?\\d?";
    public static String dateTimeRegex = "^\\d{4}[-]([0]?[1-9]|(1[0-2]))[-]([1-9]|([012]?\\d)|(3[01]))([ \\t\\n\\x0B\\f\\r])(([0-1]?[0-9]?)|([2]?[0-4]?))([:])(([0-5]?[0-9]?|[6]{1}[0]{1}))([:])((([0-5]?[0-9]?|[6]{1}[0]{1})))$";
    public static String dateRegex = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))$";
    private static Logger logger = Logger.getLogger(StringUtil.class);

    static {
        formatYMDSlash.setLenient(false);//这个的功能是不把1996/13/3 转换为1997/1/3
        formatY_M_D.setLenient(false);
        formatY_M.setLenient(false);
        formatY.setLenient(false);
        formatYMD.setLenient(false);
        formatYM.setLenient(false);
        formatY_M_D_HMS.setLenient(false);
        formatYMDHMS.setLenient(false);
        formatY_M_D_HMSMILLS.setLenient(false);
    }

    /**
     * @Description: 检验日期字符串合法性，日期格式正则表达式不全，可补充
     * @Param: [dateStr]
     * @return: java.util.Date
     * @Author: CHEN ZUOLI
     * @Date: 2018/4/11
     * @Time: 11:42
     * @Ps: 当日期类型字符串格式正确，但是数值不合法（秒的值达到66）时，会抛异常
     */
    public static Date parseStringToDate(String dateStr) {
        if (dateStr == null || dateStr.trim().equals(""))
            return null;
        dateStr = dateStr.trim();
        Date date = null;
        try {
            if (dateStr.matches(ymdSlashRegex)) {
                date = formatYMDSlash.parse(dateStr);
            } else if (dateStr.matches(y_m_d)) {
                date = formatY_M_D.parse(dateStr);
            } else if (dateStr.matches(y_m)) {
                date = formatY_M.parse(dateStr);
            } else if (dateStr.matches(y)) {
                date = formatY.parse(dateStr);
            } else if (dateStr.matches(ymd)) {
                date = formatYMD.parse(dateStr);
            } else if (dateStr.matches(ym)) {
                date = formatYM.parse(dateStr);
            } else if (dateStr.matches(ymdhms)) {
                date = formatYMDHMS.parse(dateStr);
            } else if (dateStr.matches(y_m_d_h_m_s)) {
                date = formatY_M_D_HMS.parse(dateStr);
            } else if (dateStr.matches(y_m_d_h_m_s_S)) {
                date = formatY_M_D_HMSMILLS.parse(dateStr);
            } else {
                logger.info("please enter the normal date string!");
            }
        } catch (Exception e) {
            logger.error("date string " + dateStr + " is not valid!");
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @Description: 检验字符串是否为jsonObject结构
     * @Param: [jsonStr]
     * @return: boolean
     * @Author: CHEN ZUOLI
     * @Date: 2018/4/11
     * @Time: 11:53
     */
    public static boolean checkValidJsonObjectStr(String jsonStr) {
        boolean flag = false;
        try {
            JSONObject.fromObject(jsonStr);
            flag = true;
        } catch (Exception e) {
            logger.error("illegal json string!");
        }
        return flag;
    }

    /**
     * @Description: 检验字符串是否为jsonArray结构
     * @Param: [jsonStr]
     * @return: boolean
     * @Author: CHEN ZUOLI
     * @Date: 2018/4/17
     * @Time: 10:27
     */
    public static boolean checkValidJsonArrayStr(String jsonStr) {
        boolean flag = false;
        try {
            JSONArray.fromObject(jsonStr);
            flag = true;
        } catch (Exception e) {
            logger.error("illegal json string!");
        }
        return flag;
    }

    /**
     * @Description: 如果字符串中包含有反斜杠、单引号、双引号字符时，为他们添加转义字符\
     * @Param: [str]
     * @return: java.lang.String
     * @Author: CHEN ZUOLI
     * @Date: 2018/4/11
     * @Time: 11:58
     */
    public static String addEscape(String str) {
        str = str.replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("\"", "\\\\\"")
                .replaceAll("'", "\\\\'");
        return str;
    }

    public static String getCurTime(){
        return  formatY_M_D_HMS.format(new Date());
    }

    public static void main(String[] args) {
//        Date date = parseStringToDate("2018-1-12 01:1:66");
//        logger.info(date);
//        logger.info("--------------");
//        logger.info(addEscape("''\\''\"'''"));
        boolean aNull = checkValidJsonObjectStr("null");
        System.out.println(aNull);
        JSONObject jsonObject = JSONObject.fromObject("null");
        System.out.println(jsonObject);
    }

}
