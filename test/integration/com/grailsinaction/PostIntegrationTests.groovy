package com.grailsinaction

import static org.junit.Assert.*
import org.junit.*

class PostIntegrationTests {

   void testFirstPost() {
       def user = new User(userId: 'Marty', password: 'ItisApassword', homepage:'https://www.google.com').save()
       
       def post1 = new Post(content: 'First post...')
       user.addToPosts(post1)       
       def post2 = new Post(content: 'Second post...')
       user.addToPosts(post2)       
       def post3 = new Post(content: 'Third post...')
       user.addToPosts(post3)
       
       assertEquals 3, user.posts.size()
       
       user.delete()
       
   }
   
    void testAccessingPosts() {
       def user = new User(userId: 'Marty', password: 'ItisApassword', homepage:'https://www.google.com').save()
       
       def post1 = new Post(content: 'First')
       user.addToPosts(post1)       
       def post2 = new Post(content: 'Second')
       user.addToPosts(post2)       
       def post3 = new Post(content: 'Third')
       user.addToPosts(post3)
       
       def postNames = user.posts.collect{ it.content }
       assertEquals (['First','Second','Third'], postNames.sort())
       user.delete()
       
    }
    
    void testPostWithTags() {
        
        def user = new User(userId: 'HenryTheThird', password:'r32r2rbhb').save()
        
        def tagGroovy = new Tag(name: 'Groovy')
        def tagGrails = new Tag(name: 'Grails')
        user.addToTags(tagGroovy)
        user.addToTags(tagGrails)
        
        def tagNames = user.tags*.name
        assertEquals(['Grails','Groovy'] , tagNames.sort())
        
        def groovyPost = new Post(content:'A groovy post')
        user.addToPosts(groovyPost)
        groovyPost.addToTags(tagGroovy)
        assertEquals 1, groovyPost.tags.size()

        def bothPost = new Post(content: 'A groovy and grails post')
        user.addToPosts(bothPost)
        bothPost.addToTags(tagGrails)
        bothPost.addToTags(tagGroovy)
        assertEquals 2, bothPost.tags.size()
        user.delete()
    }
}
