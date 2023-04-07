package my.tarc.mycontact

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = arrayOf(Contact::class), version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object{
        //Singleton prevents multiple instances of database opening at the same time
        @Volatile
        // volatile - can be destroyed as it stores in memory
        private var INSTANCE: ContactDatabase? = null

        // Context = application context =  application level (every component in this app can see)
        fun getDatabase(context: Context) : ContactDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            // synchronized = Do it now... I wait for you
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contact_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}