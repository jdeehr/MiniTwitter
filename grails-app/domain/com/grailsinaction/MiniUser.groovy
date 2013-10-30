package com.grailsinaction

class MiniUser {
    
    String userId
    String password
    Date dateCreated
    Profile profile //1:1 relationship
    static hasMany = [ posts : Post , tags : Tag , following : MiniUser ] //one to many relationship

    String toString() {
        "User: ${userId}"
    }
    

    static constraints = {
        userId(size:3..20, unique: false, nullable: false)
        password(size: 6..25, nullable: false, validator: { passwd, userzz -> return passwd != userzz.userId})  //Dont be confused here by the naming, the first param in the closure
                                                                                               //is the value that was placed in the field, the second references the
                                                                                               //instance of the domain class itself.
        dateCreated()
        profile(nullable: true)
    }
    
    static mapping = {
        profile lazy:false
        posts sort:'dateCreated'
    }
}
