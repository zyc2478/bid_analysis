package com.autobid.service.util;

import com.autobid.util.InitUtil;
import com.ppdai.open.core.OpenApiClient;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import org.apache.log4j.Logger;
//import org.junit.Test;
@SuppressWarnings("deprecation")
public class TokenUtil {

    private final static String openId;
    private static String token;
    private static int expireDays;
    private static String initDate;
    private static Jedis jedis;

    static {
        openId = "7344c77f9a7f4f249bd9df04115171e6";
        try {
            expireDays = Integer.parseInt(ConfUtil.getProperty("expire_days"));
//            int refreshTokenExpired = Integer.parseInt(ConfUtil.getProperty("refresh_token_expired"));
            initDate = ConfUtil.getProperty("init_date");
            String redisHost = ConfUtil.getProperty("redis_host");
            int redisPort = Integer.parseInt(ConfUtil.getProperty("redis_port"));
            jedis = new Jedis(redisHost, redisPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //private static Logger logger = Logger.getLogger(TokenUtil.class);

    public static void genNewToken() throws Exception {

        InitUtil.init();
        //logger.info(determineRefreshDate());

        String tokenFromConf = ConfUtil.getProperty("refresh_token");

        String refreshToken;
        if (tokenFromConf.equals("")) {
            refreshToken = jedis.get("refreshToken");
        } else {
            refreshToken = tokenFromConf;
        }
        OpenApiClient.refreshToken(openId, refreshToken);
    }

    public static boolean determineTokenInitExists() throws IOException {
        return ConfUtil.getProperty("token_init").equals("");
    }

    public static boolean determineRefreshDate() throws ParseException {

        int diffDate = getDiffDate();
        int leftDays = expireDays - diffDate % expireDays;
        return leftDays <= 1;
    }

    private static int getDiffDate() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date beginDate = df.parse(initDate);
        Date today = new Date();
        //Date today = df.parse("2017-10-23 12:10");
        //logger.info(today);
        long diffTime = today.getTime() - beginDate.getTime();
        long diffDate = (int) (diffTime / (1000 * 60 * 60 * 24));
        return (int) diffDate;
    }

/*    public static boolean determineRefreshTokenExpired() throws ParseException {

        int diffDate = getDiffDate();
        int leftDays = refreshTokenExpired - diffDate % refreshTokenExpired;
        return leftDays <= 1;
    }*/

    public static String getToken() {
        token = jedis.get("token");
        return token;
    }

    public static void setToken(String newToken) {
        TokenUtil.token = newToken;
        jedis.setex("token", 691200, token);
    }

/*    public static String getRefreshToken() {
        refreshToken = jedis.get("refreshToken");
        return refreshToken;
    }*/

/*    public static void setRefreshToken(String newRefreshToken) {
        TokenUtil.refreshToken = newRefreshToken;
        jedis.setex("refreshToken", 8640000, refreshToken);
    }*/

    public static void setRefreshTokenInit(String refreshTokenInit) {
        //String initRefreshToken = ConfUtil.getProperty("refresh_token_init");
        jedis.setex("refreshToken", 8640000, refreshTokenInit); //100天后过期
    }

    public static void setTokenInit(String tokenInit) {
        //String initToken =  ConfUtil.getProperty("token_init");
        jedis.setex("token", 691200, tokenInit); //8天后过期
    }
}