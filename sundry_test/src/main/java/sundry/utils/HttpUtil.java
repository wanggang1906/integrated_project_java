package sundry.utils;

import java.io.IOException;
/*import com.qunar.payment.gateway.front.channel.mpgs.po.HttpReqEntity;*/
import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class HttpUtil {
    // 基于apach的http工具类

    private HttpUtil() {
    }

    private static CredentialsProvider credentialsProvider;
/*    private static final SSLConnectionSocketFactory SOCKET_FACTORY = getSocketFactory();*/
    private static final NoopHostnameVerifier NO_OP = new NoopHostnameVerifier();

/*    public static String execute(HttpReqEntity httpReqEntity) throws IOException {
        CloseableHttpClient httpclient = getClosableHttpClient(httpReqEntity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(getHttpUriRequest(httpReqEntity));
            return EntityUtils.toString(response.getEntity());
        } finally {
            try {
                if (null != response) response.close();
                if (null != httpclient) httpclient.close();
            } catch (IOException ignored) {
            }
        }
    }*/

    private static TrustManager getTrustManagers() {
        return new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        };
    }

/*    private static SSLConnectionSocketFactory getSocketFactory() {
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance(MpgsConstant.SECURITYPROTOCOL_VERSION_TLS_1_2);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        try {
            sslContext.init(null, new TrustManager[]{getTrustManagers()}, new SecureRandom());
        } catch (KeyManagementException e) {
            return null;
        }
        return new SSLConnectionSocketFactory(sslContext);
    }

    private static CloseableHttpClient getClosableHttpClient(HttpReqEntity entity) {
        return HttpClients.custom()
                .setSSLSocketFactory(SOCKET_FACTORY)
                .setSSLHostnameVerifier(NO_OP)
                .setDefaultCredentialsProvider(getCredentialsProvider(entity.getCredUserName(), entity.getCredPasswd()))
                .build();
    }

    private static HttpPut getHttpPut(String requestUrl, String requestMessage, RequestConfig config) {
        HttpPut httpPut = new HttpPut(requestUrl);
        httpPut.setConfig(config);
        httpPut.addHeader(HttpHeaders.CONTENT_TYPE, MpgsConstant.CONTENTTYPE_JSON);
        httpPut.setEntity(new StringEntity(requestMessage, MpgsConstant.CHARSET_UTF8));
        return httpPut;
    }*/

    private static HttpGet getHttpGet(String requestUrl, RequestConfig config) {
        HttpGet httpGet = new HttpGet(requestUrl);
        httpGet.setConfig(config);
        return httpGet;
    }

/*
    private static HttpUriRequest getHttpUriRequest(HttpReqEntity entity) {
        return entity.getHttpMethod() == HttpMethod.GET ? getHttpGet(entity.getRequestUrl(), entity.getRequestConfig())
                : getHttpPut(entity.getRequestUrl(), entity.getRequestMessage(), entity.getRequestConfig());
    }
*/

    private static CredentialsProvider getCredentialsProvider(String userName, String passwd) {
        if (null == credentialsProvider) {
            synchronized (HttpUtil.class) {
                if (null == credentialsProvider) {
                    CredentialsProvider credsProvider = new BasicCredentialsProvider();
                    credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, passwd));
                    credentialsProvider = credsProvider;
                }
            }
        }
        return credentialsProvider;
    }
}
