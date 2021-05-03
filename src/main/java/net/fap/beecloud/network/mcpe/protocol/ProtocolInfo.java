package net.fap.beecloud.network.mcpe.protocol;

public interface ProtocolInfo {
	byte LOGIN = 0x01;
	byte DISCONNECT = 0x02;
	byte PROXY_EVENT = 0x03;
	byte TEXT = 0x04;
}
