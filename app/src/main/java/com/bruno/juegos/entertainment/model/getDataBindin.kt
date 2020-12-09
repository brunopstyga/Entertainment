package com.bruno.juegos.entertainment.model

class getDataBindin(var questions: Questions, jl: ArrayList<String>, check: Boolean)
{
    private val categories: Map<String, Boolean>? = null
    lateinit var arrayList : ArrayList<String>
    private var check : Boolean = false
        get() {
            TODO()
        }


    fun setProcurementType(questions: Questions, check: Boolean) {
        this.questions = questions
        this.check = check
    }

 fun setpepe(j: ArrayList<String>){
     this.arrayList = j
 }
}