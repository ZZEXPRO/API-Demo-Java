import com.alibaba.fastjson.JSONObject;
import sun.security.provider.MD5;
import utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * API demo Java示例
 * @author zzex
 */
public class Demo {

    //交易对
    private static final String SYMBOL = "ETHZC";
    //货币名称
    private static final String CURRENCY = "ETH";
    //API key
    private static final String API_KEY = "5b092b11-001c-4e50-9d8e-00f9db752241";
    //加密方式
    private static final String SIGN_TYPE = "MD5";
    //api secret
    private static final String API_SECRET = "3A703C58F86BBD5807326AF6B243529E";
    //根URL
    private static final String URL = "http://www.zzex.pro";
    //分页请求，页码1
    private static final int PAGE = 1;
    //分页请求，每页20条
    private static final int PAGE_SIZE = 20;
    //当前时间戳秒
    private static final int TIMESTAMP = CommonUtils.getCurrentTimeSec();

    public static void main(String[] args) {
        System.out.println("===============请求结果==============");
        // 测试获取服务器时间
        getServiceTime();
        // 获得单个订单的交易明细
//        getTrade();
        // 获得订单列表
//        getOrders();
        // 获得已成交的记录
//        getSuccessedTrades();
        //获得单个币的资产
//        getCurrency();
        // 获得资产列表
//        getCurrencyList();
        // 获得所有行情汇总
//        getAllTicker();
//         获得市场深度
//        getTickerDepth();
        // 获取k线数据
//        getKlines();
        // 获得系统支持的交易对
//        getSymbols();
        // 创建订单
//        createOrder();
        // 取消订单
//        cancelOrder();
    }

    /**
     * 测试，获取服务器时间
     */
    public static void getServiceTime() {
        String reqUrl = URL + "/m/timestamp";
        String response = CommonUtils.get(reqUrl);
        System.out.println(response);
    }

