package org.example.prac02_bstagram.feed;

import lombok.RequiredArgsConstructor;
import org.example.prac02_bstagram.feed.model.Feed;
import org.example.prac02_bstagram.feed.model.FeedDto;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedRepository feedRepository;

    public List<FeedDto.FeedRes> list() {
        List<Feed> entityList = feedRepository.findAll();

        return entityList.stream().map(FeedDto.FeedRes::from).toList();
    }
}
