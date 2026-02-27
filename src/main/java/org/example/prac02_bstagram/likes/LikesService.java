package org.example.prac02_bstagram.likes;

import lombok.RequiredArgsConstructor;
import org.example.prac02_bstagram.feed.model.Feed;
import org.example.prac02_bstagram.likes.model.Likes;
import org.example.prac02_bstagram.likes.model.LikesDto;
import org.example.prac02_bstagram.user.model.AuthUserDetails;
import org.example.prac02_bstagram.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;


    public void likes(AuthUserDetails user, Long feedIdx) {
        User userEntity = user.toEntity();
        Feed feedEntity = Feed.builder().idx(feedIdx).build();
        Optional<Likes> result = likesRepository.findByUserAndFeed(userEntity, feedEntity);

        if(result.isPresent()) {
            likesRepository.delete(result.get());
        } else {
            Likes likes = Likes.builder()
                    .user(userEntity)
                    .feed(feedEntity)
                    .build();
            likesRepository.save(likes);
        }
    }
}
