package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.store.model.GetStoreRes;
import com.example.demo.src.store.StoreDao;
import com.example.demo.src.store.model.*;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class StoreProvider {

    private final StoreDao storeDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StoreProvider(StoreDao storeDao, JwtService jwtService) {
        this.storeDao = storeDao;
        this.jwtService = jwtService;
    }

    public List<GetStoreRes> getStores() throws BaseException{
        try{
            List<GetStoreRes> getStoreRes = storeDao.getStores();
            return getStoreRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetStoreRes getStore(int store_no) throws BaseException {
        try {
            GetStoreRes getStoreRes = storeDao.getStore(store_no);
            return getStoreRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public List<GetStoreRes> getStoresByName(String name) throws BaseException{
        try{
            List<GetStoreRes> getStoreRes = storeDao.getStoresByName(name);
            return getStoreRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public int checkId(String id) throws BaseException{
        try{
            return storeDao.checkId(id);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public int checkNumber(String store_Rnumber) throws BaseException{
        try{
            return storeDao.checkNumber(store_Rnumber);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }





}
