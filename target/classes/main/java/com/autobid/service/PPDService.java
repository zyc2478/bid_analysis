package com.autobid.service;

import com.ppdai.open.core.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * @Author Richard Zeng
 * @ClassName: BidService
 * @Description: 与拍拍贷的API调用服务
 * @Date 2017�?10�?13�? 下午5:15:21
 */

@SuppressWarnings("deprecation")
public class PPDService {

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
/*
    LLoanInfoService.BatchListingInfos接口返回JSON
    {
        "LoanInfos": [
            {
                "FistBidTime": null,
                "LastBidTime": null,
                "LenderCount": 0,
                "AuditingTime": null,
                "RemainFunding": 1500,
                "DeadLineTimeOrRemindTimeStr": "2016/11/19",
                "CreditCode": "AA",
                "ListingId": 23886149,
                "Amount": 1500,
                "Months": 42,
                "CurrentRate": 12.0085,
                "BorrowName": "zhangsan",
                "Gender": 1,
                "EducationDegree": "专科",
                "GraduateSchool": "四川工业科技学院",
                "StudyStyle": "普�??",
                "Age": 22,
                "SuccessCount": 0,
                "WasteCount": 0,
                "CancelCount": 0,
                "FailedCount": 0,
                "NormalCount": 0,
                "OverdueLessCount": 0,
                "OverdueMoreCount": 0,
                "OwingPrincipal": 0,
                "OwingAmount": 0,
                "AmountToReceive": 0,
                "FirstSuccessBorrowTime": null,
                "LastSuccessBorrowTime": null,
                "RegisterTime": "2016-11-04T04:57:40.473",
                "CertificateValidate": 1,
                "NciicIdentityCheck": 0,
                "PhoneValidate": 1,
                "VideoValidate": 0,
                "CreditValidate": 0,
                "EducateValidate": 1,
                "HighestPrincipal":500.00,
                "HighestDebt":500.00,
                "TotalPrincipal":500.00
            },
            {
                "xx": "…�??"
            }
        ],
        "Result": 1,
        "ResultMessage": "",
        "ResultCode": null
    }
 */
    public static JSONArray batchListingInfos(List<Integer> listIds) throws Exception {
        String url = "https://openapi.ppdai.com/invest/LLoanInfoService/BatchListingInfos";
        Result result = OpenApiClient.send(url, new PropertyObject("ListingIds", listIds, ValueTypeEnum.Other));
        return JSONObject.fromObject(result.getContext()).getJSONArray("LoanInfos");
    }
}
