package com.grailsinaction

import static org.junit.Assert.*
import org.junit.*
import grails.test.*
import com.grailsinaction.User

class UserIntegrationTests {
    
    void testSaveAndUpdate() {
        def user = new User(userId: 'Joelman', password: 'secrets', homepage:'https://www.google.com')
        assertNotNull user.save()
        
        def foundUser = User.get(user.id)
        foundUser.password = 'sesameseed'
        foundUser.save()
        
        def editedUser = User.get(user.id)
        assertEquals 'sesameseed', editedUser.password
        editedUser.delete()
        assertFalse User.exists(editedUser.id)
        assertFalse User.exists(foundUser.id)
    }   
    
    void testSaveFirstUser() {
        def user3 = new User(userId: 'Joellman', password: 'secrets', homepage:'https://www.google.com')
        assertNotNull user3.save()
        assertNotNull user3.id
        
        def foundUser = User.get(user3.id)
        assertEquals 'Joellman', foundUser.userId      
        foundUser.delete()
        assertFalse User.exists(foundUser.id)
    }
  
    
    void testSaveThenDelete() {
        def user3 = new User(userId: 'Joelman', password: 'migsIsHott', homepage:'https://www.google.com')
        assertNotNull user3.save()
        
        def foundUser = User.get(user3.id)
        foundUser.delete()
        
        assertFalse User.exists(foundUser.id)
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
        user.delete()        
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
        user.delete()
        
    }
    
}
