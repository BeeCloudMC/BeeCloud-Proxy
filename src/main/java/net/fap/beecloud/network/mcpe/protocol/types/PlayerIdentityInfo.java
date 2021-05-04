package net.fap.beecloud.network.mcpe.protocol.types;

import lombok.Getter;
import lombok.Setter;
import net.fap.beecloud.utils.BinaryStream;

public class PlayerIdentityInfo {
	@Getter
	@Setter
	public String name;
	@Getter
	@Setter
	public String UUID;
	@Getter
	@Setter
	public String addr;

	public void encode(BinaryStream out) {
		out.putString(this.getName());
		out.putString(this.getUUID());
		out.putString(this.getAddr());
	}

	public void decode(BinaryStream in) {
		this.setName(in.getString());
		this.setUUID(in.getString());
		this.setAddr(in.getString());
	}
}
