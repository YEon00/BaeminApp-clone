package com.example.demo.src.store;

import com.example.demo.src.store.model.*;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/stores")
public class StoreController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final StoreProvider storeProvider;
    @Autowired
    private final StoreService storeService;
    @Autowired
    private final JwtService jwtService;

    public StoreController(StoreProvider storeProvider, StoreService storeService, JwtService jwtService){
        this.storeProvider = storeProvider;
        this.storeService = storeService;
        this.jwtService = jwtService;
    }
    /**
     * 가게 조회 API
     * [GET]/stores
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetStoreRes>> getStores(@RequestParam(required = false) String name) {
        try{
            if(name == null){
                List<GetStoreRes> getStoreRes = storeProvider.getStores();
                return new BaseResponse<>(getStoreRes);
            }
            List<GetStoreRes> getStoreRes = storeProvider.getStoresByName(name);
            return new BaseResponse<>(getStoreRes);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{store_no}")
    public BaseResponse<GetStoreRes> getStores(@PathVariable("store_no") int store_no) {
        try {
            GetStoreRes getStoreRes = storeProvider.getStore(store_no);
            return new BaseResponse<>(getStoreRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 가게 가입 API
     * [Post]/stores/register
     */
    @ResponseBody
    @PostMapping("/")
    public BaseResponse<PostStoreRes> createStore(@RequestBody PostStoreReq postStoreReq) {
//        if(postStoreReq.getDeliver_time() == null){
//            return new BaseResponse<>(POST_STORES_EMPTY_ID);
//        }
        if(postStoreReq.getId() == ""){
            return new BaseResponse<>(POST_STORES_EMPTY_ID);
        }
        if(postStoreReq.getName() == ""){
            return new BaseResponse<>(POST_STORES_EMPTY_NAME);
        }
        if(postStoreReq.getStore_Rnumber() == ""){
            return new BaseResponse<>(POST_STORES_EMPTY_Rnumber);
        }
        if(postStoreReq.getIntro() == ""){
            return new BaseResponse<>(POST_STORES_EMPTY_INTRO);
        }
        try {
            PostStoreRes postStoreRes = storeService.createStore(postStoreReq);
            return new BaseResponse<>(postStoreRes);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    @PatchMapping("/{store_no}")
    public BaseResponse<String> modifyStoreName(@PathVariable("store_no") int store_no, @RequestBody Store store){
        try {
            PatchStoreReq patchStoreReq = new PatchStoreReq(store_no,store.getName());
            storeService.modifyStoreName(patchStoreReq);

            String result = "가게 이름 변경 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/notice/{store_no}")
    public BaseResponse<String> modifyNotice(@PathVariable("store_no") int store_no, @RequestBody Store store) {
        try {
            PatchStoreNoticeReq patchStoreNoticeReq = new PatchStoreNoticeReq(store_no, store.getNotice_image(), store.getNotice_contents());
            storeService.modifyNotice(patchStoreNoticeReq);
            String result = "공지사항 수정 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }


    @DeleteMapping("/{store_no}")
    public BaseResponse<String> deleteStore(@PathVariable("store_no") int store_no, @RequestBody Store store){
        try {
            DeleteStoreReq deleteStoreReq = new DeleteStoreReq(store_no);
            storeService.deleteStore(deleteStoreReq);

            String result = "가게 삭제 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
