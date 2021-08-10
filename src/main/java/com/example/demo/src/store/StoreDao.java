package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.src.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import javax.sql.DataSource;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.MODIFY_FAIL_USERNAME;

@Repository
public class StoreDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetStoreRes> getStores(){
        String getStoresQuery = "select * from Store";
//        float avg_star = this.jdbcTemplate.queryForObject("select avg(star) from Store AS S LEFT JOIN Review AS R on R.store_no = S.store_no where S.store_no = 1", float.class);
//        System.out.println("별점은");
//        System.out.println(avg_star);
        List<GetStoreRes> query = this.jdbcTemplate.query(getStoresQuery,
                (rs, rowNum) -> new GetStoreRes(
                        rs.getInt("store_no"),
                        rs.getInt("category_no"),
                        rs.getString("intro"),
                        rs.getString("phone"),
                        rs.getInt("deliver_time"),
                        rs.getString("hygiene_info"),
                        rs.getString("name"),
                        rs.getInt("min_price"),
                        rs.getInt("isPayment_now"),
                        rs.getInt("isPayment_meet"),
                        rs.getDate("updatedAt")));
        return query;
    }

    public GetStoreRes getStore(int store_no){
        String getStoresQuery = "select * from Store where store_no = ?";
        int getStoreParams = store_no;
        return this.jdbcTemplate.queryForObject(getStoresQuery,
                (rs, rowNum) -> new GetStoreRes(
                        rs.getInt("store_no"),
                        rs.getInt("category_no"),
                        rs.getString("intro"),
                        rs.getString("phone"),
                        rs.getInt("deliver_time"),
                        rs.getString("hygiene_info"),
                        rs.getString("name"),
                        rs.getInt("min_price"),
                        rs.getInt("isPayment_now"),
                        rs.getInt("isPayment_meet"),
                        rs.getDate("updatedAt")),
                getStoreParams);
    }

    public List<GetStoreRes> getStoresByName(String name) {
        String getStoresByNameQuery = "select * from Store where name =?";
        String getStoresByNameParams = name;
        return this.jdbcTemplate.query(getStoresByNameQuery,
                (rs, rowNum) -> new GetStoreRes(
                        rs.getInt("store_no"),
                        rs.getInt("category_no"),
                        rs.getString("intro"),
                        rs.getString("phone"),
                        rs.getInt("deliver_time"),
                        rs.getString("hygiene_info"),
                        rs.getString("name"),
                        rs.getInt("min_price"),
                        rs.getInt("isPayment_now"),
                        rs.getInt("isPayment_meet"),
                        rs.getDate("updatedAt")),
                getStoresByNameParams);
    }
    public int createStore(PostStoreReq postStoreReq){
        String createStoreQuery = "insert into Store(id, name, category_no, intro, phone," +
                "deliver_time, bossname, store_address, store_Rnumber, hygiene_info, isTogo, isStore, min_price," +
                "isPayment_now,isPayment_meet, main_image, notice_image, notice_contents) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Object[] createStoreParams = new Object[]{postStoreReq.getId(), postStoreReq.getName(),postStoreReq.getCategory_no(),
                postStoreReq.getIntro(), postStoreReq.getPhone(), postStoreReq.getDeliver_time(), postStoreReq.getBossname(), postStoreReq.getStore_address(),
                postStoreReq.getStore_Rnumber(),  postStoreReq.getHygiene_info(), postStoreReq.getIsTogo(), postStoreReq.getIsStore(), postStoreReq.getMin_price(),
                postStoreReq.getIsPayment_now(), postStoreReq.getIsPayment_meet(), postStoreReq.getMain_image(),
                postStoreReq.getNotice_image(), postStoreReq.getNotice_contents()};

        System.out.println(createStoreParams[3]);

        this.jdbcTemplate.update(createStoreQuery, createStoreParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int checkId(String id){
        String checkIdQuery = "select exists(select id from Store where id = ?)";
        String checkIdParams = id;
        return this.jdbcTemplate.queryForObject(checkIdQuery,
                int.class,
                checkIdParams);
    }
    public int checkNumber(String store_Rnumber){
        String checkIdQuery = "select exists(select store_Rnumber from Store where store_Rnumber = ?)";
        String checkIdParams = store_Rnumber;
        return this.jdbcTemplate.queryForObject(checkIdQuery,
                int.class,
                checkIdParams);
    }

    public int modifyStoreName(PatchStoreReq patchStoreReq){
        String modifyStoreNameQuery = "update Store set name = ? where store_no = ?";
        Object[] modifyStoreNameParams = new Object[]{patchStoreReq.getName(), patchStoreReq.getStore_no()};
        return this.jdbcTemplate.update(modifyStoreNameQuery,modifyStoreNameParams);
    }

    public int modifyStoreNotice(PatchStoreNoticeReq patchStoreNoticeReq){
        String modifyNoticeQuery = "update Store set notice_image = ?,notice_contents = ? where store_no = ?";
        Object[] modifyNoticeParams = new Object[]{patchStoreNoticeReq.getNoticeImage(), patchStoreNoticeReq.getNoticeContents(), patchStoreNoticeReq.getStoreNo()};

        return this.jdbcTemplate.update(modifyNoticeQuery, modifyNoticeParams);

    }

    public int deleteStore(DeleteStoreReq deleteStoreReq){
        String modifyStoreNameQuery = "delete from Store where store_no = ?";
        Object[] modifyStoreNameParams = new Object[]{deleteStoreReq.getStore_no()};
        return this.jdbcTemplate.update(modifyStoreNameQuery,modifyStoreNameParams);
    }
}
