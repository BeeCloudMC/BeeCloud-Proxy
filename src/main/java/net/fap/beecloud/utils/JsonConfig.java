package net.fap.beecloud.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

public final class JsonConfig {
	@Getter
	private final File path;
	@Getter
	@Setter
	private LinkedHashMap<String, Object> data;

	public JsonConfig(String path) {
		this.path = FileUtil.file(path);
		FileUtil.touch(this.path);
		this.reload();
	}

	public JsonConfig(File file) {
		this.path = file;
		FileUtil.touch(this.path);
		this.reload();
	}

	public Object getRaw(String key) {
		return this.data.get(key);
	}

	public <T> T get(String key, Class<T> type) {
		return Convert.convert(type, this.getRaw(key));
	}

	public String getString(String key) {
		return Convert.toStr(this.getRaw(key));
	}

	public int getInteger(String key) {
		return Convert.toInt(this.getRaw(key));
	}

	public long getLong(String key) {
		return Convert.toLong(this.getRaw(key));
	}

	public byte getByte(String key) {
		return Convert.toByte(this.getRaw(key));
	}

	public char getChar(String key) {
		return Convert.toChar(this.getRaw(key));
	}

	public HashMap<String, Object> getMap(String key) {
		return (HashMap<String, Object>) Convert.toMap(String.class, Object.class, this.getRaw(key));
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
