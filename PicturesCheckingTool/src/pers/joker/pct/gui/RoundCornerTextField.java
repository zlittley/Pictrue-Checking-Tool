package pers.joker.pct.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

/**
 * 圆角输入框
 * TODO
 * @author Joker
 * @version 1.0
 * @date 2018年11月18日 上午11:15:49
 * @copyright Joker A.C.
 * @remarks TODO
 *
 *
 */
public class RoundCornerTextField extends JTextField{
	private static final long serialVersionUID = 8000533161578429030L;
	private int arcH = 20 ;
    private int arcW = 20 ;
    Shape shape ;
    
    public RoundCornerTextField() {
        setBorder(null);
        setBackground(null);
//        setMargin(new Insets(0, 0, 0, 0));
    }
    
    public RoundCornerTextField(Color color) {
    	setBorder(new RoundBorder(color));
    	setBackground(null);
    }
    
    @Override
    public boolean contains(int x, int y) {
        shape = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight() , 20 , 20) ;
        return shape.contains(x, y);
    }
    
    @Override
    public Insets getInsets() {
    	return new Insets(0, 10, 0, 10);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(50, 50, 50, 100));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH);
        g2.setColor(new Color(255, 255, 255, 100));
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH);
    }
}
