import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import remote.AreasRemoteDataSource

class AreasRepositoryImpl
constructor(
    private val remoteDataSource: AreasRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : AreasRepository