package model

data class TransactionHistory(private val paymentMethod:String,val AccountNumber:String,
                              val time:String, val amount:String)
