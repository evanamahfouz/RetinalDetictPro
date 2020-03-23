package com.example.ratinadeticpro.data.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [UserEntity::class, PredictImgEntity::class, WhatToDoEntity::class],
    version = 2

)
abstract class RetinaDetectDataBase : RoomDatabase() {
    abstract fun userDOA(): UserDOA
    abstract fun predictImgDOA(): PredictImgDOA
    abstract fun whatToDoDOA(): WhatToDoDOA


}



