package com.korit.kakaoemotionshop.web.api;

import com.korit.kakaoemotionshop.entity.EmoLike;
import com.korit.kakaoemotionshop.security.PrincipalDetails;
import com.korit.kakaoemotionshop.service.LikeService;
import com.korit.kakaoemotionshop.web.dto.CMRespDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LikeApi {

    @Autowired
    private LikeService likeService;

    @ApiOperation(value = "좋아요 상태", notes = "유저의 좋아요 상태 조회")
    @GetMapping("/like/{emoId}/status")
    public ResponseEntity<CMRespDto<Integer>> getLikeStatus(@PathVariable int emoId,
                                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        int likeStatus = likeService.getLikeStatus(emoId, principalDetails.getUser().getUserId());

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully",likeStatus));
    }

    @ApiOperation(value = "좋아요 요청", notes = "좋아요 요청 메소드")
    @PostMapping("/emo/{emoId}/like")
    public ResponseEntity<CMRespDto<Integer>> like(@PathVariable int emoId,
                                             @AuthenticationPrincipal PrincipalDetails principalDetails){
        int likeCount = likeService.like(emoId, principalDetails.getUser().getUserId());
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully",likeCount));
    }

    @ApiOperation(value = "좋아요 취소", notes = "좋아요 취소 메소드")
    @DeleteMapping("/emo/{emoId}/like")
    public ResponseEntity<CMRespDto<Integer>> dislike(@PathVariable int emoId,
                                             @AuthenticationPrincipal PrincipalDetails principalDetails){
        int likeCount = likeService.dislike(emoId, principalDetails.getUser().getUserId());
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully",likeCount));
    }

}
