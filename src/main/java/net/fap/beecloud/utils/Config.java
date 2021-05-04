package net.fap.beecloud.utils;

import net.fap.beecloud.Server;
import net.fap.beecloud.plugin.PluginBase;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插件配置文件类
 *
 * @author catrainbow
 */

public class Config {

    private String configFilePath;
    private File configFile;
    private String fileName;
    private static FileUtil fileUtil = new FileUtil();

    public Config(String pluginPath, String file) {
        try {
            String path = Server.getServer().getPluginData() + File.separator + pluginPath;
            File dir = new File(path);
            this.fileName = file;
            if (!dir.exists()) dir.mkdir();
            File config = new File(path + File.separator + file + ".yml");
            if (!config.exists()) config.createNewFile();
            this.configFilePath = path + File.separator + file + ".yml";
            this.configFile = config;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(String index) {
        if (getConfigValue(index) == null) return false;
        else return true;
    }

    public void putObject(String index, Object value) {
        String string = index + ":" + value;
        this.writeData(string);
    }

    public void putMap(String index, Map<Object, Object> value) {
        String string = index + ":";
        for (Object obj : value.keySet())
            string += "|" + String.valueOf(obj) + ":" + String.valueOf(value.get(obj));
        this.writeData(string);
    }

    public void putArray(String index, List<Object> value) {
        String array = index + ":";
        for (Object obj : value)
            index += "|" + String.valueOf(value);
        this.writeData(array);
    }

    public Map<String, Object> getMap(String index) {
        String mapValue = this.getConfigValue(index);
        String[] mapValue2 = mapValue.split("\\:");
        Map<String, Object> map = new HashMap<>();
        for (String str : mapValue2)
            map.put(String.valueOf(str.split("\\:")[0]), str.split("\\:")[1]);
        return map;
    }

    public List<String> getStringList(String index) {
        String arrayValue = this.getConfigValue(index);
        List<String> list = new ArrayList<>();
        String[] value = arrayValue.split("\\|");
        for (String str : value)
            list.add(str);
        return list;
    }

    public List<Integer> getIntegerList(String index) {
        String arrayValue = this.getConfigValue(index);
        List<Integer> list = new ArrayList<>();
        String[] value = arrayValue.split("\\|");
        for (String str : value)
            list.add(Integer.valueOf(str));
        return list;
    }

    public List<Double> getDoubleList(String index) {
        String arrayValue = this.getConfigValue(index);
        List<Double> list = new ArrayList<>();
        String[] value = arrayValue.split("\\|");
        for (String str : value)
            list.add(Double.valueOf(str));
        return list;
    }

    public List<Boolean> getBooleanList(String index) {
        String arrayValue = this.getConfigValue(index);
        List<Boolean> list = new ArrayList<>();
        String[] value = arrayValue.split("\\|");
        for (String str : value)
            list.add(Boolean.valueOf(str));
        return list;
    }

    public List<Long> getLongList(String index) {
        String arrayValue = this.getConfigValue(index);
        List<Long> list = new ArrayList<>();
        String[] value = arrayValue.split("\\|");
        for (String str : value)
            list.add(Long.valueOf(str));
        return list;
    }

    public boolean getBoolean(String index) {
        return Boolean.valueOf(this.getConfigValue(index));
    }

    public String getString(String index) {
        return String.valueOf(this.getConfigValue(index));
    }

    public int getInteger(String index) {
        return Integer.valueOf(this.getConfigValue(index));
    }

    public double getDouble(String index) {
        return Double.valueOf(this.getConfigValue(index));
    }

    public long getLong(String index) {
        return Long.valueOf(this.getConfigValue(index));
    }

    private String getConfigValue(String index) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.configFilePath), Charset.forName("utf-8")));
            String lineData = null;
            while ((lineData = br.readLine()) != null) {
                String[] str1 = lineData.split("\\:");
                if (str1[0].equals(index)) return str1[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeData(String data) {
        try {
            if (!configFile.exists()) configFile.createNewFile();
            if (contains(data.split("\\:")[0]))
                remove(data.split("\\:")[0]);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFilePath, true), "UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            ;
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(String index) {
        if (contains(index)) fileUtil.removeLineFromFile(fileName + ".yml", index + ":" + getConfigValue(index));
    }

}
