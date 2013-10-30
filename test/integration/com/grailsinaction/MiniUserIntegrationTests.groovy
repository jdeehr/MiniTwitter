package com.grailsinaction

import static MiniUser.*
import static org.junit.Assert.*

class MiniUserIntegrationTests {


    void testSaveAndUpdate() {
        def user = new MiniUser(userId: 'Joelman', password: 'secrets')
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
        def user3 = new MiniUser(userId: 'Joellman', password: 'Joellmangft4t')
        assertNotNull user3.save()
        assertNotNull user3.id
        
        def foundUser = get(user3.id)
        assertEquals 'Joellman', foundUser.userId      
        foundUser.delete()
        assertFalse exists(foundUser.id)
    }
  
    
    void testSaveThenDelete() {
        def user3 = new MiniUser(userId: 'Joelman', password: 'migsIsHott')
        assertNotNull user3.save()
        
        def foundUser = get(user3.id)
        foundUser.delete()
        
        assertFalse exists(foundUser.id)
    } 
    
    void testBadSave() {
        def user = new MiniUser(userId:'chuck norris', password: 'tiny')
        assertFalse user.validate()
        assertTrue user.hasErrors()
        
        def errors = user.errors
        assertEquals "size.toosmall", errors.getFieldError("password").code
        assertEquals "tiny", errors.getFieldError("password").rejectedValue
        


    }
    
    void testBadSaveCorrected() {
        def user = new MiniUser(userId:'chuck norris', password: 'tiny')

        assertFalse(user.validate())
        assertTrue(user.hasErrors())
        assertNull(user.save())
        
        user.password = "theironfist"

        assertTrue(user.validate())
        assertFalse(user.hasErrors())
        assertNotNull user.save()
        assert 1, MiniUser.list().size()
    }

    void testForFollowing() {

        def glen = new MiniUser (userId:'glenDude', password: 'cvdsiuhfg843')

        def steve = new MiniUser (userId:'stevenDude', password: 'fvf082243')

        def harry = new MiniUser (userId:'harryThePerson', password: '234iudwsdg')

        harry.addToFollowing(steve)

        assertEquals 1, harry.following.size()

    }


    
}
