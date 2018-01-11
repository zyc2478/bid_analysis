package com.autobid.service;

import com.autobid.util.InitUtil;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PPDServiceTest {

    private static Logger logger = Logger.getLogger("PPDServiceTest.class");

    private static String token = "";

    static{
        try {
            InitUtil.init();
            token = InitUtil.getToken();
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

    @Test
    public void testBatchListingInfos() throws Exception {
        logger.info("");
        List<Integer> listIds = new ArrayList<>();
        listIds.add(92751890);
        listIds.add(92727980);
        listIds.add(92711160);
        JSONArray listingInfosArray = PPDService.batchListingInfos(listIds);
        for(Object listingInfos:listingInfosArray){
            logger.info(listingInfos.toString());
        }
    }
}
