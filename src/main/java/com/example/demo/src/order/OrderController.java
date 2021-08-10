package com.example.demo.src.order;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.order.model.GetOrderRes;
import com.example.demo.src.order.model.PatchOrderReq;
import com.example.demo.src.order.model.PostOrderReq;
import com.example.demo.src.order.model.PostOrderRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.src.order.model.*;

import static com.example.demo.config.BaseResponseStatus.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final OrderProvider orderProvider;
    @Autowired
    private final OrderService orderService;

    public OrderController(OrderProvider orderProvider, OrderService orderService) {
        this.orderProvider = orderProvider;
        this.orderService = orderService;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetOrderRes>> getOrder(@RequestParam(required = false) Integer storeNo,@RequestParam(required = false) Integer userNo) {
        try {
            if(storeNo != null) {
                List<GetOrderRes> getOrderRes = orderProvider.getOrderByStore(storeNo);
                return new BaseResponse<>(getOrderRes);
            }
            if(userNo != null){
                List<GetOrderRes> getOrderRes = orderProvider.getOrderByUser(userNo);
                return new BaseResponse<>(getOrderRes);
            }
            List<GetOrderRes> getOrderRes = orderProvider.getOrder();
            return new BaseResponse<>(getOrderRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostOrderRes> createOrder(@RequestBody PostOrderReq postOrderReq) {
        if(postOrderReq.getPayment() == "" ){
            return new BaseResponse<>(POST_ORDER_EMPTY_PAYMENT);
        }
        try {
            PostOrderRes postOrderRes = orderService.createOrder(postOrderReq);
            return new BaseResponse<>(postOrderRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("")
    public BaseResponse<String> modifyOrder(@RequestBody Order order) {
        try {
            PatchOrderReq patchOrderReq = new PatchOrderReq(order.getStoreRequest(), order.getDeliverRequest(),order.getOrderNo());
            orderService.modifyOrder(patchOrderReq);

            String result = "주문 수정 완료";
            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("/{order_no}")
    public BaseResponse<String> deleteOrder(@PathVariable("order_no") int order_no, @RequestBody Order order) {
        try {
            DeleteOrderReq deleteOrderReq = new DeleteOrderReq(order_no);
            orderService.deleteOrder(deleteOrderReq);

            String result = "주문 삭제 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
