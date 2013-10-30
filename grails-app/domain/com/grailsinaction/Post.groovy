package com.grailsinaction

class Post {
    
    String postContent
    Date dateCreated



    static constraints = {
        postContent(blank: false)
    }
    
    static belongsTo = [user : MiniUser]  //bi-directional link
    static hasMany = [ tags : Tag ]
    
    static mapping = {
        sort dateCreated:"desc"
    }
    
}
