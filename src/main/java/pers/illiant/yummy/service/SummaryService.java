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
}
