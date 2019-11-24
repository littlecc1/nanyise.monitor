package top.wello.monitor.service;

import com.google.gson.Gson;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.wello.monitor.util.Utility;

import java.io.IOException;
import java.util.Map;

@Component
public class NetworkService {

    static Logger logger = LoggerFactory.getLogger(NetworkService.class);

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Gson gson = new Gson();

    public static String get(String url) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("get failed", e);
        }
        return null;
    }

    public static String getFtp(String url) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                return String.valueOf(response.code());
            }
        } catch (IOException e) {
            logger.error("get failed", e);
        }
        return null;
    }

//    public static <T> T get(String url, Map<String, String> params) {
//
//    }

    public static <T> T get(String url, Map<String, String> params, Class<T> t) {
        String fullUrl = appendParams(url, params);
        logger.info("Request Url: " + fullUrl);
        if (!Utility.isEmpty(fullUrl)) {
            Request request = new Request.Builder()
                    .get()
                    .url(fullUrl)
                    .build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                if (response.isSuccessful()) {
                    String res = response.body().string();
                    if (String.class.equals(t)) {
                        return (T) res;
                    } else {
                        return gson.fromJson(res, t);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    public static String post(String url, Map<String, String> param) {
        if (!Utility.isEmpty(url) && param != null && param.size() > 0) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry: param.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            return post(url, builder.build());
        }
        return null;
    }

    public static String post(String url, FormBody param) {
        if (!Utility.isEmpty(url) && param != null) {
            Request request = new Request.Builder()
                    .url(url)
                    .post(param)
                    .build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                logger.error("post failed", e);
            }
        }
        return null;
    }

    private static String appendParams(String url, Map<String, String> params) {
        if (params == null || params.size() < 1) {
            return url;
        }
        StringBuilder builder = new StringBuilder(url).append('?');
        for (Map.Entry<String, String> entry: params.entrySet()) {
            builder.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        return builder.toString().substring(0, builder.length() - 1);
    }

}
