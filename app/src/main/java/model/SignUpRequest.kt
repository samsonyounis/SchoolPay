package model

data class SignUpRequest(val firstName:String, val lastName:String, val userName:String,
                         val email:String, val IdNumber:String, val contactNumber:String, val password:String)
