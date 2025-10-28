package com.asthetikgymclub.shoppingapp.di

import com.asthetikgymclub.shoppingapp.data.AuthRepository
import com.asthetikgymclub.shoppingapp.data.AuthRepositoryImpl
import com.asthetikgymclub.shoppingapp.data.AuthRepository
import com.asthetikgymclub.shoppingapp.data.AuthRepositoryImpl
import com.asthetikgymclub.shoppingapp.data.ProductRepository
import com.asthetikgymclub.shoppingapp.data.ProductRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideProductRepository(firestore: FirebaseFirestore): ProductRepository = ProductRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideCartRepository(firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth): CartRepository = CartRepositoryImpl(firestore, firebaseAuth)

    @Provides
    @Singleton
    fun provideOrderRepository(firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth): OrderRepository = OrderRepositoryImpl(firestore, firebaseAuth)

    @Provides
    @Singleton
    fun provideFavoritesRepository(firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth): FavoritesRepository = FavoritesRepositoryImpl(firestore, firebaseAuth)

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: android.content.Context): androidx.work.WorkManager = androidx.work.WorkManager.getInstance(context)

    @Provides
    @Singleton
    fun provideSettingsRepository(@ApplicationContext context: android.content.Context): com.asthetikgymclub.shoppingapp.data.settings.SettingsRepository = com.asthetikgymclub.shoppingapp.data.settings.SettingsRepositoryImpl(context)
}
