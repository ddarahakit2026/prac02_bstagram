package org.example.prac02_bstagram.feed;

import lombok.RequiredArgsConstructor;
import org.example.prac02_bstagram.common.model.BaseResponse;
import org.example.prac02_bstagram.feed.model.FeedDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/feed")
@RestController
public class FeedController {
    private final FeedService feedService;

    @GetMapping("/list")
    public ResponseEntity list() {
       List<FeedDto.FeedRes> result = feedService.list();

       return ResponseEntity.ok(BaseResponse.success(result));
    }
}
