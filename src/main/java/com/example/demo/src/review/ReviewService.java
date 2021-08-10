package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.review.model.DeleteReviewReq;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import com.example.demo.utils.JwtService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ReviewService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;
    private final JwtService jwtService;

    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider, JwtService jwtService) {
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;
        this.jwtService = jwtService;
    }

    public PostReviewRes createReview(PostReviewReq postReviewReq) throws BaseException{
        try {
            int review_no = reviewDao.createReview(postReviewReq);
            String jwt = jwtService.createReviewJwt(review_no);
            return new PostReviewRes(review_no);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyReview(PatchReviewReq patchReviewReq) throws BaseException{
        try {
            int result = reviewDao.modifyReview(patchReviewReq);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_REVIEW);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteReview(DeleteReviewReq deleteReviewReq) throws BaseException {
        try {
            int result = reviewDao.deleteReview(deleteReviewReq);
            if (result == 0) {
                throw new BaseException(DELETE_FAIL_REVIEW);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
