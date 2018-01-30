package com.autobid.util;

import com.ppdai.open.core.AuthInfo;
import com.ppdai.open.core.OpenApiClient;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TokenInit {

    private static Logger logger = Logger.getLogger("TokenInit.class");
    private static String code;
//    private static boolean initFlag = false;

    static {
        try {
            code = ConfUtil.getProperty("code");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*	private volatile static TokenInit instance;
        public static TokenInit getInstance(){
            if(instance == null){
                synchronized (TokenInit.class){
                    if(instance == null){
                        instance = new TokenInit();
                    }
                }
            }
            return instance;
        }
        */
    public static void initToken() throws Exception {
        InitUtil.init();
        System.out.println("code is:" + code);
        //authInfo = OpenApiClient.authorize("9ca3fb6357b04c5385fd51d1e6db9922");
        AuthInfo authInfo = OpenApiClient.authorize(code);
        String token = authInfo.getAccessToken();
        String refreshToken = authInfo.getRefreshToken();

        logger.info("token is: " + token);
        logger.info("refresh token is: " + refreshToken);
        ConfUtil.setProperty("token_init", token);
        ConfUtil.setProperty("refresh_token_init", refreshToken);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String nowDate = sdf.format(new Date());
        ConfUtil.setProperty("init_date", nowDate);
        TokenUtil.setTokenInit(token);
        TokenUtil.setRefreshTokenInit(refreshToken);

    }
}
