package pers.joker.pct.gui;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 缩放到给定大小的图标
 * TODO
 * @author Joker
 * @version 1.0
 * @date 2018年11月20日 下午7:39:17
 * @copyright Joker A.C.
 * @remarks TODO
 *
 *
 */
public class ScaledIcon extends ImageIcon {
	private static final long serialVersionUID = -5052998730108427044L;
	
	public ScaledIcon(int width, int height, String filename) {
		super(filename);
		setImage(getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

}
