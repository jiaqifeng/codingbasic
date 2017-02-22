package com.jack;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void primitivesExamples() {
        // Serialization
        Gson gson = new Gson();
        System.out.println("gson.toJson(1)="+gson.toJson(1));
        System.out.println("gson.toJson(\"abcd\")="+gson.toJson("abcd"));
        System.out.println("gson.toJson(new Long(10))="+gson.toJson(new Long(10)));
        int[] values = { 1 };
        System.out.println("gson.toJson({1})="+gson.toJson(values));

                // Deserialization
        int one = gson.fromJson("1", int.class);
        System.out.println("1->"+one);
        Integer one1 = gson.fromJson("1", Integer.class);
        Long one2 = gson.fromJson("1", Long.class);
        Boolean falsee = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
    }

    public static void deepStringMaps() {
        Gson gson = new Gson();
        String data="[{type:auto, period:10},{type:keyword,key:\"cpu\",period:60}]";
        String[] res=gson.fromJson(data,String[].class);
        System.out.println("deep string map result="+res);
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        deepStringMaps();
    }
}
