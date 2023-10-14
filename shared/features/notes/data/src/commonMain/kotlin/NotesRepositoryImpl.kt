import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore

class NotesRepositoryImpl
constructor(@Suppress("unused") private val db: FirebaseFirestore) : NotesRepository