package util;

import com.ning.http.client.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * http请求工具
 * <p>
 * 异步
 *
 * @author shuzhuang.su
 * @date 2020-03-05 20:06
 */
public class HTTPUtil {

    private AsyncHttpClient client;

    private static class HttpClientHolder {
        private static HTTPUtil httpClient = new HTTPUtil();
    }

    private HTTPUtil() {
        AsyncHttpClientConfig.Builder configBuilder = new AsyncHttpClientConfig.Builder();
        configBuilder.setMaxConnections(100);
        /*使用默认值：
        configBuilder.setConnectTimeout(3000);
        configBuilder.setReadTimeout(5000);*/
        configBuilder.setRequestTimeout(60000);
        this.client = new AsyncHttpClient(configBuilder.build());
    }

    public static HTTPUtil getAsyncHttpClient() {
        return HttpClientHolder.httpClient;
    }

    public Future<Response> get(String url) {
        return this.client.prepareGet(url).execute();
    }

    public void get(String url, AsyncHandler resHandler) {
        this.client.prepareGet(url).execute(resHandler);
    }

    public Future<Response> post(String url) {
        return this.client.preparePost(url).execute();
    }

    public void post(String url, AsyncHandler resHandler) {
        this.client.preparePost(url).execute(resHandler);
    }

    private Request buildRequest(String url, Map<String, String> paramsMap) {
        RequestBuilder requestBuilder = new RequestBuilder();
        if (paramsMap != null && paramsMap.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (entry.getKey() != null) {
                    requestBuilder.addFormParam(entry.getKey(), entry.getValue());
                }
            }
        }
        // 添加RequestHeader，key
        requestBuilder.addHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        requestBuilder.setMethod("POST");
        requestBuilder.setUrl(url);

        return requestBuilder.build();
    }

    public Future<Response> post(String url, Map<String, String> paramsMap) {
        Request req = this.buildRequest(url, paramsMap);
        return this.client.executeRequest(req);
    }

    public void post(String url, Map<String, String> paramsMap, AsyncHandler resHandler) {
        Request req = this.buildRequest(url, paramsMap);
        this.client.executeRequest(req, resHandler);
    }

    public void close() {
        this.client.close();
    }


}
