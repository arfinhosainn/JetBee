package com.example.jetbee.domain.repository

import com.example.jetbee.domain.model.AuthUser
import com.example.jetbee.domain.model.CartProducts
import com.example.jetbee.domain.model.Product
import com.example.jetbee.presentaion.order_screen.Order
import com.example.jetbee.util.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.Transaction
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    fun firebaseSignIn(user: AuthUser): Flow<Resource<String>>

    fun firebaseSignUp(user: AuthUser): Flow<Resource<String>>

    suspend fun getAllCoffee(): Resource<List<Product>>

    fun addCoffeeToCart(cartProduct: CartProducts, userId: String): Flow<Resource<Task<Void>>>

    fun deleteCoffeeFromCart(userId: String, cartProduct: CartProducts): Resource<Task<Void>>

    suspend fun getUserData(userId: String): AuthUser?

    fun currentUserExist(): Flow<Boolean>

    fun signOut()

    fun currentUser(): FirebaseUser?

    suspend fun addOrders(
        orderList: MutableList<Order>, onSuccess: () -> Unit,
        onFailure: (String?) -> Unit,
    )

    fun increaseProductQuantity(documentId: String): Task<Transaction>

    fun userLogOut()


}