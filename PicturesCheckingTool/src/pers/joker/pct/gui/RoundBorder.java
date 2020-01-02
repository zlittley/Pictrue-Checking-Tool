package pers.joker.pct.gui;

import java.awt.*;

import javax.swing.border.Border;

/**
 * 圆角边框
 * TODO
 * @author Joker
 * @version 1.0
 * @date 2018年11月18日 上午10:57:26
 * @copyright Joker A.C.
 * @remarks TODO
 *
 *
 */
public class RoundBorder implements Border {
	
	private Color color;
	
	public RoundBorder() {
		this(Color.BLACK); //默认黑色
	}
	
	public RoundBorder(Color color) {
		this.color = color;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(color);
		g2d.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 20, 20);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(0, 10, 0, 10);
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

}
