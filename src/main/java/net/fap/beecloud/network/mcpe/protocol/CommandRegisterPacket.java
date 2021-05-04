package net.fap.beecloud.network.mcpe.protocol;

/**
 * 当BeeCloud试图在子服注册一个命令时会发送这个数据包
 *
 * @author catrainbow
 */

public class CommandRegisterPacket extends BeeCloudPacket {

    private String commandStr;
    private String commandUsage;

    public CommandRegisterPacket(String commandStr, String commandUsage) {
        this.commandStr = commandStr;
        this.commandUsage = commandUsage;
    }

    public void setCommandStr(String commandStr) {
        this.commandStr = commandStr;
    }

    public void setCommandUsage(String commandUsage) {
        this.commandUsage = commandUsage;
    }

    @Override
    public void resend() {
        super.resend();
    }

    @Override
    public String to_String() {
        return "CommandRegisterPacket:" + commandStr + ":" + commandUsage;
    }
}
