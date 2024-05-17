package com.example.quoteplus.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.quoteplus.presentation.ui.composables.CustomAppBar

@Composable
fun ListQuotesScreen(drawerState: DrawerState){
    Scaffold(
        topBar = { CustomAppBar(
            drawerState = drawerState,
            title = "List Quotes",
            backgroundColor = Color.Green
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "List quotes")
        }
    }
}