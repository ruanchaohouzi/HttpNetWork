package com.shuwen.network

class NetWork{

    companion object{
        private var mUserId: String? = null

        fun setUserId(userId: String){
            mUserId = userId
        }

        fun getUserId(): String {
            if(mUserId == null){
                throw KotlinNullPointerException( "you shold init NetWork")
            }
            return mUserId!!
        }
    }
}