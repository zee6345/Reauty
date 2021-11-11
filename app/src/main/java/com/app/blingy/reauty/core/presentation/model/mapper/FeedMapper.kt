package com.app.blingy.reauty.core.presentation.model.mapper

import com.app.blingy.reauty.core.domain.model.feed.Feed
import com.app.blingy.reauty.feature.home.domain.model.UiModelFeed
import javax.inject.Inject

class FeedMapper @Inject constructor() : UiMapper<List<Feed>, List<UiModelFeed>> {
    override fun mapToView(input: List<Feed>): List<UiModelFeed> {
        val list: MutableList<UiModelFeed> = mutableListOf()
        input.forEach { feed ->
            val item = UiModelFeed(
                comments = feed.comments,
                haveBrandMention = feed.haveBrandMention,
                haveHashTagsMention = feed.haveHashTagsMention,
                havePersonalMention = feed.havePersonalMention,
                likes = feed.likes,
                postImageLink = feed.postImageLink,
                postOwnerId = feed.postOwnerID,
                postOwnerName = feed.postOwnerName,
                postText = feed.postText,
                postType = feed.postType,
                postVideoLink = feed.postVideoLink,
                postStatus = feed.post_status,
                timeStamp = feed.timeStamp,
                postOwnerImageLink = feed.postOwnerImageLink,
            )
            list.add(item)
        }
        return list
    }


}