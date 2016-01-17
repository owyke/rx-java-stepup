package se.mejsla.stepup.rxjava.http;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import rx.apache.http.ObservableHttp;
import rx.apache.http.ObservableHttpResponse;
import rx.functions.Action1;

import java.io.IOException;
import java.util.Map;


public class OmdbClient {


    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();

//        ObservableHttp.createGet("http://www.omdbapi.com/?page=1&r=json&s=star", httpClient)
//                .toObservable()
//                .flatMap( observableHttpResponse -> observableHttpResponse.getContent())
//                .map(bytes -> new String(bytes))
//                .map(str ->  {
//                    try {
//                        Map res  = objectMapper.readValue(str, Map.class);
//                        System.out.println(res);
//                        return res;
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                })
//                .subscribe(m -> System.out.println(m));
//                .subscribe(str -> {
//                    try {
//                        Map res  = objectMapper.readValue(str, Map.class);
//                        System.out.println(res);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });


    }



    private static Action1<byte[]> getAction1() {
        return (q)-> System.out.println(new String(q));
    }






}



