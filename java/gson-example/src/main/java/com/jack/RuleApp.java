package com.jack;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by jack on 17-2-22.
 */
public class RuleApp {

    public static Rule[] parse(String json) {
        System.out.println("start parsing "+json);
        Gson gson=new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();
        Rule[] rules=new Rule[array.size()];
        for (int i=0; i<array.size(); i++) {
            try {
                JsonObject obj=(JsonObject)array.get(i);
                if (obj.has("type")) {
                    String type=obj.get("type").getAsString();
                    if ("regex".equals(type))
                        rules[i]=gson.fromJson(array.get(i), RegexRule.class);
                    else if ("number".equals(type))
                        rules[i]=gson.fromJson(array.get(i), NumberRule.class);
                    else {
                        System.out.printf("%s type of rule is not suport\n", type);
                        return null;
                    }
                }
                if (rules[i]==null || !rules[i].legal()) {
                    System.out.println("not legal rule "+array.get(i));
                    return null;
                }
            } catch (Exception e) {
                System.out.println("get execption when fromJson("+i+")="+array.get(i));
                return null;
            }
        }
        for (Rule rule:rules)
            System.out.println("rule: " + rule);
        return rules;
    }
    public static void main(String[] args) {
        Rule[] rules;
        rules=parse("[{type=regex,regex=abc},{type=number,value=2}]");
        rules=parse("[{type=regex,regex=abc},{type=number,value=2},{type=auto,value=2}]");// auto not support
        rules=parse("[{type=regex,regex=abc},{type=number,value=2},{type=number,regex=2}]");//not legal for value is null
        rules=parse("[{type=regex,regex=abc},{type=number,value=2},{type=number}]");// not legal for regex is null
    }
}
