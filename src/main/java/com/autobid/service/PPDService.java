package com.autobid.service;

import com.ppdai.open.core.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * @Author Richard Zeng
 * @ClassName: BidService
 * @Description: 与拍拍贷的API调用服务
 * @Date 2017年10月13日 下午5:15:21
 */

@SuppressWarnings("deprecation")
public class PPDService {

//    private static Logger logger = Logger.getLogger(PPDService.class);
/*
     BidService.BidList返回JSON
    {
        "Result": 0,
        "ResultMessage": "null",
        "TotalPages": "1",
        "TotalRecord": "20",
        "BidList": {
            "Title": "测试使用",
            "ListingId": "223423",
            "Months": "10",
            "Rate": "10",
            "Amount": "10000",
            "BidAmount": "80"
        }
    }
*/
    public static JSONArray bidList(String token, String startTime, String endTime, int pageIndex, int pageSize) throws Exception {
        String url = "https://openapi.ppdai.com/invest/BidService/BidList";
        Result result = OpenApiClient.send(url, token,
                new PropertyObject("StartTime",startTime, ValueTypeEnum.DateTime),
                new PropertyObject("EndTime",endTime, ValueTypeEnum.DateTime),
                new PropertyObject("PageIndex",pageIndex, ValueTypeEnum.Int32),
                new PropertyObject("PageSize",pageSize, ValueTypeEnum.Int32));
        if(pageIndex==0){
            result = OpenApiClient.send(url, token,
                    new PropertyObject("StartTime",startTime, ValueTypeEnum.DateTime),
                    new PropertyObject("EndTime",endTime, ValueTypeEnum.DateTime),
                    new PropertyObject("PageSize",pageSize, ValueTypeEnum.Int32));
        }
        JSONObject resultJSON = JSONObject.fromObject(result.getContext());
        JSONArray bidListArray = resultJSON.getJSONArray("BidList");
        return bidListArray;
    }
}
