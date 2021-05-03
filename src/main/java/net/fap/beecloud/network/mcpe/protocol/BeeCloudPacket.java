package net.fap.beecloud.network.mcpe.protocol;

/**
 * BeeCloud Packet
 * 以字符串形式存在的数据包
 * 可以转换为字符并通过Socket实现与Nukkit服务端的数据包交互
 *
 * @author catrainbow
 */

public class BeeCloudPacket {

	/**
	 * 将字符串转换为数据包的函数
	 *
	 * @param pk2 字符串形式的数据包
	 */
	public void putString(String[] pk2) {
	}

	/**
	 * 当数据包被回发时执行的函数
	 */
	public void resend() {
	}

	/**
	 * 将数据包转为字符串的函数
	 *
	 * @return 结果
	 */
	public String to_String() {
		return null;
	}

}
