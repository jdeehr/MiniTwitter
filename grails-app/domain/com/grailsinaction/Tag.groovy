package com.grailsinaction

class Tag {
    
    String name
    MiniUser user

    static constraints = {
        name(blank:false)
    }
    
    static hasMany = [ posts : Post ]
    static belongsTo = [ MiniUser, Post ] // the belongsTo closure says that tags can only be added from the MiniUser and Post side, and not the opposite.
    //IE you could call Post.addToTags() or MiniUser.addToTags(), but not Tag.addToPost()
}
