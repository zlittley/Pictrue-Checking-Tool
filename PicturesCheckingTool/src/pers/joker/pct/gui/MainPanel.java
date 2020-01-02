package pers.joker.pct.gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主窗口面板
 */
public class MainPanel extends JPanel {
    private static MainPanel mainPanel = null;
    private JPanel startPanel;  //起始界面
    private JPanel checkPanel;  //检查界面
    private JPanel historyPanel;    //历史检查记录界面
    private JPanel aboutPanel;  //关于界面

    private MainPanel(){
        CardLayout layout = new CardLayout();
        setLayout(layout);

        add(StartPanel.getInstance(), "start");
        add(CheckPanel.getInstance(), "check");
    }

    public static MainPanel getInstance(){
        if(mainPanel == null){
            mainPanel = new MainPanel();
        }
        return mainPanel;
    }
}
