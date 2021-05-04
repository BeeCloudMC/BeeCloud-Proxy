package net.fap.beecloud.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import net.fap.beecloud.Server;
import net.fap.beecloud.plugin.PluginBase;
import net.fap.beecloud.plugin.PluginLogger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLParser {
    public static List<PluginBase> getPluginList() throws DocumentException {
        List<PluginBase> list = new ArrayList<>();

        String[] file = Server.getServer().getPluginFile().list();
        for (int i = 0; i < file.length; i++) {
            String fileName = file[i];
            if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("jar")) {
                try {
                    String name = null;
                    String main = null;
                    String jar = Server.getServer().getPluginFile() + File.separator + fileName;
                    List<List<String>> pluginYml = readZipFile(Server.getServer().getPluginFile() + File.separator + fileName);
                    for (List<String> list1 : pluginYml)
                        for (String str : list1) {
                            if (str.contains("name")) name = getPluginInfo(str, "name");
                            else if (str.contains("main")) main = getPluginInfo(str, "main");
                        }
                    if (name != null && main != null) {
                        PluginBase plugin = new PluginBase();
                        plugin.setName(name);
                        plugin.setJar(jar);
                        plugin.setClassName(main);
                        plugin.setPluginLogger(new PluginLogger());
                        list.add(plugin);
                        plugin.getLogger().setPluginBase(plugin);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    private static String getPluginInfo(String line, String target) {
        if (line.contains(target)) {
            String[] strings = line.split("\\:");
            return strings[1];
        } else return null;
    }

    //读取zip文件内的文件,返回文件名称列表
    public static List<String> readZipFileName(String path) {
        List<String> list = new ArrayList<>();
        try {
            ZipFile zipFile = new ZipFile(path);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                list.add(entries.nextElement().getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //读取zip文件内的文件,返回文件内容列表
    public static List<List<String>> readZipFile(String path) {
        List<String> list = new ArrayList<>();
        List<List<String>> ddlList = null;
        try {
            JarFile zipFile = new JarFile(path);
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            JarInputStream zin = new JarInputStream(in);
            ZipEntry ze;
            while ((ze = zin.getNextEntry()) != null) {
                ddlList = new ArrayList<>();
                if (ze.isDirectory()) {
                } else {
                    //System.err.println("file - " + ze.getName() + " : "+ ze.getSize() + " bytes");
                    long size = ze.getSize();
                    if (size > 0) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(ze), Charset.forName("utf-8")));
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] index = line.split(",");
                            List<String> indexList = Arrays.asList(index);
                            ddlList.add(indexList);
                        }
                        br.close();
                    }
                }
                //处理ddlList,此时ddlList为每个文件的内容,while每循环一次则读取一个文件
            }
            zin.closeEntry();
            return ddlList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //此处返回无用,懒得修改了
        return ddlList;
    }

}