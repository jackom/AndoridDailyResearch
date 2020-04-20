package com.example.testdemos.testhttp;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Main {

//    public static final String URL="http://192.168.1.7/php/android.php?id=wervdsdk&apiToken=e7abfe078b9d4daef05f0c32d896b4d1&timestamp=1562744564383";

    public static final String URL="http://jl.quyuansu.com/pull/advert/list?id=wervdsdk&apiToken=904619ac4ed32d98d7cf714c632f85a7&timestamp=1562746454901";
    public static final String PARAMS="versionName=2.1.00&versionCode=26&ip=192.168.1.3&mac=dc:74:a8:45:8d:4e&imei=353288085806804,353289085806802";
//    public static final String PARAMS="versionName=2.1.00&versionCode=26&ip=192.168.1.3&mac=dc:74:a8:45:8d:4e&imei=353288085806804,353289085806802&imsi=460030236524898&model=SM-C5000&manufacture=samsung&api_level=26&OSV=8.0.0&androidId=a8284f0b7ca294a5&serialno=9caf90d8&sw=1080&sh=1920&dip=2.625&net=1&info_la=34860&info_ci=28883";



    public static void main(String[] args) {
        postApi();



//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Map<String,String> map=getMap(PARAMS);
//                        String result=sendPost(URL,PARAMS,map);
//                        System.out.println(result+"");
//                    } catch (IOException e) {
//                        System.out.println(e.toString()+"");
//                        e.printStackTrace();
//                    }
//                }
//            }).start();

    }

    public static void postApi(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    java.net.URL url = new URL(URL);
                    URLConnection connection = url.openConnection();
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestProperty("accept","*/*");
                    connection.setRequestProperty("Connection","Keep-Alive");// 维持长连接
                    connection.setRequestProperty("content-type","application/x-www-form-urlencoded;charset=utf-8");
//                    connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//                    connection.setRequestProperty("Content-Length", String.valueOf(PARAMS.getBytes("utf-8").length));



                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                    writer.write(PARAMS.toCharArray());
                    writer.flush();
                    writer.close();

//                    connection.getOutputStream().write(PARAMS.getBytes("utf-8"));



                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null) {
                        builder.append(line);
                    }
                    System.out.println(URL);
                    System.out.println(PARAMS);
                    System.out.println(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("数据:"+e.toString());
                }
            }
        }).start();
    }



//    Map<String,String> map=getMap(PARAMS);
//                    for(Map.Entry<String,String> entry:map.entrySet()){
//                        System.out.println(entry.getKey()+":::"+entry.getValue());
//                        connection.addRequestProperty(entry.getKey(),entry.getValue());
//                    }
//                    connection.connect();

    private static Map<String,String> getMap(String params){
        String[] str=params.split("&");
        Map<String,String> map=new HashMap<>();
        for(String item:str){
            String[] aa=item.split("=");
            map.put(aa[0],aa[1]);
        }
        return map;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     */
    public static String sendPost(String url, String param) throws UnsupportedEncodingException, IOException {
        return sendPost(url, param, null);
    }

    public static String sendPost(String url, String param, Map<String, String> header) throws UnsupportedEncodingException, IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
        //设置超时时间
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(15000);
        // 设置通用的请求属性
        if (header!=null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                System.out.println(entry.getKey()+"::"+entry.getValue());
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        out = new PrintWriter(conn.getOutputStream());
        // 发送请求参数
        out.print(param);
        // flush输出流的缓冲
        out.flush();
        // 定义BufferedReader输入流来读取URL的响应
        in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf8"));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        if(out!=null){
            out.close();
        }
        if(in!=null){
            in.close();
        }
        return result;
    }


}