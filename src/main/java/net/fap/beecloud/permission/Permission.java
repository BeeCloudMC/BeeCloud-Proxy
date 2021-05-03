package net.fap.beecloud.permission;

import net.fap.beecloud.SynapsePlayer;

import java.util.ArrayList;

/**
 * 权限类
 *
 * @author catrainbow
 */

public class Permission {

    public static final String DEFAULT_OP = "op";
    public static final String DEFAULT = "default";

    public ArrayList<String> permissionList;
    public SynapsePlayer player;

    public Permission(SynapsePlayer player)
    {
        this.player = player;
    }

}
