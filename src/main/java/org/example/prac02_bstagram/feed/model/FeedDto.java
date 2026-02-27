package org.example.prac02_bstagram.feed.model;

import lombok.Builder;
import lombok.Getter;
import org.example.prac02_bstagram.likes.model.Likes;
import org.example.prac02_bstagram.user.model.AuthUserDetails;
import org.example.prac02_bstagram.user.model.User;

import java.util.List;

public class FeedDto {
    @Getter
    public static class Reg {
        private String contents;
        private List<Long> imageIdxList;

        public Feed toEntity(AuthUserDetails user) {
            return Feed.builder()
                    .contents(contents)
                    .user(user.toEntity())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ImageUploadRes {
        private Long idx;
        private String imageUrl;

        public static ImageUploadRes from(FeedImage entity) {
            return ImageUploadRes.builder()
                    .idx(entity.getIdx())
                    .imageUrl(entity.getImageUrl())
                    .build();
        }
    }


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
