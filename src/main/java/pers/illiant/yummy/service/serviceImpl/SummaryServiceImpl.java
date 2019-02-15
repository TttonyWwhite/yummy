package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.RestaurantMapper;
import pers.illiant.yummy.entity.Restaurant;
import pers.illiant.yummy.model.RestaurantPositionVO;
import pers.illiant.yummy.service.SummaryService;
import pers.illiant.yummy.util.Result;
import pers.illiant.yummy.util.ResultUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("summaryService")
public class SummaryServiceImpl implements SummaryService {

    String[] cityNames = {"徐州市", "连云港市", "宿迁市", "淮安市", "盐城市", "泰州市", "扬州市", "南京市", "镇江市", "南通市",
                            "常州市", "无锡市", "苏州市"};

    @Autowired
    RestaurantMapper restaurantMapper;

    @Override
    public Result restaurantPositionSummary() {
        List<Restaurant> restaurantList = restaurantMapper.selectAll();
        List<RestaurantPositionVO> result = new ArrayList<>();

        for (String city : cityNames) {
            RestaurantPositionVO vo = new RestaurantPositionVO();
            vo.setCityName(city);
            int number = 0;

            for (Restaurant restaurant : restaurantList) {
                if (restaurant.getAddress().contains(city))
                    number++;
            }

            vo.setRestaurantNumber(number);
            result.add(vo);
        }



        return ResultUtils.success(result);
    }
}
