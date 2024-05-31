package com.example.quoteplus.data.remote

import android.content.Context
import android.util.Log
import com.example.quoteplus.data.local.DataStoreManager
import com.example.quoteplus.data.model.QuoteResponse
import com.example.quoteplus.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val context: Context,
    private val quoteDataSource: QuoteRemoteDataSource): QuoteRepository {
    override suspend fun  getQuotes(): Flow<QuoteResponse> {
        val token = DataStoreManager.getToken(context).first()
        return quoteDataSource.getQuotes("Bearer $token")
    }
}

