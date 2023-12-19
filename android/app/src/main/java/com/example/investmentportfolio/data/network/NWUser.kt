package com.example.investmentportfolio.data.network

data class NWUserLoginRequest(
    val username: String?,
    val password: String?,
)

data class NWUserRegisterResponse(
    val message: String?,
    val code: Int,
//    "message": "Register Successful",
//"code": 200
)

data class UserLoginResponse(
    val data: UserLoginData?,
    val status: UserLoginStatus?
)

data class UserLoginData(
    val token: String?,
    val userId: Int?
)

data class UserLoginStatus(
    val message: String?,
    val code: Int
)