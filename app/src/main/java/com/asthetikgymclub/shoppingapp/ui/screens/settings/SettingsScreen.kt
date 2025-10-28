package com.asthetikgymclub.shoppingapp.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.asthetikgymclub.shoppingapp.navigation.Screen
import com.asthetikgymclub.shoppingapp.ui.screens.settings.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val isDarkMode = viewModel.isDarkMode.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            Text(text = "Dark Mode")
            Switch(
                checked = isDarkMode.value,
                onCheckedChange = { viewModel.setDarkMode(it) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Screen.About.route) }) {
            Text(text = "About")
        }
    }
}
