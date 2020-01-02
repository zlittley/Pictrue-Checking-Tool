package pers.joker.pct.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 起始面板
 */
public class StartPanel extends JPanel {
    private static StartPanel startPanel = null;
    private String path;    //检查路径

    JLabel hint1 = new JLabel("* 文件或目录路径为空！");
    JLabel hint2 = new JLabel("* 不存在这个文件或目录!");

    private StartPanel(){
        setLayout(null);    //绝对布局
        //setBackground(Color.lightGray);

        //文字提示
        JLabel label = new JLabel("文件或目录路径：");
        label.setFont(new Font("Ubuntu Regular",Font.BOLD,18));
        label.setBounds(60, 125, 150, 30);

        //文本框
        RoundCornerTextField textField = new RoundCornerTextField(new Color(0,30,255));
        int textFieldWidth = 600;
        int textFieldHeight = 50;
        textField.setFont(new Font("Ubuntu Regular",Font.PLAIN,18));
        textField.setBounds(60, 170, textFieldWidth, textFieldHeight);

        //文件选择按钮
        RoundedRectangleButton fileChooserButton = new RoundedRectangleButton("   ");
        fileChooserButton.setIconTextGap(0);
        int buttonWidth = 50;
        int buttonHeight = 50;
        fileChooserButton.setBounds(680, 170, buttonWidth, buttonHeight);
        ImageIcon icon = new ImageIcon("src/pers/joker/pct/resources/icons/file.png");
        ImageIcon selectedIcon = new ImageIcon("src/pers/joker/pct/resources/icons/file_selected.png");
        icon.setImage(icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        selectedIcon.setImage(selectedIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        fileChooserButton.setIcon(icon);
        fileChooserButton.setSelectedIcon(selectedIcon);
        fileChooserButton.setBorder(new TextFieldBorder());
        fileChooserButton.setToolTipText("选择文件或目录");
        JFileChooser fileChooser = new JFileChooser();
        //设置文件选择器可以选择文件或目录
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //设置文件过滤器
        fileChooser.setFileFilter(new ImageFileFilter());
        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //如果选择了文件或目录
                if(fileChooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    textField.setText(file.getAbsolutePath());
                    //重新绘制刷新文本框中的内容
                    repaint();
                }
            }
        });

        //提示信息
        hint1.setForeground(Color.red);
        hint2.setForeground(Color.red);
        hint1.setBounds(60, 230, 200, 30);
        hint2.setBounds(60, 230, 200, 30);
        hint1.setVisible(false);
        hint2.setVisible(false);

        //开始检查按钮
        RoundedRectangleButton startButton = new RoundedRectangleButton("开 始 检 查");
        startButton.setFont(new Font("Ubuntu Regular", Font.BOLD, 16));
        startButton.setIconTextGap(0);
        startButton.setBounds(340, 280, 120, 50);
        startButton.setBorder(new TextFieldBorder());
        startButton.setToolTipText("开始检查");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText() == null || textField.getText().trim().equals("")){
                    System.out.println("输入框为空！");
                    hint1.setVisible(true);
                    hint2.setVisible(false);
                    repaint();
                }else if(!new File(textField.getText()).exists()){
                    System.out.println("路径不存在！");
                    hint1.setVisible(false);
                    hint2.setVisible(true);
                    repaint();
                }else {
                    //path = textField.getText().trim();
                    //开启检查进程并监控检查是否在进行
                    CheckPanel.getInstance().check(textField.getText().trim());
                    System.out.println("开始检查");
                    //显示检查页面
                    CardLayout layout = (CardLayout) getParent().getLayout();
                    layout.show(getParent(), "check");
                }
            }
        });
        
        add(label);
        add(textField);
        add(fileChooserButton);
        add(hint1);
        add(hint2);
        add(startButton);
    }

    public String getPath() {
        return path;
    }

    public JLabel getHint1() {
        return hint1;
    }

    public JLabel getHint2() {
        return hint2;
    }

    public static StartPanel getInstance(){
        if(startPanel == null){
            startPanel = new StartPanel();
        }
        return startPanel;
    }

}
