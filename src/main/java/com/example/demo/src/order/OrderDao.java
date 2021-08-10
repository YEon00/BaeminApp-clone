package com.example.demo.src.order;

import com.example.demo.src.order.model.DeleteOrderReq;
import com.example.demo.src.order.model.GetOrderRes;
import com.example.demo.src.order.model.PatchOrderReq;
import com.example.demo.src.order.model.PostOrderReq;
import com.example.demo.src.review.model.GetReviewRes;
import com.example.demo.src.review.model.PostReviewReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j //로그
public class OrderDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);}

    public List<GetOrderRes> getOrder(){
        String getOrderQuery = "select * from OrderTable";
        System.out.println(getOrderQuery);
        List<GetOrderRes> query = this.jdbcTemplate.query(getOrderQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getInt("order_no"),
                        rs.getInt("basket_no"),
                        rs.getString("store_request"),
                        rs.getString("deliver_request"),
                        rs.getString("payment"),
                        rs.getString("usepoint_request"),
                        rs.getInt("point_no"),
                        rs.getString("coupon_request"),
                        rs.getInt("store_coupon"),
                        rs.getInt("status"),
                        rs.getDate("updatedAt")
                ));
        return query;

    }

    public List<GetOrderRes> getOrderByUser(int user_no){
        String getOrderQuery = "select * from OrderTable as OT LEFT JOIN Order_basket as OB" +
                " on OT.basket_no = OB.basket_no where user_no = ?";
        int getOrderParams = user_no;

        List<GetOrderRes> query = this.jdbcTemplate.query(getOrderQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getInt("order_no"),
                        rs.getInt("basket_no"),
                        rs.getString("store_request"),
                        rs.getString("deliver_request"),
                        rs.getString("payment"),
                        rs.getString("usepoint_request"),
                        rs.getInt("point_no"),
                        rs.getString("coupon_request"),
                        rs.getInt("store_coupon"),
                        rs.getInt("status"),
                        rs.getDate("updatedAt")),
                getOrderParams);
        return query;

    }
    public List<GetOrderRes> getOrderByStore(int store_no){
        String getOrderQuery = "select * from OrderTable as OT LEFT JOIN Order_basket as OB" +
                " on OT.basket_no = OB.basket_no where store_no = ?";
        int getOrderParams = store_no;
        List<GetOrderRes> query = this.jdbcTemplate.query(getOrderQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getInt("order_no"),
                        rs.getInt("basket_no"),
                        rs.getString("store_request"),
                        rs.getString("deliver_request"),
                        rs.getString("payment"),
                        rs.getString("usepoint_request"),
                        rs.getInt("point_no"),
                        rs.getString("coupon_request"),
                        rs.getInt("store_coupon"),
                        rs.getInt("status"),
                        rs.getDate("updatedAt")),
                getOrderParams);
        return query;
    }


    public int createOrder(PostOrderReq postOrderReq) {
        String createOrderQuery = "insert into OrderTable(basket_no," +
                "store_request, deliver_request, payment, usepoint_request," +
                "point_no, coupon_request, store_coupon, status) VALUES" +
                "(?,?,?,?,?,?,?,?,?)";

        Object[] createOrderParams = new Object[]{postOrderReq.getBasketNo(), postOrderReq.getStoreRequest(),
                postOrderReq.getDeliverRequest(), postOrderReq.getPayment(), postOrderReq.getUsepointRequest(),
                postOrderReq.getPointNo(), postOrderReq.getCouponRequest(),
                postOrderReq.getStoreCoupon(), postOrderReq.getStatus()};
        for (int i = 0; i<createOrderParams.length;i++){
            System.out.println(createOrderParams[i]);
        }
        this.jdbcTemplate.update(createOrderQuery, createOrderParams);
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int modifyOrder(PatchOrderReq patchOrderReq) {
        String modifyQuery = "update OrderTable set store_request = ? , deliver_request = ? where order_no = ?";
        Object[] modifyParams = new Object[]{patchOrderReq.getStoreRequest(), patchOrderReq.getDeliverRequest(), patchOrderReq.getOrderNo()};
        return this.jdbcTemplate.update(modifyQuery, modifyParams);
    }

    public int deleteOrder(DeleteOrderReq deleteOrderReq) {
        String deleteQuery = "delete from OrderTable where order_no = ?";
        Object[] deleteParams = new Object[]{deleteOrderReq.getOrder_no()};
        return this.jdbcTemplate.update(deleteQuery, deleteParams);
    }
}
