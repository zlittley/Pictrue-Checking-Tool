package pers.joker.pct.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 圆角按钮
 * TODO
 * @author Joker
 * @version 1.0
 * @date 2018年12月2日 上午10:34:35
 * @copyright Joker A.C.
 * @remarks TODO
 *
 *
 */
public class RoundedRectangleButton extends JButton implements MouseListener {
	private static final long serialVersionUID = -4948836488972082987L;
	
	private Shape shape;
	private Color clickedColor;
	private Color quitColor;
	
	public RoundedRectangleButton() {
		this("Button");
	}
	
	public RoundedRectangleButton(String text) {
		this(text, null);
	}
	
	public RoundedRectangleButton(ImageIcon icon) {
		this(null, icon);
	}
	
	public RoundedRectangleButton(String text, ImageIcon icon) {
		this(text, icon, Color.WHITE);
	}
	
	public RoundedRectangleButton(String text, ImageIcon icon, Color quitColor) {
		super(text, icon);
		this.clickedColor = Color.LIGHT_GRAY;
		this.quitColor = quitColor;
		setContentAreaFilled(false);
		setFocusable(false);
		setIconTextGap(10);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(clickedColor);
		} else {
			g.setColor(quitColor);
		}
		//填充圆角矩形区域 也可以为其它的图形
		g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 20, 20);
		super.paintComponent(g);
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		g.setColor(clickedColor);	//设置成点击时的颜色
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
	}
	
	@Override
	public boolean contains(int x, int y) {
		if(shape == null || !(shape.getBounds().equals(getBounds()))) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
		}
		return shape.contains(x, y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
