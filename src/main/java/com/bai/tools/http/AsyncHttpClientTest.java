package com.bai.tools.http;

import com.alibaba.fastjson.JSONObject;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.asynchttpclient.*;

import javax.print.attribute.standard.RequestingUserName;
import java.net.http.HttpHeaders;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.asynchttpclient.Dsl.*;
public class AsyncHttpClientTest {

    public static void test(){


        AsyncHttpClient asyncHttpClient = asyncHttpClient();
// bound
        Future<Response> whenResponse = asyncHttpClient.prepareGet("http://172.16.90.102:9488/v1/ver").execute();
        try{
            Object test = whenResponse.get(1, TimeUnit.SECONDS);
            System.out.println(JSONObject.toJSONString(test));
        }catch (Exception e){

        }

// unbound
//        Request request = get("http://www.example.com/").build();

    }

    /**
     * 當post 回應時間過長時,可以用此方式,回傳的future用get方法做回應時間過長處理
     * 傳遞模式是post,資料格式json
     * @param jsonReq request
     * @return {@link Future}
     */
    public static Future getFutureResponse(JSONObject jsonReq,String url){
        AsyncHttpClient asyncHttpClient = asyncHttpClient();
        Future<Response> whenResponse = asyncHttpClient.preparePost(url).setHeader("Accept", "application/json").setHeader("Content-Type","application/json").setBody(jsonReq.toJSONString()).execute();
        return whenResponse;
    }

    /**
     * 當post 回應時間過長時,可以用此方式,回傳的future用get方法做回應時間過長處理
     * 傳遞模式是post,資料格式json
     * @param jsonReq request
     * @return {@link Future}
     */
    public static JSONObject getResponseWithTimeOut(JSONObject jsonReq,String url,int timeOutSeconds){
        AsyncHttpClient asyncHttpClient = asyncHttpClient();
        Future<Response> whenResponse = asyncHttpClient.preparePost(url).setHeader("Accept", "application/json").setHeader("Content-Type","application/json").setBody(jsonReq.toJSONString()).execute();
        JSONObject result = null;
        try{
            Response resp = whenResponse.get(timeOutSeconds, TimeUnit.SECONDS);
            System.out.println(JSONObject.toJSONString(resp));
            String respBody =resp.getResponseBody();
            System.out.println("respBody:"+respBody);
            result = JSONObject.parseObject(respBody,JSONObject.class);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR MES :"+e.getMessage());
            return null;
        }
    }


    public static void test2(){


        AsyncHttpClient asyncHttpClient = asyncHttpClient();

// bound

        JSONObject jb = new JSONObject();
        jb.put("gid","gtifapi");



        Future<Response> whenResponse = asyncHttpClient.preparePost("http://172.16.90.102:9453/v1/bet/getGameVersion").setHeader("Accept", "application/json").setHeader("Content-Type","application/json").setBody(jb.toJSONString()).execute();
        try{
            Object test = whenResponse.get(10, TimeUnit.SECONDS);
            System.out.println(JSONObject.toJSONString(test));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

// unbound
//        Request request = get("http://www.example.com/").build();

    }
}
