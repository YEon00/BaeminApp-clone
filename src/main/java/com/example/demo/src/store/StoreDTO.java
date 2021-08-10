package com.example.demo.src.store;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.Date;

@SqlResultSetMapping(
        name = "StoreMemberMapping",
        classes = @ConstructorResult(
                targetClass = StoreDTO.class,
                columns = {
                        @ColumnResult(name="storeNo", type = Integer.class),
                        @ColumnResult(name="categoryNo",type = Integer.class )
                }
        )
)
public class StoreDTO {
    private int store_no;
    private int category_no;
    private String intro;
    private String phone;
    private int deliver_time;
    private String hygiene_info;
    private String name;
    private int min_price;
    private int isPayment_now;
    private int isPayment_meet;
    private Date updatedAt;
}
