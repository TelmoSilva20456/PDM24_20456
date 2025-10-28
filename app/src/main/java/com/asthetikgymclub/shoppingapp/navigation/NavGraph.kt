package com.asthetikgymclub.shoppingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asthetikgymclub.shoppingapp.ui.screens.auth.LoginScreen
import com.asthetikgymclub.shoppingapp.ui.screens.auth.OnboardingScreen
import com.asthetikgymclub.shoppingapp.ui.screens.auth.SignupScreen
import com.asthetikgymclub.shoppingapp.ui.screens.cart.CartScreen
import com.asthetikgymclub.shoppingapp.ui.screens.checkout.CheckoutScreen
import com.asthetikgymclub.shoppingapp.ui.screens.home.HomeScreen
import com.asthetikgymclub.shoppingapp.ui.screens.about.AboutScreen
import com.asthetikgymclub.shoppingapp.ui.screens.favorites.FavoritesScreen
import com.asthetikgymclub.shoppingapp.ui.screens.orders.OrdersScreen
import com.asthetikgymclub.shoppingapp.ui.screens.productdetails.ProductDetailsScreen
import com.asthetikgymclub.shoppingapp.ui.screens.profile.ProfileScreen
import com.asthetikgymclub.shoppingapp.ui.screens.settings.SettingsScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(Screen.Onboarding.route) { OnboardingScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Signup.route) { SignupScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.ProductDetails.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            if (productId != null) {
                ProductDetailsScreen(productId = productId, navController = navController)
            }
        }
        composable(Screen.Cart.route) { CartScreen(navController) }
        composable(Screen.Checkout.route) { CheckoutScreen() }
        composable(Screen.Orders.route) { OrdersScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.Favorites.route) { FavoritesScreen() }
        composable(Screen.Settings.route) { SettingsScreen(navController) }
        composable(Screen.About.route) { AboutScreen() }
    }
}
