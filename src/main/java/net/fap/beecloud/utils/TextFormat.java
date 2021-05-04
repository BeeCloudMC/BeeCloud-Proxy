package net.fap.beecloud.utils;

import org.fusesource.jansi.Ansi;

/**
 * 处理字符串为彩色
 *
 * @author catrainbow
 */

public class TextFormat {

    public static Ansi color(String str, Ansi.Color color) {
        return Ansi.ansi().a(color).a(str).reset();
    }

}
