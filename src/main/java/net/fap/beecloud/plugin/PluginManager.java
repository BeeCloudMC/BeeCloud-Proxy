package net.fap.beecloud.plugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class PluginManager {
	private URLClassLoader urlClassLoader;

	public PluginManager(List<PluginBase> plugins) throws MalformedURLException {
		init(plugins);
	}

	private void init(List<PluginBase> plugins) throws MalformedURLException {
		int size = plugins.size();
		URL[] urls = new URL[size];

		for (int i = 0; i < size; i++) {
			PluginBase plugin = plugins.get(i);
			String filePath = plugin.getJar();

			urls[i] = new URL("file:" + filePath);
		}

		urlClassLoader = new URLClassLoader(urls);
	}

	public PluginCase getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clazz = urlClassLoader.loadClass(className);
		Object instance = clazz.newInstance();

		return (PluginCase) instance;
	}
}
