package pers.joker.pct;

import pers.joker.pct.checker.ImageChecker;
import pers.joker.pct.checker.ImageTextRecognizer;
import pers.joker.pct.checker.Reporter;
import pers.joker.pct.gui.MainWindow;
import pers.joker.pct.models.Record;
import pers.joker.pct.utils.BaiduOCR;
import pers.joker.pct.utils.IOStreamUtil;

import java.util.ArrayList;

public class Main {

//    private static String filePath = "/home/joker/工作/课程/保密检查与监督/image/Í¼Æ¬/1.png";
//    private static String dirPath = "/home/joker/工作/课程/保密检查与监督/image/Í¼Æ¬";

    public static void main(String[] args) {
	    // write your code here
        //System.out.println("为什么这里的中文字体一定要用楷体，我吐了。");

        //test:BaiduOCR
        //BaiduOCR.getAuthor();

        //test:ImageTextRecognizer
        //ArrayList<String> list = ImageTextRecognizer.check("/home/joker/工作/课程/保密检查与监督/image/Í¼Æ¬/1.png");
        //String list = ImageTextRecognizer.extract(filePath);
        //System.out.println(list);

        //test:ImageChecker
//        ImageChecker checker = new ImageChecker();
////        ArrayList<Record> records = checker.check(filePath);
//        checker.check(filePath);
//        //checker.printResult();
//        Reporter reporter = new Reporter();
//        reporter.report(checker, "/home/joker/工作/report.html");

        //test:MainWindow
        MainWindow.getInstance();
    }
}
