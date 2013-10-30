package com.grailsinaction

class Profile {
    
    static belongsTo = MiniUser
            
        byte[] photo
        
        String fullName
        String bio
        String homepage
        String email
        String timezone
        String country
        String jabberAddress

    String toString() {
        "Profile for ${fullName} (${id})"
    }
    static constraints = {
        
        fullName(nullable: true) //We dont mind if the user passes in a full name or not.
        bio(nullable: true, maxsize: 1000)
        homepage(url: true, nullable: true)
        email(email: true, nullable: true)
        photo(nullable: true)
        country(nullable: true)
        timezone(nullable: true)
        jabberAddress(email: true, nullable: true)       
        
    }
}
