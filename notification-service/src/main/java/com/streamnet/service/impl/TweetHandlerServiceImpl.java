package com.streamnet.service.impl;

import com.streamnet.commons.event.TweetNotificationDto;
import com.streamnet.commons.event.UpdateTweetEvent;
import com.streamnet.model.Tweet;
import com.streamnet.model.User;
import com.streamnet.repository.TweetRepository;
import com.streamnet.service.TweetHandlerService;
import com.streamnet.service.UserHandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TweetHandlerServiceImpl implements TweetHandlerService {

    private final TweetRepository tweetRepository;
    private final UserHandlerService userHandlerService;

    @Override
    @Transactional
    public void handleUpdateTweet(UpdateTweetEvent tweetEvent) {
        tweetRepository.findById(tweetEvent.getId())
                .map(tweet -> {
                    tweet.setText(tweetEvent.getText());
                    return tweet;
                });
    }

    @Override
    @Transactional
    public Tweet getOrCreateTweet(TweetNotificationDto tweet) {
        User author = userHandlerService.getOrCreateUser(tweet.getAuthor());
        return tweetRepository.findById(tweet.getId())
                .orElseGet(() -> {
                    Tweet newTweet = new Tweet();
                    newTweet.setId(tweet.getId());
                    newTweet.setText(tweet.getText());
                    newTweet.setTweetType(tweet.getTweetType());
                    newTweet.setAuthor(author);
                    return tweetRepository.save(newTweet);
                });
    }
}
