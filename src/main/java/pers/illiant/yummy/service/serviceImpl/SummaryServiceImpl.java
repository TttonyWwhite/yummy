package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.AddressMapper;
import pers.illiant.yummy.dao.MemberMapper;
import pers.illiant.yummy.dao.RestaurantMapper;
import pers.illiant.yummy.entity.Address;
import pers.illiant.yummy.entity.Member;
import pers.illiant.yummy.entity.Restaurant;
import pers.illiant.yummy.model.MemberSummaryVO;
import pers.illiant.yummy.model.RestaurantSummaryVO;
import pers.illiant.yummy.service.SummaryService;
import pers.illiant.yummy.util.Result;
import pers.illiant.yummy.util.ResultUtils;

import java.util.ArrayList;
import java.util.List;

@Service("summaryService")
public class SummaryServiceImpl implements SummaryService {

    String[] cityNames = {"徐州市", "连云港市", "宿迁市", "淮安市", "盐城市", "泰州市", "扬州市", "南京市", "镇江市", "南通市",
                            "常州市", "无锡市", "苏州市"};

    String[] restaurantClass = {"美食", "快餐便当", "特色菜系", "异国料理", "小吃夜宵", "甜品饮品"};

    String[] levels = {"Level1", "Level2", "Level3", "Level4"};

    @Autowired
    RestaurantMapper restaurantMapper;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    AddressMapper addressMapper;

    @Override
    public Result restaurantPositionSummary() {
        List<Restaurant> restaurantList = restaurantMapper.selectAll();
        List<RestaurantSummaryVO> result = new ArrayList<>();

        for (String city : cityNames) {
            RestaurantSummaryVO vo = new RestaurantSummaryVO();
            vo.setSummaryItem(city);
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

    @Override
    public Result restaurantClassSummary() {
        List<Restaurant> restaurants = restaurantMapper.selectAll();
        List<RestaurantSummaryVO> result = new ArrayList<>();

        for (String item : restaurantClass) {
            RestaurantSummaryVO vo = new RestaurantSummaryVO();
            vo.setSummaryItem(item);

            int number = 0;
            for (Restaurant restaurant : restaurants) {
                if (restaurant.getType().contains(item))
                    number++;
            }
            vo.setRestaurantNumber(number);

            result.add(vo);
        }

        return ResultUtils.success(result);
    }

    @Override
    public Result memberPositionSummary() {
        List<Address> addresses = addressMapper.selectAll();
        List<MemberSummaryVO> result = new ArrayList<>();

        for (String city : cityNames) {
            MemberSummaryVO vo = new MemberSummaryVO();
            vo.setSummaryItem(city);
            int number = 0;
            for (Address address : addresses) {
                if (address.getAddress().contains(city))
                    number++;
            }

            vo.setMemberNumber(number);
            result.add(vo);
        }

        return ResultUtils.success(result);
    }

    @Override
    public Result memberLevelSummary() {
        List<Member> members = memberMapper.selectAll();
        List<MemberSummaryVO> result = new ArrayList<>();

        int level_num = 1;
        for (String level : levels) {
            MemberSummaryVO vo = new MemberSummaryVO();
            vo.setSummaryItem(level);
            int number = 0;
            for (Member member : members) {
                if (member.getLevel() == level_num)
                    number++;
            }
            level_num++;

            vo.setMemberNumber(number);
            result.add(vo);
        }
        return ResultUtils.success(result);
    }


}
