package com.example.jetbee.data.remote.repositoryImpl

import android.util.Log
import com.example.jetbee.domain.model.AuthUser
import com.example.jetbee.domain.model.CartProducts
import com.example.jetbee.domain.model.Product
import com.example.jetbee.domain.repository.FirebaseRepository
import com.example.jetbee.presentaion.order_screen.Order
import com.example.jetbee.util.Constant.CART_PRODUCTS_FIELD
import com.example.jetbee.util.Constant.QUANTITY
import com.example.jetbee.util.Constant.USER_COLLECTION
import com.example.jetbee.util.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val firebaseRealTimeDatabase: FirebaseDatabase
) : FirebaseRepository {
    override fun firebaseSignIn(user: AuthUser): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading())
        firebaseAuth.signInWithEmailAndPassword(user.email, user.password).addOnSuccessListener {
            trySend(Resource.Success("Login Successful"))
        }.addOnFailureListener {
            trySend(Resource.Error(it.message.toString()))
        }
        awaitClose {
            close()
        }
    }

    override fun firebaseSignUp(user: AuthUser): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading())
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                val userUid = it.user?.uid
                fireStore.collection(USER_COLLECTION).document(userUid!!).set(user)
                trySend(Resource.Success("Signup Successful"))
            }.addOnFailureListener {
                trySend(Resource.Error(it.message.toString()))
            }
        awaitClose {
            close()
        }
    }

    override suspend fun getAllCoffee(): Resource<List<Product>> {
        val result: List<Product>
        return try {
            result = fireStore.collection("products").get().await().map {
                it.toObject(Product::class.java)
            }
            Resource.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Resource.Error(e.message.toString())
        }
    }

    override fun addCoffeeToCart(
        cartProduct: CartProducts,
        userId: String
    ): Flow<Resource<Task<Void>>> {
        return flow {
            emit(Resource.Loading())
            val result = fireStore.collection("user_collection").document(userId)
                .update("cartProducts", FieldValue.arrayUnion(cartProduct))
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun deleteCoffeeFromCart(
        userId: String,
        cartProduct: CartProducts
    ): Resource<Task<Void>> {
        return try {
            val result = fireStore.collection(USER_COLLECTION).document(userId)
                .update(CART_PRODUCTS_FIELD, FieldValue.arrayRemove(cartProduct))
            Resource.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Resource.Error(e.message.toString())

        }
    }

    override suspend fun getUserData(userId: String): AuthUser? {
        val docRef = fireStore.collection(USER_COLLECTION).document(userId)
        return docRef.get().addOnSuccessListener { document ->
            document.toObject(AuthUser::class.java)
        }.addOnFailureListener {
            it.toString()
        }.await().toObject(AuthUser::class.java)
    }


    override fun currentUserExist(): Flow<Boolean> {
        return flow {
            emit(firebaseAuth.currentUser != null)
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override suspend fun addOrders(
        orderList: MutableList<Order>,
        onSuccess: () -> Unit,
        onFailure: (String?) -> Unit
    ) {
        val collectionRef = fireStore.collection("orders")
        val rootRef = firebaseRealTimeDatabase.reference
        val counRef = rootRef.child("counters").child("orders").child("count")

        fireStore.runBatch {
            for (order in orderList) {
                val documentRef = collectionRef.document()
                it.set(documentRef, order)
            }
        }.addOnSuccessListener {
            onSuccess()
            counRef.runTransaction(object : Transaction.Handler {
                override fun doTransaction(currentData: MutableData): Transaction.Result {
                    val count = currentData.getValue(Int::class.java) ?: return Transaction.success(
                        currentData
                    )
                    currentData.value = count + orderList.size
                    return Transaction.success(currentData)
                }

                override fun onComplete(
                    error: DatabaseError?,
                    committed: Boolean,
                    currentData: DataSnapshot?
                ) {
                    Log.d("Completeddd", "onComplete: ${error?.message}")
                }
            })
        }.addOnFailureListener {
            Log.d("failed to add order", "addOrders: ")
        }
            .await()
    }

    override fun increaseProductQuantity(documentId: String): Task<com.google.firebase.firestore.Transaction> {
        val document = userCartCollection!!.document(documentId)
        return Firebase.firestore.runTransaction { transaction ->
            val productBefore = transaction.get(document)
            var quantity = productBefore.getLong(QUANTITY)
            quantity = quantity!! + 1
            transaction.update(document, QUANTITY, quantity)
        }
    }

    override fun userLogOut() {
        return firebaseAuth.signOut()
    }



    private val userCartCollection = currentUser()?.uid?.let {
        fireStore.collection(USER_COLLECTION)
    }
   }
