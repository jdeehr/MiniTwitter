package com.grailsinaction

class Tag {
    
    String name
    String user

    static constraints = {
        name(blank:false)
    }
    
    static hasMany = [ posts : Post ]
    static belongsTo = [ User, Post ] // the belongsTo closure says that tags can only be added from the User and Post side, and not the opposite. 
    //IE you could call Post.addToTags() or User.addToTags(), but not Tag.addToPost()
}
