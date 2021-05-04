package net.fap.beecloud.console.simple;

import net.fap.beecloud.console.ServerLogger;

public class StopCommand extends CommandHandler {

    public String commandStr = "stop";
    public String commandUsage = "停止服务器";

    public StopCommand() {
        setCommandStr(this.commandStr, this.commandUsage);
    }

    @Override
    public void setCommandStr(String commandStr, String commandUsage) {
        super.setCommandStr(commandStr, commandUsage);
    }

    @Override
    public void runCommand(String args[], String commandSender) {
        if (args.length == 1)
            System.exit(0);
        else ServerLogger.info("Usage: " + commandUsage);
        super.runCommand(args, commandSender);
    }

}
