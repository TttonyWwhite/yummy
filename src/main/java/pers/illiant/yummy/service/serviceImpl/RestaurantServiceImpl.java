package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pers.illiant.yummy.dao.FoodMapper;
import pers.illiant.yummy.dao.RestaurantMapper;
import pers.illiant.yummy.entity.Food;
import pers.illiant.yummy.entity.Restaurant;
import pers.illiant.yummy.model.FoodVO_release;
import pers.illiant.yummy.model.RestaurantVO_post;
import pers.illiant.yummy.model.RestaurantVO_register;
import pers.illiant.yummy.service.RestaurantService;
import pers.illiant.yummy.util.IDCreater;
import pers.illiant.yummy.util.MailSender;
import pers.illiant.yummy.util.Result;
import pers.illiant.yummy.util.ResultUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantMapper restaurantMapper;

    @Autowired
    FoodMapper foodMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public Result register(RestaurantVO_register restaurant) {
        String id = IDCreater.doGenRandomNum(7);
        Restaurant restaurant1 = new Restaurant(id, restaurant.getAddress(), restaurant.getType(), restaurant.getPhoneNumber(), restaurant.getLng_lat(), restaurant.getImgUrl(), restaurant.getShopName(), restaurant.getPassword(), restaurant.getEmail());
        try {
            restaurantMapper.insert(restaurant1);
            sendRestaurantMail(restaurant.getEmail(), id);
            return ResultUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.error(11122, "餐厅注册失败");
        }
    }

    @Override
    public List<RestaurantVO_post> getAll() {
        List<Restaurant> list = restaurantMapper.selectAll();
        List<RestaurantVO_post> retList = new ArrayList<>();
        for (Restaurant rest : list) {
            RestaurantVO_post vo = new RestaurantVO_post();
            vo.setShopName(rest.getShopName());
            vo.setImgUrl(rest.getImgurl());
            vo.setRestaurantId(rest.getRestaurantId());
            retList.add(vo);
        }

        return retList;
    }

    @Override
    public Result releaseFood(FoodVO_release food) {
        Food food1 = new Food();
        food1.setFoodname(food.getFoodName());
        food1.setImgurl(food.getImgUrl());
        food1.setPrice(food.getPrice());
        food1.setRestaurantId(food.getRestaurantId());

        try {
            foodMapper.insert(food1);
            return ResultUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.error(11119, "fail to release food");
        }
    }

    @Override
    public Result login(String id, String password) {
        Restaurant restaurant = restaurantMapper.selectByPrimaryKey(id);
        if (restaurant == null)
            return ResultUtils.error(11120, "nonexistence");
        if (restaurant.getPassword().equals(password))
            return ResultUtils.success();
        else
            return ResultUtils.error(11121, "Wrong Password");
    }

    @Override
    public Restaurant findById(String id) {
        return restaurantMapper.selectByPrimaryKey(id);
    }

    public void sendRestaurantMail(String recipient, String restaurantId) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(recipient);
            helper.setSubject("餐厅注册成功邮件");
            Context context = new Context();
            context.setVariable("restaurantId", restaurantId);
            String emailContet = templateEngine.process("restaurantEmailTemplate", context);
            helper.setText(emailContet, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }
}
