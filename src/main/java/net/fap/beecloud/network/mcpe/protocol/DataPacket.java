package net.fap.beecloud.network.mcpe.protocol;

import lombok.Getter;
import net.fap.beecloud.network.mcpe.NetworkSessionAdapter;
import net.fap.beecloud.utils.BinaryStream;

public abstract class DataPacket extends BinaryStream implements Cloneable {
	@Getter
	private boolean encoded = false;

	public abstract void handle(NetworkSessionAdapter adapter);

	public final void encode() {
		if (!this.encoded) {
			this.encodeHeader();
			this.encodePayload();
			this.encoded = true;
		}
	}

	public void encodeHeader() {
		this.putByte(this.PID());
	}

	public void encodePayload() {

	}

	public void decodeHeader() {
		this.getByte();
	}

	public void decodePayload() {

	}

	public final void decode() {
		this.decodeHeader();
		this.decodePayload();
	}

	public abstract byte PID();

	@Override
	public DataPacket clone() {
		DataPacket pk = null;
		try {
			pk = (DataPacket) super.clone();
		} catch (CloneNotSupportedException ignored) {

		}
		return pk;
	}
}
