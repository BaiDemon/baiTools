package com.bai.tools;

import com.alibaba.fastjson.JSONObject;
import com.bai.tools.http.AsyncHttpClientTest;

public class Main {
    public static void main(String args[]){
        // asynchttp test
        System.out.println("test");
        AsyncHttpClientTest.test2();
        JSONObject jb = new JSONObject();
        String url = "http://172.16.90.102:9453/v1/bet/getGameVersion";
        jb.put("gid","gtiapi");

        System.out.println(AsyncHttpClientTest.getResponseWithTimeOut(jb,url,10).toJSONString());
    }
}
