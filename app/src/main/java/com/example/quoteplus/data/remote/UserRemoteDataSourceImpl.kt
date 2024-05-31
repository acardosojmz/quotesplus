package com.example.quoteplus.data.remote

import android.content.Context
import android.util.Log
import com.example.quoteplus.data.local.DataStoreManager
import com.example.quoteplus.data.model.LoginRequest
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSourceImpl @Inject constructor(
    private val context: Context,
    private val userService: UserService): UserRemoteDataSource {
    override suspend fun login(loginRequest: LoginRequest): Flow<UserLoginResponse> {
        try{
            val responseApi: JsonObject =  userService.login(loginRequest)
            val userLoginResponse = Gson().fromJson(responseApi, UserLoginResponse::class.java)
            DataStoreManager.saveTokenToDataStore(context, userLoginResponse.data)
            return flow{emit(userLoginResponse)}
        } catch (ex: Exception){
            Log.e("ERROR:", ex.message.toString())
            val userLoginResponse = UserLoginResponse(
                success = false,
                message = ex.message.toString(),
                data="")
            return flow{emit(userLoginResponse)}
        }



    }

}