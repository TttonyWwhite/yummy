package pers.illiant.yummy.service;

import pers.illiant.yummy.util.Result;

public interface SummaryService {

    //统计餐厅地区分布
    Result restaurantPositionSummary();

    //统计餐厅种类分布
    Result restaurantClassSummary();
}
