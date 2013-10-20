package com.grailsinaction

import com.grailsinaction.Profile

class User {
    
    String userId
    String password
    String homepage
    Date dateCreated
    Profile profile //1:1 relationship
    static hasMany = [ posts : Post ]
    

    static constraints = {
        userId(size:3..20, unique: false)
        password(size: 6..20, validator: { passwd, user -> return passwd != user.userId})
        homepage(url: true, nullable: true)
        profile(nullable: true)
    }
    
    static mapping = {
        profile lazy:false
    }
}
