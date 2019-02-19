package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pers.illiant.yummy.dao.*;
import pers.illiant.yummy.entity.*;
import pers.illiant.yummy.model.*;
import pers.illiant.yummy.service.SummaryService;
import pers.illiant.yummy.util.Result;
import pers.illiant.yummy.util.ResultUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.LocalDate;
import org.joda.time.DateTime;

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

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    UpdateRequestMapper updateRequestMapper;

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

    @Override
    public Result businessSummary(String restaurantId) {
        Date current_date = new Date();
        List<BusinessSummaryVO> list = new ArrayList<>();
        List<OrderInfo> infos = orderInfoMapper.selectByRestaurantId(restaurantId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current_date);

        for (int i = 0;i < 7;i++) { //一周之内
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date date = calendar.getTime();
            double total = 0;
            BusinessSummaryVO vo = new BusinessSummaryVO();
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            vo.setDate(month + "/" + day);

            for (OrderInfo item : infos) {
                Date temp = item.getOrderTime();
                LocalDate ld1 = new LocalDate(new DateTime(date));
                LocalDate ld2 = new LocalDate(new DateTime(temp));
                if (ld1.equals(ld2)) {
                    total += item.getPrice(); //营收额不算运费
                }
            }

            vo.setTotal(total);
            list.add(vo);
        }
        return ResultUtils.success(list);
    }

    @Override
    public Result newMemberSummary(String restaurantId) {
        List<String_Int_Pair> list = new ArrayList<>();
        Date currentDate = new Date();
        List<OrderInfo> infos = orderInfoMapper.selectByRestaurantId(restaurantId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, -8);
        for (int i = 0;i < 7;i++) {
            String_Int_Pair vo = new String_Int_Pair();
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
            vo.setStr(month + "/" + day);

            vo.setCount(getNewMemberNumbers(calendar.getTime(), infos));
            list.add(vo);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return ResultUtils.success(list);
    }

    @Override
    public Result orderCountSummary(String restaurantId) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        List<String_Int_Pair> list = new ArrayList<>();
        List<OrderInfo> infos = orderInfoMapper.selectByRestaurantId(restaurantId);


        for (int i = 0;i < 7;i++) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date date = calendar.getTime();
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String_Int_Pair vo = new String_Int_Pair();
            vo.setStr(month + "/" + day);

            int orderCount = 0;

            for (OrderInfo item : infos) {
                Date temp = item.getOrderTime();
                LocalDate ld1 = new LocalDate(new DateTime(date));
                LocalDate ld2 = new LocalDate(new DateTime(temp));
                if (ld1.equals(ld2)) {
                    orderCount++;
                }
            }
            vo.setCount(orderCount);
            list.add(vo);

        }

        return ResultUtils.success(list);

    }

    @Override
    public Result refundSummary(String restaurantId) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        List<String_Int_Pair> list = new ArrayList<>();
        List<OrderInfo> infos = orderInfoMapper.selectByRestaurantId(restaurantId);

        for (int i = 0;i < 7;i++) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date date = calendar.getTime();
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String_Int_Pair vo = new String_Int_Pair();
            vo.setStr(month + "/" + day);

            int refundCount = 0;
            for (OrderInfo item : infos) {
                Date temp = item.getOrderTime();
                LocalDate ld1 = new LocalDate(new DateTime(date));
                LocalDate ld2 = new LocalDate(new DateTime(temp));
                if (ld1.equals(ld2) && item.getState().equals("Refund")) {
                    refundCount++;
                }
            }

            vo.setCount(refundCount);
            list.add(vo);
        }

        return ResultUtils.success(list);
    }

    @Override
    public Result getRequests() {
        List<UpdateRequest> list = updateRequestMapper.selectAll();
        List<RequestVO> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (UpdateRequest item : list ) {
            RequestVO vo = new RequestVO();
            vo.setRequestId(item.getRequestId());
            vo.setAddress(item.getAddress());
            vo.setImgUrl(item.getImgurl());
            vo.setPhoneNumber(item.getPhonenumber());
            vo.setRequestTime(sdf.format(item.getRequestTime()));
            vo.setRestaurantId(item.getRestaurantId());
            vo.setShopName(item.getShopname());
            vo.setType(item.getType());

            switch (item.getState()) {
                case "Wait":
                    vo.setState("待处理");
                    break;
                case "Approved":
                    vo.setState("已批准");
                    break;
                case "Rejected":
                    vo.setState("已驳回");
                    break;
            }

            result.add(vo);
        }

        return ResultUtils.success(result);
    }

    @Override
    public Result getRequest(int requestId) {
        UpdateRequest request = updateRequestMapper.selectByPrimaryKey(requestId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String state = "";
        switch (request.getState()) {
            case "Wait":
                state = "待处理";
                break;
            case "Approved":
                state = "已批准";
                break;
            case "Rejected":
                state = "已驳回";
                break;
        }

        RequestVO vo = new RequestVO(request.getImgurl(), request.getShopname(), request.getRestaurantId(), request.getAddress(),
        request.getType(), request.getPhonenumber(), sdf.format(request.getRequestTime()), requestId, state);

        return ResultUtils.success(vo);
    }

    @Override
    public Result rejectRequest(int requestId) {
        UpdateRequest request = updateRequestMapper.selectByPrimaryKey(requestId);
        request.setState("Rejected");
        updateRequestMapper.updateByPrimaryKey(request);

        return ResultUtils.success();
    }

    @Override
    public Result approveRequest(int requestId) {
        UpdateRequest request = updateRequestMapper.selectByPrimaryKey(requestId);
        request.setState("Approved");
        updateRequestMapper.updateByPrimaryKey(request);

        return ResultUtils.success();
    }

    private boolean isNewMember(int memberId, List<OrderInfo> orderInfos, Date date) {
        for (OrderInfo item : orderInfos) {
            if (item.getOrderTime().before(date) && item.getMemberId() == memberId)
                return false;
        }

        return true;
    }

    private int getNewMemberNumbers(Date date, List<OrderInfo> orderInfos) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        List<OrderInfo> todayOrders = new ArrayList<>();
        for (OrderInfo item : orderInfos) {
            LocalDate ld1 = new LocalDate(new DateTime(date));
            LocalDate ld2 = new LocalDate(new DateTime(item.getOrderTime())).minusDays(1);
            if (ld1.equals(ld2)) {
                todayOrders.add(item);
            }
        }
        List<Integer> todayMemberIds = new ArrayList<>();
        List<OrderInfo> todayInfos = new ArrayList<>();

        //去掉同一天中重复下单的id
        for (OrderInfo item : todayOrders) {
            if ( !todayMemberIds.contains(item.getMemberId())) {
                todayMemberIds.add(item.getMemberId());
                todayInfos.add(item);
            }

        }

        int newMemberNumber = 0;

        for (OrderInfo item : todayInfos) {
            if (isNewMember(item.getMemberId(), orderInfos, date)) {
                newMemberNumber++;
            }
        }

        return newMemberNumber;
    }


}