    /**
     * 获得单个订单的交易明细
     */
    public static void getTrade() {
        String reqUrl = URL + "/o/api/order/trades";

        try {
            int timestamp = CommonUtils.getCurrentTimeSec();
            int id = 142396;

            Map<String, Object> map = new HashMap<String,Object>();
            map.put("api_key", API_KEY);
            map.put("sign_type", SIGN_TYPE);
            map.put("timestamp", timestamp);
            String sign = CommonUtils.createSign(map, API_SECRET);
            StringBuffer sBuffer = new StringBuffer(reqUrl);
            sBuffer.append("/").append(SYMBOL).append("/").append(id).append("/").append(API_KEY).append("/")
                    .append(timestamp).append("/").append(SIGN_TYPE).append("/").append(sign);
            System.out.println("请求URL：" + sBuffer.toString());
            String response = CommonUtils.get(sBuffer.toString());
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得订单列表
     * symbol:交易对名称，如BTCBC
     * page：页数，从1开始，
     * pageSize：每页记录数，最大20，默认按时间倒叙排列
     * status 0表示未完成（挂单中），1表示已完成（含已取消），2表示所有
     */
    public static void getOrders() {
        String reqUrl = URL + "/o/api/orders";

        try {
            // 0表示未完成（挂单中），1表示已完成（含已取消），2表示所有
            int status = 1;
            int timestamp = CommonUtils.getCurrentTimeSec();

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("api_key", API_KEY);
            map.put("sign_type", SIGN_TYPE);
            map.put("timestamp", timestamp);
            String sign = CommonUtils.createSign(map, API_SECRET);

            StringBuffer sBuffer = new StringBuffer(reqUrl);
            sBuffer.append("/").append(SYMBOL).append("/").append(PAGE).append("/").append(PAGE_SIZE).append("/")
                    .append(status).append("/").append(API_KEY).append("/").append(timestamp).append("/")
                    .append(SIGN_TYPE).append("/").append(sign);
            String response = CommonUtils.get(sBuffer.toString());
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获得已成交记录
     *
     * symbol:交易对名称，如BTCBC page：页数，从1开始，
     * pageSize：每页记录数，最大20，默认按时间倒叙排列
     */
    public static void getSuccessedTrades() {
        String reqUrl = URL + "/o/api/trades";
        try {

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("api_key", API_KEY);
            map.put("sign_type", SIGN_TYPE);
            map.put("timestamp", TIMESTAMP);
            String sign = CommonUtils.createSign(map, API_SECRET);

            StringBuffer sBuffer = new StringBuffer(reqUrl);
            sBuffer.append("/").append(SYMBOL).append("/").append(PAGE).append("/").append(PAGE_SIZE).append("/")
                    .append(API_KEY).append("/").append(TIMESTAMP).append("/").append(SIGN_TYPE).append("/").append(sign);
            String response = CommonUtils.get(sBuffer.toString());
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获得单个币资产
     *
     * currency (string): 币类型，如BTC、LTC、ETH
     *
     */
    public static void getCurrency() {
        String reqUrl = URL + "/a/api/account";

        try {
            int timestamp = CommonUtils.getCurrentTimeSec();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("api_key", API_KEY);
            map.put("sign_type", SIGN_TYPE);
            map.put("timestamp", timestamp);
            String sign = CommonUtils.createSign(map, API_SECRET);


            StringBuffer sBuffer = new StringBuffer(reqUrl);
            sBuffer.append("/").append(CURRENCY).append("/").append(API_KEY).append("/")
                    .append(timestamp).append("/").append(SIGN_TYPE).append("/").append(sign);
            String response = CommonUtils.get(sBuffer.toString());
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * 获得资产列表
     *
     */
    public static void getCurrencyList() {
        String reqUrl = URL + "/a/api/accounts";

        try {
            int timestamp = CommonUtils.getCurrentTimeSec();
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("api_key", API_KEY);
            map.put("sign_type", SIGN_TYPE);
            map.put("timestamp", timestamp);
            String sign = CommonUtils.createSign(map, API_SECRET);
            StringBuffer sBuffer = new StringBuffer(reqUrl);
            sBuffer.append("/").append(API_KEY).append("/").append(timestamp).append("/")
                    .append(SIGN_TYPE).append("/").append(sign);
            String response = CommonUtils.get(sBuffer.toString());
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * 获得所有行情汇总
     *
     */
    public static void getAllTicker() {
        String reqUrl = URL + "/m/allticker/"+CommonUtils.getCurrentTimeSec();

        try {
            String response = CommonUtils.get(reqUrl);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * 获得市场深度
     *
     */
    public static void getTickerDepth() {
        String reqUrl = URL + "/m/depth/"+SYMBOL;

        try {
            String response = CommonUtils.get(reqUrl);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 获得K线数据
     * symbol:交易对名称，如ETHZC
     * type: K线类型，取值1min, 5min, 15min, 30min, 60min, 1day, 1mon, 1week, 1year
     * size: 数据条数，取值1、10、100、1000
     */
    public static void getKlines() {
        String reqUrl = URL + "/m/kline/"+SYMBOL+"/1min/1";

        try {
            String response = CommonUtils.get(reqUrl);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 获得系统支持的交易对
     */
    public static void getSymbols() {
        String reqUrl = URL + "/m/symbol";

        try {
            String response = CommonUtils.get(reqUrl);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 创建订单
     */
    public static void createOrder() {
        String reqUrl = URL + "/o/api/order";

        String orderNo = String.valueOf(System.currentTimeMillis()) + "-" + Math.random();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("api_key", API_KEY);
            map.put("o_no", orderNo);
            map.put("o_price_type", "limit");
            map.put("o_type", Math.random() > 0.5 ? "sell" : "buy");
            map.put("price", 1);
            map.put("volume", 0.1);
            map.put("sign_type", SIGN_TYPE);
            map.put("symbol", SYMBOL);
            map.put("timestamp", CommonUtils.getCurrentTimeSec());
            map.put("sign", CommonUtils.createSign(map, API_SECRET));
            String params = JSONObject.toJSONString(map);
            System.out.println(map.toString());
            String response = CommonUtils.post(reqUrl, params);
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消订单
     *
     * id (integer): 订单ID
     * o_no (string): 订单号
     *
     */

    public static void cancelOrder() {
        String reqUrl = URL + "/o/api/order";

        try {
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("api_key", API_KEY);
            map.put("id", 142469);
            map.put("o_no", "1540618640732-0.1735936827645982");
            map.put("symbol", SYMBOL);
            map.put("timestamp", CommonUtils.getCurrentTimeSec());
            map.put("sign_type", SIGN_TYPE);
            map.put("sign", CommonUtils.createSign(map, API_SECRET));

            String params = JSONObject.toJSONString(map);
            String response = CommonUtils.del(reqUrl, params);
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
