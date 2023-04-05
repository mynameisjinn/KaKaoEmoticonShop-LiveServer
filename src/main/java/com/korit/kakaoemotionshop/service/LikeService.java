package com.korit.kakaoemotionshop.service;

import com.korit.kakaoemotionshop.entity.EmoLike;
import com.korit.kakaoemotionshop.exception.CustomLikeException;
import com.korit.kakaoemotionshop.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public int getLikeStatus(int emoId, int userId) {
        EmoLike emoLike = EmoLike.builder()
                .emoId(emoId)
                .userId(userId)
                .build();

        return likeRepository.getLikeStatus(emoLike);
    }

    public int like(int emoId, int userId) {
        EmoLike emoLike = EmoLike.builder()
                .emoId(emoId)
                .userId(userId)
                .build();

        likeRepository.addLike(emoLike);
        return likeRepository.getLikeCount(emoId);
    }

    public int dislike(int emoId, int userId) {
        EmoLike emoLike = EmoLike.builder()
                .emoId(emoId)
                .userId(userId)
                .build();

        likeRepository.deleteLike(emoLike);
        return likeRepository.getLikeCount(emoId);
    }
}
