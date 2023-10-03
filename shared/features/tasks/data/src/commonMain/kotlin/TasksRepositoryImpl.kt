import dev.gitlive.firebase.firestore.FirebaseFirestore

class TasksRepositoryImpl
constructor(private val db: FirebaseFirestore) : TasksRepository