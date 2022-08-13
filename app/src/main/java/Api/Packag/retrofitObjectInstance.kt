package Api.Packag

import Utility.Packag.ConstantUrl.Companion.Base_Url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitObjectInstance {
    private val retrofitObject by lazy {
        Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(
            GsonConverterFactory.create()).build()
    }
    val ApiConnect:UserApiInterface = retrofitObject.create(UserApiInterface::class.java)
}