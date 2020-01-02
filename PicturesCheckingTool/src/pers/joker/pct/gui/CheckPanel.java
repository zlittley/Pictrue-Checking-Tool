package pers.joker.pct.gui;

import pers.joker.pct.checker.ImageChecker;
import pers.joker.pct.checker.Reporter;
import pers.joker.pct.models.Record;
import pers.joker.pct.utils.IOStreamUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * 检查面板
 */
public class CheckPanel extends JPanel {
    private static CheckPanel checkPanel = null;

    Thread checkThread = null;  //检查进程
    ImageChecker checker = new ImageChecker();
    Reporter reporter = new Reporter();
    JTextArea textArea = new JTextArea();
    JLabel label1 = new JLabel("正在检查...");
    JLabel label2 = new JLabel("检查完成");
    JButton reportButton = new JButton("生成报告");
    boolean flag = false;
    boolean isReported = false;

    private CheckPanel(){
        setLayout(null);

        label1.setBounds(20, 40, 100, 30);
        label2.setBounds(20, 40, 100, 30);
        label1.setVisible(true);
        label2.setVisible(false);

        //返回按钮
        JButton backButton = new JButton("返回");
        backButton.setBounds(680, 40, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkThread != null && checkThread.isAlive()){
                    if(JOptionPane.showConfirmDialog(getParent(), "正在执行图片检查，返回将终止检查进程，确认返回吗？") == JOptionPane.YES_OPTION){
                        checkThread.stop(); //终止检查过程
                        StartPanel.getInstance().getHint1().setVisible(false);
                        StartPanel.getInstance().getHint2().setVisible(false);
                        CardLayout layout = (CardLayout) getParent().getLayout();
                        layout.show(getParent(), "start");
                    }
                }else if (!isReported){
                    if(JOptionPane.showConfirmDialog(getParent(), "还未生成本次检查的报告，返回将丢失检查结果，确认返回吗？") == JOptionPane.YES_OPTION){
                        StartPanel.getInstance().getHint1().setVisible(false);
                        StartPanel.getInstance().getHint2().setVisible(false);
                        CardLayout layout = (CardLayout) getParent().getLayout();
                        layout.show(getParent(), "start");
                    }
                }else {
                    if(JOptionPane.showConfirmDialog(getParent(), "确认返回吗？") == JOptionPane.YES_OPTION){
                        StartPanel.getInstance().getHint1().setVisible(false);
                        StartPanel.getInstance().getHint2().setVisible(false);
                        CardLayout layout = (CardLayout) getParent().getLayout();
                        layout.show(getParent(), "start");
                    }
                }
            }
        });
        //生成报告按钮
        reportButton.setBounds(560, 40, 100, 30);
        reportButton.setVisible(false);
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(getParent(), "确认生成检查报告吗？") == JOptionPane.YES_OPTION){
                    JFileChooser fileChooser = new JFileChooser();
                    //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fileChooser.setSelectedFile(new File("/home/joker/工作/report.html"));
                    if(fileChooser.showSaveDialog(getParent()) == JFileChooser.APPROVE_OPTION){
                        File file = fileChooser.getSelectedFile();
                        if(reporter.report(checker, file.getAbsolutePath())){
                            isReported = true;
                            JOptionPane.showMessageDialog(getParent(),"报告生成成功！");
                        }
                    }
                }
            }
        });

        textArea.setText("文本框");
        textArea.setEditable(false);
//        textArea.setBounds(20, 80, 760, 400);
//        textArea.setBorder(new LineBorder(Color.lightGray));
        textArea.setLineWrap(true); //设置自动换行
        //添加滚动条
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(20, 80, 760, 400);
        scrollPane.setBorder(new LineBorder(Color.lightGray));

        add(label1);
        add(label2);
        add(backButton);
        add(reportButton);
        add(scrollPane);
//        add(textArea);
        //System.out.println("文本框已添加");

        //重定向输出流
        IOStreamUtil.redirectOutputStream(textArea);

        /*不需要使用监控进程，直接在check进程中设置组件可见属性，并用SwingUtilities.invokeLater()刷新界面即可
        //监控检查进程是否运行
        Thread thread = new Thread(()->{
            //System.out.println("开始监听");
            while (true){
                if(checkThread != null && checkThread.isAlive()){
                    if(!flag){   //从停止状态刚转到检查状态
                        flag = true;
                        label1.setVisible(true);
                        label2.setVisible(false);
                        reportButton.setVisible(false);
                    }
                    //System.out.println("在执行");
                    //让光标始终处在文尾
                    //textArea.setCaretPosition(textArea.getText().length());
                    //重新绘制
//                    validate();
//                    repaint();
                }else {
                    if (flag){  //从检查状态刚转到停止状态
                        flag = false;
                        label1.setVisible(false);
                        label2.setVisible(true);
                        reportButton.setVisible(true);
                        //System.out.println("未执行");
                        //让光标始终处在文尾
                        //textArea.setCaretPosition(textArea.getText().length());
                        //重新绘制
//                        validate();
//                        repaint();
                    }
                }
//                try {
//                    wait(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        */
    }

    public void check(String path){
        if(checkThread == null || !checkThread.isAlive()){
            checkThread = new Thread(()->{
                //检查前显示的内容
                label1.setVisible(true);
                label2.setVisible(false);
                reportButton.setVisible(false);

                textArea.setText("");   //重置文本框中的内容
                ArrayList<Record> records = checker.check(path);
                if(records != null && !records.isEmpty()){
                    isReported = false;
                }else{
                    isReported = true;  //防止如果文件夹是空的，返回时不会提示是否要生成报告
                }

                //检查后显示的内容
                label1.setVisible(false);
                label2.setVisible(true);
                reportButton.setVisible(true);
            });
            checkThread.start();
        }
    }

    public ImageChecker getChecker(){
        return checker;
    }

    public static CheckPanel getInstance(){
        if(checkPanel == null){
            checkPanel = new CheckPanel();
        }
        return checkPanel;
    }
}
