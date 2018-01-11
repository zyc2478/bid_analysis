package com.autobid.service;

import com.autobid.util.InitUtil;
import net.sf.json.JSONArray;
import org.junit.Test;

public class PPDServiceTest {
    //private static final int NONE = 0;

    private static String token = "";

    static{
        try {
            InitUtil.init();
            token = InitUtil.getToken();
            /*String openId = InitUtil.getOpenId();
            Jedis jedis = InitUtil.getJedis();
            ConfBean confBean = InitUtil.getConfBean();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBidListPeriod() throws Exception {
        System.out.println(token);
        JSONArray bidListArray = PPDService.bidList(token,"2017-10-01","2017-10-31",1,50);
        for(Object bidListObj:bidListArray){
            System.out.println(bidListObj);
        }
    }
}
