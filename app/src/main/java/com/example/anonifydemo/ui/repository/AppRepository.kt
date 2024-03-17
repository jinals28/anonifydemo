package com.example.anonifydemo.ui.repository

import android.util.Log
import com.example.anonifydemo.R
import com.example.anonifydemo.ui.dataClasses.Avatar
import com.example.anonifydemo.ui.dataClasses.Comment
import com.example.anonifydemo.ui.dataClasses.FollowingTopic
import com.example.anonifydemo.ui.dataClasses.Like
import com.example.anonifydemo.ui.dataClasses.Post
import com.example.anonifydemo.ui.dataClasses.Topic
import com.example.anonifydemo.ui.dataClasses.User

object AppRepository {
    // Users table
    private val users = mutableListOf<User>()

    // Posts table
    private val posts = mutableListOf<Post>()

    // Comments table
    private val comments = mutableListOf<Comment>()

    // Topics table
    private val topics = mutableListOf<Topic>(
        Topic(1, "#Entertainment"),
        Topic(2, "#Social"),
        Topic(3, "#FashionAndBeauty"),
        Topic(4, "#Mentalhealth"),
        Topic(5, "#InternshipAndJobs"),
        Topic(6, "#CollegeStories"),
        Topic(7, "#TravelTopics"),
        Topic(8, "#Technology"),
        Topic(9, "#Career"),
        Topic(10, "#Anxiety"),
        Topic(11, "#Family"),
        Topic(12, "#Sports"),
        Topic(13, "#Addiction"),
        Topic(14, "#Advice"),
        Topic(15, "#Aging"),
        Topic(16, "#BadHabits"),
        Topic(17, "#BodyShaming"),
        Topic(18, "#TimeManagement"),
        Topic(19, "#Racism"),
        Topic(20, "#Other")
    )

    private val followingTopicList = mutableListOf<FollowingTopic>()

    // Likes table
    private val likes = mutableListOf<Like>()

    private val avatarList = listOf<Avatar>(
        Avatar(1,R.drawable.dinosaur,"Moki"),
        Avatar(2,R.drawable.dog,"Jinto"),
        Avatar(3,R.drawable.panda,"Yarri"),
        Avatar(4,R.drawable.rabbit,"Zink"),
        Avatar(5,R.drawable.bear,"Loki"),
        Avatar(6,R.drawable.cat,"Yolo"),
        Avatar(7,R.drawable.octopus,"Kairo"),
        Avatar(8,R.drawable.owl,"Lumi"),
        Avatar(9,R.drawable.deer,"Yara"),
        Avatar(10,R.drawable.tiger,"Lokai"),
        Avatar(11,R.drawable.shark,"Soli"),
        Avatar(12,R.drawable.elephant,"Juno"),
        Avatar(13,R.drawable.lion,"Simba"),
        Avatar(14,R.drawable.wolf,"Jinx"),
        Avatar(15,R.drawable.sloth,"Zinna"),
        Avatar(16,R.drawable.rabbit2,"Lexa"),
        Avatar(17,R.drawable.llama,"Lyric"),
        Avatar(18,R.drawable.penguin,"Zolar")
    )

    // Other tables can be similarly defined

    // Add user to repository
    fun addUser(user: User) {
        users.add(user)
        Log.d("Anonify : Repo", users.toString())
    }

    // Add post to repository
    fun addPost(post: Post) {
        posts.add(post)
    }

    // Add comment to repository
    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    // Add topic to repository
    fun addTopic(topic: Topic) {
        topics.add(topic)
    }

    fun addFollowingTopic(followingTopic: FollowingTopic) {
        followingTopicList.add(followingTopic)
    }

    // Add like to repository
    fun addLike(like: Like) {
        likes.add(like)
    }

    // Other CRUD operations can be added similarly

    // Get all users from repository
    fun getUsers(): List<User> {
        return users
    }

    // Get all posts from repository
    fun getPosts(): List<Post> {
        return posts
    }

    // Get all comments from repository
    fun getComments(): List<Comment> {
        return comments
    }

    // Get all topics from repository
    fun getTopics(): List<Topic> {
        return topics
    }

    fun getFollowingTopics(): List<FollowingTopic> {
        return followingTopicList
    }

    // Get all likes from repository
    fun getLikes(): List<Like> {
        return likes
    }

    fun getAvatarList() : List<Avatar>{
        return avatarList
    }

    fun getUser(uid: String): User? {

        return users.find { it.uid == uid }

    }



    // Other methods for fetching data can be added similarly
}