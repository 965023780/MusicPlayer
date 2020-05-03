package com.example.musicplayer.utils.http;

import android.content.Context;
import android.util.Log;

import com.example.musicplayer.utils.json.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    private String baseAddress="http://47.99.165.194";
    private String address;
    private Context context;

    public void setHttpUtil(String address, Context context) {
        this.address = baseAddress+address;
        this.context = context;
    }

    public String sendGet(String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = address + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            Log.d("HttpError","发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Log.d("result", result);
        return result;
    }


    public String postData(Object data) {
        Json json = new Json();
        final StringBuffer out = new StringBuffer(json.toJson(data));
        final StringBuffer result = new StringBuffer();
        Log.d("append", out.toString());


        URL url;
        HttpURLConnection httpURLConnection = null;
        try {
            url = new URL(address);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(4000);
            httpURLConnection.setReadTimeout(8000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            PrintWriter pw = new PrintWriter(httpURLConnection.getOutputStream());
            pw.write(out.toString());
            pw.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            pw.close();
            reader.close();
            Log.d("result", result.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }

        return result.toString();
    }
}
