package org.example.prac02_bstagram.feed.model;

import lombok.Builder;
import lombok.Getter;
import org.example.prac02_bstagram.likes.model.Likes;
import org.example.prac02_bstagram.user.model.AuthUserDetails;

public class FeedDto {
    @Getter
    @Builder
    public static class FeedRes {
        private Long idx;
        private String contents;
        private String writer;
        private int likesCount;
        private boolean isLikes;

        public static FeedRes from(Feed entity, AuthUserDetails user) {

//            boolean isLikes = false;
//            for(Likes likes : entity.getLikesList()) {
//                if(likes.getUser().getIdx() == user.getIdx()) {
//                    isLikes = true;
//                }
//            }

            return FeedRes.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
                    .likesCount(entity.getLikesList().size())
                    .isLikes(entity.getLikesList().stream().anyMatch(
                            likes -> likes.getUser().getIdx() == user.getIdx()
                    ))
                    .build();
        }
    }
}
