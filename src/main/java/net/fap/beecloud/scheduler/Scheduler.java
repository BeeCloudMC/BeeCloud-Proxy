package net.fap.beecloud.scheduler;

import java.util.Date;
import java.util.Timer;

/**
 * 计时器类
 * 毫秒单位
 *
 * @author catrainbow
 */

public class Scheduler {

	/**
	 * 延时计时器
	 *
	 * @param task  PluginTask
	 * @param delay 延时(ms)
	 */
	public void schedulerTask(Task task, long delay) {
		Timer timer = new Timer();
		timer.schedule(task, delay);
	}

	/**
	 * 循环计时器
	 *
	 * @param task  PluginTask
	 * @param delay 循环间隔(ms)
	 */
	public void schedulerDelayedTask(Task task, long delay) {
		Date date = new Date();
		Timer timer = new Timer();
		timer.schedule(task, date, delay);
	}

	/**
	 * 延时循环计时器
	 *
	 * @param task   PluginTask
	 * @param delay  延时(ms)
	 * @param period 循环间隔(ms)
	 */
	public void schedulerRepeatingTask(Task task, long delay, long period) {
		Timer timer = new Timer();
		timer.schedule(task, delay, period);
	}

}
