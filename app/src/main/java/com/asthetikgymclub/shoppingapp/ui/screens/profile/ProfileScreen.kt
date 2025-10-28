package com.asthetikgymclub.shoppingapp.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asthetikgymclub.shoppingapp.ui.screens.profile.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val userState = viewModel.user.collectAsState()

    userState.value?.let { user ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${user.displayName}")
            Text(text = "Email: ${user.email}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* TODO: Edit profile */ }) {
                Text(text = "Edit Profile")
            }
            Button(onClick = { viewModel.logout() }) {
                Text(text = "Logout")
            }
        }
    }
}
