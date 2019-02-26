package pers.illiant.yummy.service;

import pers.illiant.yummy.util.Result;

public interface SummaryService {

    //统计餐厅地区分布
    Result restaurantPositionSummary();

    //统计餐厅种类分布
    Result restaurantClassSummary();

    //统计会员地区分布
    Result memberPositionSummary();

    //统计会员等级
    Result memberLevelSummary();

    //餐厅营业额统计(最近一周)
    Result businessSummary(String restaurantId);

    //餐厅新增用户统计（第一次在本餐厅点餐的用户）
    Result newMemberSummary(String restaurantId);

    //餐厅订单数统计
    Result orderCountSummary(String restaurantId);

    //餐厅退订数统计
    Result refundSummary(String restaurantId);

    //平台营收统计(最近一周)
    Result financialSummary();

    //获取餐厅修改信息申请列表
    Result getRequests();

    Result getRequest(int requestId);

    Result rejectRequest(int requestId);

    Result approveRequest(int requestId);
}


