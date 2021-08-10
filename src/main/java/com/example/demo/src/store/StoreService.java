package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.store.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Transactional()// command p 엔티티 매니저
public class StoreService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StoreDao storeDao;
    private final StoreProvider storeProvider;
    private final JwtService jwtService;

    @Autowired
    public StoreService(StoreDao storeDao, StoreProvider storeProvider, JwtService jwtService) {
        this.storeDao = storeDao;
        this.storeProvider = storeProvider;
        this.jwtService = jwtService;
    }


    //POST
    public PostStoreRes createStore(PostStoreReq postStoreReq) throws BaseException {
        if(storeProvider.checkId(postStoreReq.getId()) == 1){
            throw new BaseException(POST_STORES_EXISTS_ID);
        }
        if(storeProvider.checkNumber(postStoreReq.getStore_Rnumber()) == 1){
            throw new BaseException(POST_STORES_EXISTS_Rnumber);
        }
        try {
            int store_no = storeDao.createStore(postStoreReq);
            String jwt = jwtService.createJwt(store_no);
            return new PostStoreRes(jwt, store_no);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }

    public void modifyStoreName(PatchStoreReq patchStoreReq) throws BaseException{
        try {
            int result = storeDao.modifyStoreName(patchStoreReq);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyNotice(PatchStoreNoticeReq patchStoreNoticeReq) throws BaseException{
        try {
            int result = storeDao.modifyStoreNotice(patchStoreNoticeReq);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_NOTICE);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public void deleteStore(DeleteStoreReq deleteStoreReq) throws BaseException{
        try {
            int result = storeDao.deleteStore(deleteStoreReq);
            if (result == 0) {
                throw new BaseException(DELETE_FAIL_STORE);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
