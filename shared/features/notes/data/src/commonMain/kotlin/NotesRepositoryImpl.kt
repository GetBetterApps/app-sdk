import dev.gitlive.firebase.firestore.FirebaseFirestore

class NotesRepositoryImpl
constructor(private val db: FirebaseFirestore) : NotesRepository