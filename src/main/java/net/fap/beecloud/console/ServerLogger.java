package net.fap.beecloud.console;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author catrainbow
 */

public class ServerLogger {

	public static void info(String message) {
		AnsiConsole.systemInstall();
		System.out.println("" + Ansi.ansi().fg(Ansi.Color.BLUE).a(getTime()).reset() + Ansi.ansi().fg(Ansi.Color.YELLOW).a("[INFO]").reset() + Ansi.ansi().fg(Ansi.Color.DEFAULT).a(message));
		AnsiConsole.systemUninstall();
	}

	public static void waring(String message) {
		AnsiConsole.systemInstall();
		System.out.println("" + Ansi.ansi().fg(Ansi.Color.BLUE).a(getTime()).reset() + Ansi.ansi().fg(Ansi.Color.RED).a("[WARING]").reset() + Ansi.ansi().fg(Ansi.Color.DEFAULT).a(message));
		AnsiConsole.systemUninstall();
	}

	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return "[" + sdf.format(date) + "]";
	}

}
