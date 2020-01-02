package pers.joker.pct.models;

import java.util.Date;

/**
 * 文件检查历史记录
 */
public class CheckRecord {
    private String checkId;
    private String filePath;
    private boolean type;   //0 - 文件，1 - 目录
    private Date date;  //检索日期

    public CheckRecord(){

    }

    public CheckRecord(String checkId, String filePath, boolean type, Date date){
        setCheckId(checkId);
        setFilePath(filePath);
        setType(type);
        setDate(date);
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
