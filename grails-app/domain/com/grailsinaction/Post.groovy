package com.grailsinaction

class Post {
    
    String content
    Date dateCreated

    static constraints = {
        content(blank: false)
    }
    
    static belongsTo = [user : User]  //bi-directional link
    
    static mapping = {
        sort dateCreated:"desc"
    }
    
}
