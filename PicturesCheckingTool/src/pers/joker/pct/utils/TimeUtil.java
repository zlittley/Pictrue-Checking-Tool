package pers.joker.pct.utils;

/**
 * 时间工具类
 */
public class TimeUtil {
    private TimeUtil(){

    }

    public static String timeFormat(long sec){
        if(sec < 0){
            return "Error";
        }
        sec = sec / 1000;    //转化成秒
        long hours = sec / 3600;
        long minutes = sec % 3600 / 60;
        long seconds = sec % 60;
        StringBuffer buffer = new StringBuffer();
        if(hours > 0){
            buffer.append(hours + "h ");
        }
        if(minutes > 0){
            buffer.append(minutes + "min ");
        }
        buffer.append(seconds + "s");
        return buffer.toString();
    }
}
