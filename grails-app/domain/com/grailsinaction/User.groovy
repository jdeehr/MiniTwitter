package com.grailsinaction

class User {
    
    String userId
    String password
    String homepage
    Date dateCreated
    Profile profile //1:1 relationship
    static hasMany = [ posts : Post , tags : Tag , following : User ] //one to many relationship
    

    static constraints = {
        userId(size:3..20, unique: false)
        password(size: 6..20, validator: { passwd, user -> return passwd != user.userId})
        homepage(url: true, nullable: true)
        profile(nullable: true)
    }
    
    static mapping = {
        profile lazy:false
        posts sort:'dateCreated'
    }
}
