package org.example.prac02_bstagram.feed;

import lombok.RequiredArgsConstructor;
import org.example.prac02_bstagram.common.model.BaseResponse;
import org.example.prac02_bstagram.feed.model.FeedDto;
import org.example.prac02_bstagram.user.model.AuthUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity list(@AuthenticationPrincipal AuthUserDetails user) {
       List<FeedDto.FeedRes> result = feedService.list(user);

       return ResponseEntity.ok(BaseResponse.success(result));
    }
}
