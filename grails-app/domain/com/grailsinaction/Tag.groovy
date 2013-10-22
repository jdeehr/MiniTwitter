package com.grailsinaction

class Tag {
    
    String name
    String user

    static constraints = {
        name(blank:false)
    }
    
    static hasMany = [ posts : Post ]
    static belongsTo = [ user : User ] // the belongsTo closure says that tags can only be added from the User side, and not the opposite. 
}
