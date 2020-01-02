package pers.joker.pct.models;

/**
 * 检查记录
 */
public class Record {
    //private String checkId;
    private String filePath;
    private String fileName;
    //"涉密", "秘密", "机密", "绝密", "保密", "泄密"
    private int[] count = {0, 0, 0, 0, 0, 0};
    //是否涉密
    private boolean isSecret = false;

    public Record(){

    }

    public Record(String filePath, String fileName, int[] count){
        setFilePath(filePath);
        setFileName(fileName);
        setCount(count);
        if(count[0] != 0 || count[1] != 0 || count[2] != 0 || count[3] != 0 || count[4] != 0 || count[5] != 0){
            isSecret = true;
        }
    }

//    public String getCheckId() {
//        return checkId;
//    }
//
//    public void setCheckId(String checkId) {
//        this.checkId = checkId;
//    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int[] getCount() {
        return count;
    }

    public void setCount(int[] count) {
        this.count = count;
    }

    public boolean isSecret() {
        return isSecret;
    }

    @Override
    public String toString() {
        return ", FileName: " + fileName + " - FilePath: " + filePath +
                ", \"涉密\": " + count[0] + ", \"秘密\": " + count[1] + ", \"机密\": " + count[2] +
                ", \"绝密\": " + count[3] + ", \"保密\": " + count[4] + ", \"泄密\" : " + count[5];
    }
}
