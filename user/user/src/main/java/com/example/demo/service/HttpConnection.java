package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;

@Service
public class HttpConnection {

	public JSONObject sendGet(String path, String globalId) throws ParseException {
		BufferedReader in = null;
		StringBuffer outResult = new StringBuffer();
		ServerHttpResponse serverHttpResponse = null;
		try {
			URL obj = new URL(path); // 호출할 url
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod("GET");

			if (globalId != null) {
				conn.setRequestProperty("globalId", globalId);
			}

			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) { // response를 차례대로 출력
				outResult.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		if (outResult.equals("")) {
			return null;
		}
		JSONParser jsonParser = new JSONParser();

		return (JSONObject) jsonParser.parse(outResult.toString());

	}

	public String sendREST(String sendUrl, String jsonValue, String globalId) throws IllegalStateException {
		String inputLine = null;
		StringBuffer outResult = new StringBuffer();
		try {
			URL url = new URL(sendUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("globalId", globalId.toString());
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setConnectTimeout(15000);
			conn.setReadTimeout(15000);
			
			OutputStream os = conn.getOutputStream();
			os.write(jsonValue.getBytes("UTF-8"));
			os.flush();
			// 리턴된 결과 읽기

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			while ((inputLine = in.readLine()) != null) {
				outResult.append(inputLine);
			}
			conn.disconnect();
		} catch (Exception e) {e.printStackTrace();}
		return outResult.toString();
	}

}
