package com.example.jetbee.di

import android.app.Application
import android.content.Context
import com.example.jetbee.R
import com.example.jetbee.data.remote.repositoryImpl.FirebaseRepositoryImpl
import com.example.jetbee.data.remote.repositoryImpl.GoogleSignInRepositoryImpl
import com.example.jetbee.domain.repository.FirebaseRepository
import com.example.jetbee.domain.repository.GoogleSignInRepository
import com.example.jetbee.presentaion.order_screen.OrderUseCase
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseRealtimeDatabase(
        application: Application
    ): FirebaseDatabase {
        return FirebaseDatabase.getInstance("https://jetbee-5f678-default-rtdb.asia-southeast1.firebasedatabase.app/")
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthentication() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFireStore() = Firebase.firestore


    @Provides
    @Singleton
    fun providesFirebaseRepository(
        firestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth, firebaseDatabase: FirebaseDatabase
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(
            fireStore = firestore,
            firebaseAuth = firebaseAuth,
            firebaseRealTimeDatabase = firebaseDatabase
        )
    }

    @Singleton
    @Provides
    fun provideAddOrdersUseCase(
        ordersRepository: FirebaseRepository,
        application: Application,
    ): OrderUseCase {
        return OrderUseCase(
            application = application, firebaseRepository = ordersRepository
        )
    }

    @Provides
    fun provideOneTapClient(
        @ApplicationContext context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named("sign_in_request")
    fun provideSignInRequest(app: Application) =
        BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
                .setServerClientId(
                    app.getString(
                        R.string.serverclietid
                    )
                ).setFilterByAuthorizedAccounts(true).build()
        ).setAutoSelectEnabled(true).build()

    @Provides
    @Named("sign_up_request")
    fun provideSignUpRequest(app: Application) =
        BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
                .setServerClientId(
                    app.getString(
                        R.string.serverclietid
                    )
                ).setFilterByAuthorizedAccounts(false).build()
        ).build()

    @Provides
    @Singleton
    fun providesGoogleSignInRepository(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        @Named("sign_in_request")
        signInRequest: BeginSignInRequest,
        @Named("sign_up_request")
        signUpRequest: BeginSignInRequest,
        db: FirebaseFirestore

    ): GoogleSignInRepository {
        return GoogleSignInRepositoryImpl(
            firestore = db,
            auth = auth,
            oneTapClient = oneTapClient,
            signInRequest = signInRequest,
            signUpRequest = signUpRequest

        )
    }


}



