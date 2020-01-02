package pers.joker.pct.checker;

import pers.joker.pct.models.Record;
import pers.joker.pct.utils.FileUtil;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * 图片内容检查类
 */
public class ImageChecker {
    //涉密字段
    private static final String[] keyWords = {"涉密", "秘密", "机密", "绝密", "保密", "泄密"};

    //检查记录
    private ArrayList<Record> records = new ArrayList<>();
    //开始时间
    private long startTime;
    //结束时间
    private long endTime;
    //检查出含有涉密字段的文件个数
    private int secretCount;
    //各种图片数量
    private int jpgCount = 0;
    private int jpegCount = 0;
    private int pngCount = 0;
    private int bmpCount = 0;

    public ImageChecker(){

    }

    public ArrayList<Record> check(String filePath){
        //清空记录
        init();

        startTime = System.currentTimeMillis();

        File file = new File(filePath);
        if(file.exists()){
            if(file.isFile() && FileUtil.isImage(file.getAbsolutePath())){
                fileCheck(file);
            }else if (file.isDirectory()){
                folderCheck(file);
            }else {
                System.out.println("未找到可以识别的图片文件！");
            }
        }else{
            System.out.println("该文件或目录不存在！");
        }

        endTime = System.currentTimeMillis();

        return records;
    }

    /**
     * 单个文件检索
     */
    private void fileCheck(File file){
        System.out.println("正在检查" + file.getAbsolutePath());
        //提取出文字
        String result = ImageTextRecognizer.extract(file.getAbsolutePath());

        //检察提取出的文字中有无涉密信息
        int[] count = new int[6];   //各涉密字段统计
        for (int i = 0; i < 6; i++){
            count[i] = countString(result, keyWords[i]);
        }

        //把结果写成一条记录
        Record record = new Record(file.getAbsolutePath(), file.getName(), count);
        System.out.println(record);
        records.add(record);
        countType(file.getName());

        if(record.isSecret()){
            secretCount++;
        }
    }

    /**
     * 文件夹检索
     */
    private void folderCheck(File file){
        //递归遍历文件夹并调用fileCheck检索是否含有保密字段
        File[] files = file.listFiles();
        if(null == files || files.length == 0) {
            System.out.println("文件夹" + file.getAbsolutePath() + "是空的！");
            return;
        }else {
            for (File f: files) {
                if(f.isDirectory()){    //如果是目录，则递归检索
                    folderCheck(f);
                }else if(FileUtil.isImage(f.getAbsolutePath())){    //如果是可识别的图片文件，则调用fileCheck
                    fileCheck(f);
                }else { //如果是其他类型文件，则跳过
                    continue;
                }
            }
        }
    }

    /**
     * 初始化检查数据
     */
    private void init(){
        records.clear();
        startTime = 0;
        endTime = 0;
        secretCount = 0;
        setJpgCount(0);
        setJpegCount(0);
        setPngCount(0);
        setBmpCount(0);
    }

    /**
     * 获取字符串中包含指定子串的数目
     * @param str 被检索字符串
     * @param toCount 检索字符串
     * @return  字符串中包含指定字段的数目
     */
    private int countString(String str, String toCount){
        int sum = 0;
        while (str.contains(toCount)){
            str = str.substring(str.indexOf(toCount) + toCount.length());
            sum++;
        }
        return sum;
    }

    /**
     * 计算各种图片的数量 jpg jpeg png bmp
     */
    private void countType(String fileName){
        if(fileName.endsWith(".jpg")){
            jpgCount++;
        }else if(fileName.endsWith(".jpeg")){
            jpegCount++;
        }else if (fileName.endsWith(".png")){
            pngCount++;
        }else if(fileName.endsWith(".bmp")){
            bmpCount++;
        }
    }

    public ArrayList<Record> getRecords(){
        return records;
    }

    public void printResult(){
        if(!records.isEmpty()){
            Iterator<Record> it = records.listIterator();
            while (it.hasNext()){
                System.out.println(it.next());
            }
        }else {
            System.out.println("未进行过检查！");
        }
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getSecretCount() {
        return secretCount;
    }

    public void setSecretCount(int secretCount) {
        this.secretCount = secretCount;
    }

    public int getJpgCount() {
        return jpgCount;
    }

    public void setJpgCount(int jpgCount) {
        this.jpgCount = jpgCount;
    }

    public int getJpegCount() {
        return jpegCount;
    }

    public void setJpegCount(int jpegCount) {
        this.jpegCount = jpegCount;
    }

    public int getPngCount() {
        return pngCount;
    }

    public void setPngCount(int pngCount) {
        this.pngCount = pngCount;
    }

    public int getBmpCount() {
        return bmpCount;
    }

    public void setBmpCount(int bmpCount) {
        this.bmpCount = bmpCount;
    }
}
