package org.example.prac02_bstagram.likes;

import org.example.prac02_bstagram.feed.model.Feed;
import org.example.prac02_bstagram.likes.model.Likes;
import org.example.prac02_bstagram.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserAndFeed(User user, Feed feed);
}
