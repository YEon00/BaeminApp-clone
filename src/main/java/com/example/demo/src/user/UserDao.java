package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from User";
        List<GetUserRes> query = this.jdbcTemplate.query(getUsersQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("user_no"),
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("nickname"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("image"),
                        rs.getString("status"))
        );
        return query;
    }

    public List<GetUserRes> getUsersByName(String name){
        String getUsersByNameQuery = "select * from User where name=?";
        String getUsersByNameParams = name;
        System.out.println(name);
        return this.jdbcTemplate.query(getUsersByNameQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("user_no"),
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("nickname"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("image"),
                        rs.getString("status")),
                getUsersByNameParams);

    }

    public GetUserRes getUser(int user_no){
        String getUserQuery = "select * from User where user_no = ?";
        int getUserParams = user_no;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("user_no"),
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("nickname"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("image"),
                        rs.getString("status")),
                getUserParams);
    }


    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into User(name, id, password, email, nickname, address," +
                "phone, image) VALUES (?,?,?,?,?,?,?,?)";

        Object[] createUserParams = new Object[]{postUserReq.getName(), postUserReq.getId(), postUserReq.getPassword(),
        postUserReq.getEmail(), postUserReq.getNickname(), postUserReq.getAddress(), postUserReq.getPhone(), postUserReq.getImage()};
        this.jdbcTemplate.update(createUserQuery,createUserParams);
        System.out.println("쿼리성공");
        String lastInserIdQuery = "select last_insert_id()";
        System.out.println("이것도");
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from User where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int checkId(String id){
        String checkIdQuery = "select exists(select id from User where id = ?)";
        String checkIdParams = id;
        return this.jdbcTemplate.queryForObject(checkIdQuery,
                int.class,
                checkIdParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update User set Name = ? where user_no = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getName(), patchUserReq.getUser_no()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select * from User where id = ?";
        String getPwdParams = postLoginReq.getId();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("user_no"),
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("nickname"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("image")
                ),
                getPwdParams);
    }

    public int deleteUser(DeleteUserReq deleteUserReq) {
        String deleteUserQuery = "delete from User where user_no = ?";
        Object[] deleteUserParams = new Object[]{deleteUserReq.getUser_no()};
        return this.jdbcTemplate.update(deleteUserQuery, deleteUserParams);
    }


}
