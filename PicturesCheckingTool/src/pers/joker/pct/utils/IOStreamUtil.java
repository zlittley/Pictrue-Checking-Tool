package pers.joker.pct.utils;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * IO流工具类
 */
public class IOStreamUtil {
    private static InputStream defaultIn = System.in;
    private static OutputStream defaultOut = System.out;

    private IOStreamUtil(){

    }

    private static void updateTextArea(final String text, JTextArea textArea) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textArea.append(text);
                textArea.setCaretPosition(textArea.getText().length());
            }
        });
    }

    /**
     * 重定向输出流到文本域
     * @param textArea
     */
    public static void redirectOutputStream(JTextArea textArea){
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b), textArea);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len), textArea);
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

    //重置输出流，但不知道是否可行
    public static void resetOutputStream(){
        System.setOut(new PrintStream(defaultOut, true));
        System.setErr(new PrintStream(defaultOut, true));
    }
}
