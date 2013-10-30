package com.grailsinaction

import static org.junit.Assert.*

class PostIntegrationTests {

   void testFirstPost() {
       def user = new MiniUser(userId: 'Marty', password: 'ItisApassword', homepage:'https://www.google.com').save()

       def post1 = new Post(postContent: 'First post...')
       user.addToPosts(post1)       
       def post2 = new Post(postContent: 'Second post...')
       user.addToPosts(post2)       
       def post3 = new Post(postContent: 'Third post...')
       user.addToPosts(post3)
       
       assertEquals 3, user.posts.size()
       
       user.delete()
       
   }

    void testAccessingPosts() {
       def user = new MiniUser(userId: 'Marty', password: 'ItisApassword', homepage:'https://www.google.com').save()
       
       def post1 = new Post(postContent: 'First')
       user.addToPosts(post1)       
       def post2 = new Post(postContent: 'Second')
       user.addToPosts(post2)       
       def post3 = new Post(postContent: 'Third')
       user.addToPosts(post3)
       
       def postNames = user.posts.collect{ it.postContent }
       assertEquals (['First','Second','Third'], postNames.sort())
       user.delete()
       
    }
    
    void testPostWithTags() {
        
        def user = new MiniUser(userId: 'HenryTheThird', password:'r32r2rbhb').save()
        
        def tagGroovy = new Tag(name: 'Groovy')
        def tagGrails = new Tag(name: 'Grails')
        user.addToTags(tagGroovy)
        user.addToTags(tagGrails)
        
        def tagNames = user.tags*.name
        assertEquals(['Grails','Groovy'] , tagNames.sort())
        
        def groovyPost = new Post(postContent:'A groovy post')
        user.addToPosts(groovyPost)
        groovyPost.addToTags(tagGroovy)
        assertEquals 1, groovyPost.tags.size()

        def bothPost = new Post(postContent: 'A groovy and grails post')
        user.addToPosts(bothPost)
        bothPost.addToTags(tagGrails)
        bothPost.addToTags(tagGroovy)
        assertEquals 2, bothPost.tags.size()

        def foundUser = MiniUser.get(user.id)
        def postNames = user.posts.collect{ it.postContent }
        assertEquals 2, postNames.size()

        foundUser.delete()
        assertFalse MiniUser.exists(foundUser.id)
        def deletedUserPosts = Post.findAllByUser(foundUser)
        assertTrue deletedUserPosts.size() == 0

    }
}
