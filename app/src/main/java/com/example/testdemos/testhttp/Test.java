package com.example.testdemos.testhttp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static final String URL = "http://jl.quyuansu.com/pull/advert/list?id=wervdsdk&apiToken=904619ac4ed32d98d7cf714c632f85a7&timestamp=1562746454901";

	public static Map<String, Object> invokeCapp(String urlStr, Map<String, Object> params) throws Exception {
		Map map = new HashMap();
		// post参数
		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0) {
				postData.append('&');
			}
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		System.out.println(postData.toString());
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		// 开始访问
		HttpURLConnection conn = (HttpURLConnection) (new URL(urlStr)).openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setConnectTimeout(2000);
		conn.setReadTimeout(5000);
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);

		System.out.println(postDataBytes);

		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;) {
			sb.append((char) c);
		}
		in.close();
		conn.disconnect();

		String responseStr = sb.toString();
		System.out.println("responseStr:" + responseStr);

		return null;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Test t = new Test();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("versionName", "2.1.00");
		param.put("versionCode", "26");
		param.put("ip", "192.168.1.3");
		param.put("mac", "dc:74:a8:45:8d:4e");
		param.put("imei", "353288085806804,353289085806802");

		t.invokeCapp(URL, param);
	}

}
