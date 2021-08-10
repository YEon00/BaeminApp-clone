package com.example.demo.src.review;

import com.example.demo.src.order.model.DeleteOrderReq;
import com.example.demo.src.review.model.DeleteReviewReq;
import com.example.demo.src.review.model.GetReviewRes;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.src.review.model.PostReviewReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);}

    public List<GetReviewRes> getReview(){
        String getReviewQuery = "select * from Review";
        List<GetReviewRes> query = this.jdbcTemplate.query(getReviewQuery,
                (rs, rowNum) -> new GetReviewRes(
                        rs.getInt("review_no"),
                        rs.getInt("user_no"),
                        rs.getInt("store_no"),
                        rs.getFloat("star"),
                        rs.getString("contents"),
                        rs.getString("image"),
                        rs.getDate("updatedAt")
                ));
        return query;

    }

    public List<GetReviewRes> getReviewsByUser(int user_no){
        String ByUserQuery = "select * from Review where user_no = ?";
        int ByUserParams = user_no;
        List<GetReviewRes> query =  this.jdbcTemplate.query(ByUserQuery,
                (rs, rowNum) -> new GetReviewRes(
                        rs.getInt("review_no"),
                        rs.getInt("user_no"),
                        rs.getInt("store_no"),
                        rs.getFloat("star"),
                        rs.getString("contents"),
                        rs.getString("image"),
                        rs.getDate("updatedAt")
                ), ByUserParams);
        return query;
    }

    public List<GetReviewRes> getReviewByStore(int store_no){
        String getReviewQuery = "select * from Review where store_no = ?";
        int getReviewParams = store_no;

        List<GetReviewRes> query =  this.jdbcTemplate.query(getReviewQuery,
                (rs, rowNum) -> new GetReviewRes(
                        rs.getInt("review_no"),
                        rs.getInt("user_no"),
                        rs.getInt("store_no"),
                        rs.getFloat("star"),
                        rs.getString("contents"),
                        rs.getString("image"),
                        rs.getDate("updatedAt")),
                getReviewParams);
        return query;
    }


    public int createReview(PostReviewReq postReviewReq) {
        String createReviewQuery = "insert into Review(user_no, store_no" +
                ",star, contents, image) values(?,?,?,?,?)";

        Object[] createReviewParams = new Object[]{postReviewReq.getUserNo(), postReviewReq.getStoreNo(),
                postReviewReq.getStar(), postReviewReq.getContents(), postReviewReq.getImage()};
        System.out.println(createReviewParams[1]);
        this.jdbcTemplate.update(createReviewQuery, createReviewParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int modifyReview(PatchReviewReq patchReviewReq) {
        String modifyQuery = "update Review set star = ?, contents = ?, image = ? where review_no =?";
        Object[] modifyParams = new Object[]{patchReviewReq.getStar(), patchReviewReq.getContents(), patchReviewReq.getImage(), patchReviewReq.getReviewNo()};
        return this.jdbcTemplate.update(modifyQuery, modifyParams);
    }

    public int deleteReview(DeleteReviewReq deleteReviewReq) {
        String deleteQuery = "delete from Review where review_no = ?";
        Object[] deleteParams = new Object[]{deleteReviewReq.getReviewNo()};
        return this.jdbcTemplate.update(deleteQuery, deleteParams);
    }



}
