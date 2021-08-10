package com.example.demo.src.review;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.DeleteOrderReq;
import com.example.demo.src.review.model.GetReviewRes;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import com.example.demo.utils.JwtService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.demo.src.review.model.*;
import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final JwtService jwtService;

    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService, JwtService jwtService) {
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetReviewRes>> getReview(@RequestParam(required = false) Integer storeNo,@RequestParam(required = false) Integer userNo) {
        try {
            if(storeNo != null){
                List<GetReviewRes> getReviewRes = reviewProvider.getReviewByStore(storeNo);
                return new BaseResponse<>(getReviewRes);
            }
            if(userNo != null){
                List<GetReviewRes> getReviewRes = reviewProvider.getReviewsByUser(userNo);
                return new BaseResponse<>(getReviewRes);
            }
            List<GetReviewRes> getReviewRes = reviewProvider.getReview();
            return new BaseResponse<>(getReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    @ResponseBody
//    @GetMapping("")
//    public BaseResponse<List<GetReviewRes>> getReview(@RequestParam(required = false) Integer userNo) {
//        try {
//            if(userNo == null){
//                List<GetReviewRes> getReviewRes = reviewProvider.getReview();
//                return new BaseResponse<>(getReviewRes);
//            }
//            List<GetReviewRes> getReviewRes = reviewProvider.getReviewsByUser(userNo);
//            return new BaseResponse<>(getReviewRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }



    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostReviewRes> createReview(@RequestBody PostReviewReq postReviewReq) {
        try {
            PostReviewRes postReviewRes = reviewService.createReview(postReviewReq);
            return new BaseResponse<>(postReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("")
    public BaseResponse<String> modifyReview(@RequestBody Review review) {
        try {
            PatchReviewReq patchReviewReq = new PatchReviewReq(review.getStar(), review.getContents(), review.getImage(),review.getReview_no());
            reviewService.modifyReview(patchReviewReq);

            String result = "리뷰 수정 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("/{review_no}")
    public BaseResponse<String> deleteReview(@PathVariable("review_no") int review_no, @RequestBody Review review) {
        try {
            DeleteReviewReq deleteReviewReq = new DeleteReviewReq(review_no);
            reviewService.deleteReview(deleteReviewReq);

            String result = "리뷰 삭제 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
