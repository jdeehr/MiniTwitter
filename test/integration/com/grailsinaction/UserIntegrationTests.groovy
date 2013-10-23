package com.grailsinaction

import org.codehaus.groovy.grails.orm.hibernate.HibernateSession

import static com.grailsinaction.User.*
import static org.junit.Assert.*
import org.junit.*
import grails.test.*
import com.grailsinaction.User

class UserIntegrationTests {

    
    void testSaveAndUpdate() {
        def user = new User(userId: 'Joelman', password: 'secrets', homepage:'https://www.google.com')
        assertNotNull user.save()
        
        def foundUser = get(user.id)
        foundUser.password = 'sesameseed'
        foundUser.save()
        
        def editedUser = get(user.id)
        assertEquals 'sesameseed', editedUser.password
        editedUser.delete()
        assertFalse exists(editedUser.id)
        assertFalse exists(foundUser.id)
    }   
    
    void testSaveFirstUser() {
        def user3 = new User(userId: 'Joellman', password: 'secrets', homepage:'https://www.google.com')
        assertNotNull user3.save()
        assertNotNull user3.id
        
        def foundUser = get(user3.id)
        assertEquals 'Joellman', foundUser.userId      
        foundUser.delete()
        assertFalse exists(foundUser.id)
    }
  
    
    void testSaveThenDelete() {
        def user3 = new User(userId: 'Joelman', password: 'migsIsHott', homepage:'https://www.google.com')
        assertNotNull user3.save()
        
        def foundUser = get(user3.id)
        foundUser.delete()
        
        assertFalse exists(foundUser.id)
    } 
    
    void testBadSave() {
        def user = new User(userId:'chuck norris', password: 'tiny', homepage: 'bad_url')
        assertFalse user.validate()
        assertTrue user.hasErrors()
        
        def errors = user.errors
        assertEquals "size.toosmall", errors.getFieldError("password").code
        assertEquals "tiny", errors.getFieldError("password").rejectedValue
        
        assertEquals "url.invalid", errors.getFieldError("homepage").code
        assertEquals "bad_url", errors.getFieldError("homepage").rejectedValue
        
        assertNull errors.getFieldError("userId")

    }
    
    void testBadSaveCorrected() {
        def user = new User(userId:'chuck norris', password: 'tiny', homepage: 'bad_url')

        assertFalse(user.validate())
        assertTrue(user.hasErrors())
        assertNull(user.save())
        
        user.password = "theironfist"
        user.homepage = "https://www.google.com"
        assertTrue(user.validate())
        assertFalse(user.hasErrors())
        assertNotNull user.save()
        assert 1, User.list().size()
    }

    void testForFollowing() {

        def glen = new User (userId:'glenDude', password: 'cvdsiuhfg843')

        def steve = new User (userId:'stevenDude', password: 'fvf082243')

        def harry = new User (userId:'harryThePerson', password: '234iudwsdg')

        harry.addToFollowing(steve)

        assertEquals 1, harry.following.size()


    }

    
}
