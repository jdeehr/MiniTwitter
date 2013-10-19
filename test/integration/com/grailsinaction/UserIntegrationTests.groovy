package com.grailsinaction

import static org.junit.Assert.*
import org.junit.*
import grails.test.*
import com.grailsinaction.User

class UserIntegrationTests {
    
    void testSaveAndUpdate() {
        def user = new User(userId: 'Joe', password: 'secret', homepage:'www.google.com')
        assertNotNull user.save()
        
        def foundUser = User.get(user.id)
        foundUser.password = 'sesame'
        foundUser.save()
        
        def editedUser = User.get(user.id)
        assertEquals 'sesame', editedUser.password
        editedUser.delete()
        assertFalse User.exists(editedUser.id)
        assertFalse User.exists(foundUser.id)
    }   
    
    void testSaveFirstUser() {
        def user3 = new User(userId: 'Joell', password: 'secret', homepage:'www.google.com')
        assertNotNull user3.save()
        assertNotNull user3.id
        
        def foundUser = User.get(user3.id)
        assertEquals 'Joell', foundUser.userId      
        foundUser.delete()
        assertFalse User.exists(foundUser.id)
    }
  
    
    void testSaveThenDelete() {
        def user3 = new User(userId: 'Joe', password: 'migs', homepage:'www.google.com')
        assertNotNull user3.save()
        
        def foundUser = User.get(user3.id)
        foundUser.delete()
        
        assertFalse User.exists(foundUser.id)
    } 
    
}
