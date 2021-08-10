package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.order.model.DeleteOrderReq;
import com.example.demo.src.order.model.PatchOrderReq;
import com.example.demo.src.order.model.PostOrderReq;
import com.example.demo.src.order.model.PostOrderRes;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.demo.config.BaseResponseStatus.*;
@Service
public class OrderService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OrderDao orderDao;
    private final OrderProvider orderProvider;

    @Autowired
    public OrderService(OrderDao orderDao, OrderProvider orderProvider) {
        this.orderDao = orderDao;
        this.orderProvider = orderProvider;
    }

    public PostOrderRes createOrder(PostOrderReq postOrderReq) throws BaseException{
        try {
            int order_no = orderDao.createOrder(postOrderReq);
            return new PostOrderRes(order_no);
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public void modifyOrder(PatchOrderReq patchOrderReq) throws BaseException{
        try {
            int result = orderDao.modifyOrder(patchOrderReq);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_ORDER);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteOrder(DeleteOrderReq deleteOrderReq) throws BaseException {
        try {
            int result = orderDao.deleteOrder(deleteOrderReq);
            if (result == 0) {
                throw new BaseException(DELETE_FAIL_ORDER);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
