package org.example.prac02_bstagram.feed.model;

import lombok.Builder;
import lombok.Getter;

public class FeedDto {
    @Getter
    @Builder
    public static class FeedRes {
        private Long idx;
        private String contents;
        private String writer;

        public static FeedRes from(Feed entity) {
            return FeedRes.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
                    .build();
        }
    }
}
