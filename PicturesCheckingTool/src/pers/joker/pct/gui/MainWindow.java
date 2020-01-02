package pers.joker.pct.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 * 程序主窗口
 */
public class MainWindow extends JFrame {
    /* 单例模式 */
    private static MainWindow mainWindow = null;
    public final int WIDTH = 800;
    public final int HEIGHT = 550;

    private MainWindow(){
        //主窗口属性设置
        setTitle("Pictures Checking Tool");
        Image icon = Toolkit.getDefaultToolkit().getImage("src/pers/joker/pct/resources/icons/secret_icon_png.png");
        setIconImage(icon);
        setSize(new Dimension(WIDTH, HEIGHT));
        //设置窗口居中
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //setBackground(Color.red);
        setContentPane(MainPanel.getInstance());
        //add(MainPanel.getInstance());
        //getContentPane().add(MainPanel.getInstance(), BorderLayout.CENTER);
        setVisible(true);
    }

    public static MainWindow getInstance(){
        if(mainWindow == null){
            mainWindow = new MainWindow();
        }
        return mainWindow;
    }

}
