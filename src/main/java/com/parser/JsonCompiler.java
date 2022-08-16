package com.parser;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class JsonCompiler {
//"(\w+)": "(.+)"
    private List<String> jsonToLines(InputStream jsonInputStream) {
        StringBuilder sb = new StringBuilder();
        try(InputStream reader = new BufferedInputStream(jsonInputStream)) {
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
        List<String> list = jsonToLines(inputStreamXml);
        Map<String,Object> map = phoneFromFile(list);
        return map;
    }
    private Map<String,Object> phoneFromFile(List<String> lines) {
        Map<String, Object> result = new HashMap<>();
        String title,value;
        int index1, index2,index3;
        for (String i: lines) {
            if (i.contains(":") && !i.contains("{")) {
                index1 = i.indexOf("\"");
                index2 = i.indexOf(":");
                index3 = i.lastIndexOf("\"");
                title = i.substring(index1+1, index2-1);
                value = i.substring(index2+3, index3);
                result.put(title, value);
            }
        }
        return result;
    }


}
