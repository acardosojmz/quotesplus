package com.example.quoteplus.data.remote

import com.example.quoteplus.data.model.LoginRequest
import com.example.quoteplus.data.model.QuoteResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuoteService {
    @GET("quotes")
    suspend fun getQuotes(): QuoteResponse
}