package com.parser;

import java.io.InputStream;
import java.util.*;

public class JsonCompiler {

    public List<String> jsonToLines(InputStream jsonInputStream) {
        List<String> jsonText = new ArrayList<>();
        Scanner scanner = new Scanner(jsonInputStream);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            jsonText.add(line.trim());
        }
        return jsonText;
    }

    public Map<String,Object> phoneFromFile(List<String> lines) {
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
