package com.parser;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParser {
    private static List<String> xmlToLines(InputStream xmlInputStream) {
        StringBuilder sb = new StringBuilder();
        try(InputStream reader = new BufferedInputStream(xmlInputStream)) {
            int a = reader.read();
            while (a != -1) {
                sb.append((char) a);
                a = reader.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> list = List.of(sb.toString().split("\r\n"));
        return list;
    }

    public Map<String,Object> phoneFromFileToMap(InputStream inputStreamXml){
        List<String> list = XmlParser.xmlToLines(inputStreamXml);
        Map<String,Object> map = XmlParser.phoneFromFile(list);
        return map;
    }
    private static Map<String,Object> phoneFromFile(List<String> lines) {
        Pattern compile = Pattern.compile("(<.+>(.*?)<.*>)");
        Matcher matcher;
        Map<String,Object> result = new HashMap<>();
        String title;
        int index1,index2,index3;
        for (String i : lines) {
            index1 = i.indexOf("<");
            index2 = i.indexOf(">");
            title = i.substring(index1+1,index2);
            if(title.contains(" ")){
                index3 = title.indexOf(" ");
                title = title.substring(0,index3);
            }
            matcher = compile.matcher(i);
            if (matcher.find()) {
                result.put(title,matcher.group(2));
            }
        }
        return result;
    }
}
