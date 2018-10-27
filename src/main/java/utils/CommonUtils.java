package utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CommonUtils {

    /**
     * 获取当前时间，精确到秒
     * @return
     */
    public static int getCurrentTimeSec() {
        return Integer.parseInt(System.currentTimeMillis() /1000+"");
    }

    /**
     * 生成32位MD5值
     */
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     *
     * @param str
     * @return
     */
    public static String getMD5String(String str) {
        try {
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4] + ""
                        + HEX_DIGITS[bytes[i] & 0xf]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getMD5String(String str, int len) {
        String string = getMD5String(str);
        if(!"".equals(string)) {
            return string.substring(0, len);
        }
        return "";
    }

    /**
     * 签名验证
     *
     * @param params
     * @param apiSecret
     * @return
     */
    public static String createSign(Map<String, Object> params, String apiSecret) {
        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>(params);

        StringBuffer sb = new StringBuffer();
        Set es = sortedMap.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        if ("MD5".equals(params.get("sign_type"))) {
            // MD5签名时，apiSecret放在最后
            sb.append("apiSecret=" + apiSecret);
        } else {
            return null;
        }

        String valueToDigest = sb.toString();
        String actualSign = "";
        if ("MD5".equals(params.get("sign_type"))) {
            // 截取前28位
            actualSign = getMD5String(valueToDigest, 28);
        }

        return actualSign;
    }

    /**===============================http 请求=====================================================*/
    /**
     * get请求
     *
     * @return
     */
    public static String get(String url) {

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            // 发送get请求
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Accept", "application/json");

            HttpResponse response = client.execute(httpGet);

            HttpEntity responseEntity = response.getEntity();
            String jsonString = EntityUtils.toString(responseEntity);
            return jsonString;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String post(String url, String params) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(params ,"utf-8"));
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String jsonString = EntityUtils.toString(responseEntity);
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String del(String url, String params) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpDeleteEntity httpDelete = new HttpDeleteEntity(url);
        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Content-Type", "application/json");
        httpDelete.setEntity(new StringEntity(params ,"utf-8"));

        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpDelete);
            HttpEntity responseEntity = response.getEntity();
            String jsonString = EntityUtils.toString(responseEntity);
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
