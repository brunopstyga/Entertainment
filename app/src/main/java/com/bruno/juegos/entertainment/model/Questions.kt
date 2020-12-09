package com.bruno.juegos.entertainment.model

enum class Questions(i: Int) {
    FIRTQUESTIONS(1),
    SECONDQUESTIONS(2),
    THIRDQUESTIONS(3),
    FOURTHQUESTIONS(4);

    companion object{
        fun create(x : Int) : Questions {
            return  when (x) {
                1 -> FIRTQUESTIONS
                2 -> SECONDQUESTIONS
                3 -> THIRDQUESTIONS
                4 -> FOURTHQUESTIONS
                else -> throw IllegalStateException()
            }
        }
    }
}