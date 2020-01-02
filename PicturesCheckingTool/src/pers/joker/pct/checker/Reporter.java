package pers.joker.pct.checker;

import pers.joker.pct.models.Record;
import pers.joker.pct.utils.TimeUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 报告生成类
 */
public class Reporter {

    public Reporter(){

    }

    /**
     * 生成检查报告 - html文件
     * @param checker 图片检查类
     */
    public boolean report(ImageChecker checker, String filePath){
        ArrayList<Record> records = checker.getRecords();
        File file = new File(filePath);
        if(file.exists()){
            System.out.println("文件已存在！");
            return false;
        }else{
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            FileWriter writer = new FileWriter(file);
            //将报告模板写入文件中
            StringBuffer buffer = new StringBuffer();
            buffer.append("<!DOCTYPE html>");
            buffer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            buffer.append("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Top 10 Express Table Designs - Smashing Magazine Source</title>");
            buffer.append("<style type=\"text/css\">body{line-height:1.6em;}h2{text-align:center;}a{text-decoration:none;color:inherit;}#hor-minimalist-a{font-family:\"Lucida Sans Unicode\",\"Lucida Grande\",Sans-Serif;font-size:12px;background:#fff;margin:45px;width:480px;border-collapse:collapse;text-align:left;}#hor-minimalist-a th{font-size:14px;font-weight:normal;color:#039;padding:10px 8px;border-bottom:2px solid #6678b1;}#hor-minimalist-a td{color:#669;padding:9px 8px 0px 8px;}#hor-minimalist-a tbody tr:hover td{color:#009;}#hor-zebra{font-family:\"Lucida Sans Unicode\",\"Lucida Grande\",Sans-Serif;font-size:12px;margin:45px;width:95%;text-align:left;border-collapse:collapse;}#hor-zebra th{font-size:14px;font-weight:normal;padding:10px 8px;color:#039;}#hor-zebra td{padding:8px;color:#669;}#hor-zebra .odd{background: #e8edff;}.secret td{color:red!important;</style></head>");
            buffer.append("<body><h2>检 查 报 告</h2>");
            buffer.append("<table id=\"hor-minimalist-a\" summary=\"Employee Pay Sheet\"><thead><tr><th scope=\"col\"></th><th scope=\"col\"></th><th scope=\"col\"></th><th scope=\"col\"></th></tr></thead>");
            //开始时间
            buffer.append("<tbody><tr><td>开始时间</td><td>" + dateFormat.format(checker.getStartTime()));
            //jpg
            buffer.append("</td><td>JPG</td><td>" + checker.getJpgCount());
            //结束时间
            buffer.append("</td></tr><tr><td>结束时间</td><td>" + dateFormat.format(checker.getEndTime()));
            //jpeg
            buffer.append("</td><td>JPEG</td><td>" + checker.getJpegCount());
            //检查总时长
            buffer.append("</td></tr><tr><td>检查时长</td><td>" + TimeUtil.timeFormat(checker.getEndTime() - checker.getStartTime()));
            //png
            buffer.append("</td><td>PNG</td><td>" + checker.getPngCount());
            //记录条数
            buffer.append("</td></tr><tr><td>检查图片总数目</td><td>" + records.size());
            //BMP
            buffer.append("</td><td>BMP</td><td>" + checker.getBmpCount());
            //涉密文件数目
            buffer.append("</td></tr><tr><td>含有涉密字段图片数目</td><td>" + checker.getSecretCount());
            buffer.append("</td></tr></tbody></table><table id=\"hor-zebra\" summary=\"Employee Pay Sheet\"><thead><tr><th scope=\"col\">序号</th><th scope=\"col\">文件路径</th><th scope=\"col\">文件名</th><th scope=\"col\">图片格式</th><th scope=\"col\">“涉密”</th><th scope=\"col\">“秘密”</th><th scope=\"col\">“机密”</th><th scope=\"col\">“绝密”</th><th scope=\"col\">“保密”</th><th scope=\"col\">“泄密”</th></tr></thead><tbody>");

            //循环写入记录
            Iterator<Record> it = records.listIterator();
            int index = 1;
            while (it.hasNext()){
                Record record = it.next();
                buffer.append("<tr");

                //修改奇数行的背景颜色和含有涉密字段记录的字体颜色
                //注意判断的顺序
                if(index % 2 != 0 && record.isSecret()){ //如果既是奇数行又含有涉密字段
                    buffer.append(" class=\"odd secret\"");
                }else if(index % 2 != 0){       //如果仅是奇数行
                    buffer.append(" class=\"odd\"");
                }else if(record.isSecret()){    //如果仅含有涉密字段
                    buffer.append(" class=\"secret\"");
                }

                buffer.append("><td>" + index + "</td><td><a href=\""
                        + record.getFilePath() + "\" target=\"blank\">"
                        + record.getFilePath() + "</a></td><td>"
                        + record.getFileName() + "</td><td>"
                        + record.getFileName().substring(record.getFileName().lastIndexOf('.') + 1).toUpperCase() + "</td><td>"
                        + record.getCount()[0] + "</td><td>"
                        + record.getCount()[1] + "</td><td>"
                        + record.getCount()[2] + "</td><td>"
                        + record.getCount()[3] + "</td><td>"
                        + record.getCount()[4] + "</td><td>"
                        + record.getCount()[5] + "</td></tr>");

                //序号加1
                index++;
            }

            buffer.append("</tbody></table></body></html>");

            //写入文件
            writer.write(buffer.toString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
