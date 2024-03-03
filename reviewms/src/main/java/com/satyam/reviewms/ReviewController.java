package com.satyam.reviewms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
        Review review = reviewService.getReviewById(reviewId);
        if(review == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review){
        Boolean isReviewSaved = reviewService.addReview(companyId, review);
        if(isReviewSaved){
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company does not exists", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@RequestBody Review updatedReview){
        Boolean isReviewUpdated = reviewService.updateReview(reviewId, updatedReview);
        if(!isReviewUpdated){
            return new ResponseEntity<>("Review does not exists", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        Boolean isReviewDeleted = reviewService.deleteReview(reviewId);
        if(!isReviewDeleted){
            return new ResponseEntity<>("Review does not exists", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
    }
}
