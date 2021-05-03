package net.fap.beecloud.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import com.google.gson.Gson;

import java.io.File;
import java.util.LinkedHashMap;

public final class JsonConfig extends Config {
	public JsonConfig(File path) {
		super(path);
	}

	public Object getRaw(String key) {
		return this.data.get(key);
	}

	public void save() {
		Gson gson = new Gson();
		FileUtil.writeBytes(Convert.toPrimitiveByteArray(gson.toJson(this.data)), this.path);
	}

	@SuppressWarnings("unchecked")
	public void reload() {
		Gson gson = new Gson();
		this.data = (LinkedHashMap<String, Object>) gson.fromJson(FileUtil.getUtf8Reader(path), LinkedHashMap.class);
	}
}
