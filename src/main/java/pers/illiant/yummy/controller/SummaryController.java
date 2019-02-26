package pers.illiant.yummy.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.illiant.yummy.service.SummaryService;
import pers.illiant.yummy.util.Result;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
public class SummaryController {

    @Resource
    SummaryService summaryService;

    @RequestMapping("/getRestaurantSummary")
    public Result getRestaurantSummary() {
        return summaryService.restaurantPositionSummary();
    }

    @RequestMapping("/getRestaurantClassSummary")
    public Result getRestaurantClassSummary() {
        return summaryService.restaurantClassSummary();
    }

    @RequestMapping("/getMemberPositionSummary")
    public Result getMemberPositionSummary() {
        return summaryService.memberPositionSummary();
    }

    @RequestMapping("/getMemberLevelSummary")
    public Result getMemberLevelSummary() {
        return summaryService.memberLevelSummary();
    }

    @RequestMapping("/getBusinessSummary")
    public Result getBusinessSummary(String restaurantId) {
        return summaryService.businessSummary(restaurantId);
    }

    @RequestMapping("/getNewMemberSummary")
    public Result getNewMemberSummary(String restaurantId) {
        return summaryService.newMemberSummary(restaurantId);
    }

    @RequestMapping("/getOrderSummary")
    public Result getOrderSummary(String restaurantId) {
        return summaryService.orderCountSummary(restaurantId);
    }

    @RequestMapping("/getRefundSummary")
    public Result getRefundSummary(String restaurantId) {
        return summaryService.refundSummary(restaurantId);
    }

    @RequestMapping("/getFinancialSummary")
    public Result getFinancialSummary() { return summaryService.financialSummary(); }

    @RequestMapping("/getRequests")
    public Result getRequests() {
        return summaryService.getRequests();
    }

    @RequestMapping("/getRequest")
    public Result getRequest(@RequestParam int requestId) {
        return summaryService.getRequest(requestId);
    }

    @RequestMapping("/rejectRequest")
    public Result rejectRequest(@RequestParam int requestId) {
        return summaryService.rejectRequest(requestId);
    }

    @RequestMapping("approveRequest")
    public Result approveRequest(@RequestParam int requestId) {
        return summaryService.approveRequest(requestId);
    }
}
