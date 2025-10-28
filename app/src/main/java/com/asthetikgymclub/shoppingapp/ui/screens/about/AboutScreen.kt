package com.asthetikgymclub.shoppingapp.ui.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Asthetik Gym Club")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Asthetik Gym Club is a premium activewear and lifestyle clothing brand. We are dedicated to providing high-quality, stylish, and comfortable clothing for the modern athlete.")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Follow us on social media:")
        Text(text = "Instagram: @asthetikgymclub")
        Text(text = "Twitter: @asthetikgymclub")
        Text(text = "Facebook: /asthetikgymclub")
    }
}
