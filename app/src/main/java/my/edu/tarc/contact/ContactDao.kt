package my.tarc.mycontact

import androidx.lifecycle.LiveData
import androidx.room.*

// Provides interface of databases functions
// C-create R- read U-update D-delete
@Dao
interface ContactDao {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    // LiveData - changes to the record can be notified to system
    fun getAllContact(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // suspend fun - execute it in a separate thread (e.g. I/O, Network)
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)
}