package pers.joker.pct.gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * 图片过滤器
 * TODO
 * @author Joker
 * @version 1.0
 * @date 2018年12月4日 下午8:10:26
 * @copyright Joker A.C.
 * @remarks TODO
 *
 *
 */
public class ImageFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if(f.isDirectory()) {
			return true;
		}
		return f.getName().endsWith(".jpg") || f.getName().endsWith(".jpeg") || f.getName().endsWith(".png") || f.getName().endsWith(".bmp");
	}

	@Override
	public String getDescription() {
		return "*.jpg,*.jpeg,*.png,*bmp";
	}

}
