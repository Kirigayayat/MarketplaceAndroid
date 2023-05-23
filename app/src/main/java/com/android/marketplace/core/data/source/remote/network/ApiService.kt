package com.android.marketplace.core.data.source.remote.network

import com.android.marketplace.core.data.source.model.*
import com.android.marketplace.core.data.source.remote.request.*
import com.android.marketplace.core.data.source.remote.response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("home")
    suspend fun getHome(): Response<BaseSingleResponse<Home>>

    @POST("login")
    suspend fun login(
        @Body login: LoginRequest,
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body data: RegisterRequest,
    ): Response<LoginResponse>

    @PUT("update-user/{id}")
    suspend fun updateUser(
        @Path("id") int: Int,
        @Body data: UpdateProfilRequest,
    ): Response<LoginResponse>

    @Multipart
    @POST("upload-user/{id}")
    suspend fun uploadUser(
        @Path("id") int: Int? = null,
        @Part data: MultipartBody.Part? = null
    ): Response<LoginResponse>

    @POST("toko")
    suspend fun createToko(
        @Body data: CreateTokoRequest
    ): Response<BaseSingleResponse<TokoResponse>>

    @GET("toko-user/{id}")
    suspend fun getUser(
        @Path("id") int: Int? = null
    ): Response<LoginResponse>

    @GET("alamat-toko/{id}")
    suspend fun getAlamatToko(
        @Path("id") idToko: Int? = null
    ): Response<BaseListResponse<AlamatToko>>

    @POST("alamat-toko")
    suspend fun createAlamatToko(
        @Body data: AlamatToko
    ): Response<BaseSingleResponse<AlamatToko>>

    @PUT("alamat-toko/{id}")
    suspend fun updateAlamatToko(
        @Path("id") id: Int? = null,
        @Body data: AlamatToko
    ): Response<BaseSingleResponse<AlamatToko>>

    @DELETE("alamat-toko/{id}")
    suspend fun deleteAlamatToko(
        @Path("id") id: Int? = null
    ): Response<BaseSingleResponse<AlamatToko>>

    @GET("produk/{id}")
    suspend fun getProduk(
        @Path("id") idToko: Int? = null
    ): Response<BaseListResponse<Product>>

    @POST("produk")
    suspend fun createProduk(
        @Body data: Product
    ): Response<BaseSingleResponse<Product>>

    @PUT("produk/{id}")
    suspend fun updateProduk(
        @Path("id") id: Int? = null,
        @Body data: Product
    ): Response<BaseSingleResponse<Product>>

    @DELETE("produk/{id}")
    suspend fun deleteProduk(
        @Path("id") id: Int? = null
    ): Response<BaseSingleResponse<Product>>

    @GET("produk-detail/{id}")
    suspend fun getOneProduk(
        @Path("id") id: Int? = null
    ): Response<BaseSingleResponse<Product>>

    @Multipart
    @POST("upload/product")
    suspend fun uploadProduk(
        @Part data: MultipartBody.Part? = null
    ): Response<BaseSingleResponse<String>>

    @GET("category")
    suspend fun getCategory(): Response<BaseListResponse<Category>>

    @POST("category")
    suspend fun createCategory(
        @Body data: KategoriRequest
    ): Response<BaseSingleResponse<Category>>

    @PUT("category/{id}")
    suspend fun updateCategory(
        @Path("id") id: Int? = null,
        @Body data: KategoriRequest
    ): Response<BaseSingleResponse<Category>>

    @DELETE("category/{id}")
    suspend fun deleteCategory(
        @Path("id") id: Int? = null
    ): Response<BaseSingleResponse<Category>>

    @GET("slider")
    suspend fun getSlider(): Response<BaseListResponse<Slider>>

    @POST("slider")
    suspend fun createSlider(
        @Body data: SliderRequest
    ): Response<BaseSingleResponse<Slider>>

    @PUT("slider/{id}")
    suspend fun updateSlider(
        @Path("id") id: Int? = null,
        @Body data: SliderRequest
    ): Response<BaseSingleResponse<Slider>>

    @DELETE("slider/{id}")
    suspend fun deleteSlider(
        @Path("id") id: Int? = null
    ): Response<BaseSingleResponse<Slider>>

    @Multipart
    @POST("upload/{path}")
    suspend fun uploadImage(
        @Path("path") id: String? = null,
        @Part data: MultipartBody.Part? = null
    ): Response<BaseSingleResponse<String>>

}