package com.example.myapp015asharedtasklist


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp015asharedtasklist.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tasks = mutableListOf<Task>() // Lokální seznam úkolů
    private lateinit var taskAdapter: TaskAdapter

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializace Firebase
        FirebaseApp.initializeApp(this)
        println("Firebase initialized successfully")

        firestore = FirebaseFirestore.getInstance()

        // Nastavení logiky pro FloatingActionButton
        binding.fabAddTask.setOnClickListener {
            showAddTaskDialog()
        }

        // Inicializace RecyclerView
        taskAdapter = TaskAdapter(tasks) { task ->
            updateTask(task) // Callback pro změnu úkolu
        }
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTasks.adapter = taskAdapter

        listenToTaskUpdates()

        // Simulace načtení dat
        // loadTasks()

        // Načtení úkolů z firestore db
        loadTasksFromFirestore()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadTasks() {
        tasks.add(Task("1", "Buy groceries", isCompleted = false, assignedTo = "Alice"))
        tasks.add(Task("2", "Clean the house", isCompleted = false, assignedTo = ""))
        tasks.add(Task("3", "Prepare presentation", isCompleted = true, assignedTo = "Bob"))
        taskAdapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadTasksFromFirestore() {
        firestore.collection("tasks").get()
            .addOnSuccessListener { result ->
                tasks.clear()
                for (document in result) {
                    val task = document.toObject(Task::class.java)
                    tasks.add(task)
                }
                taskAdapter.notifyDataSetChanged()
                println("Tasks loaded from Firestore")
            }
            .addOnFailureListener { e ->
                println("Error loading tasks: ${e.message}")
            }
    }


    private fun updateTask(task: Task) {
        firestore.collection("tasks").document(task.id).set(task)
            .addOnSuccessListener {
                println("Task updated in Firestore: ${task.name}, completed: ${task.isCompleted}")
            }
            .addOnFailureListener { e ->
                println("Error updating task: ${e.message}")
            }
    }


    private fun showAddTaskDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Task")

        // Kontejner pro vstupní pole
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(32, 16, 32, 16)

        val input1 = EditText(this)
        input1.hint = "Task name"
        input1.inputType = InputType.TYPE_CLASS_TEXT
        layout.addView(input1)

        val input2 = EditText(this)
        input2.hint = "Who will do it"
        input2.inputType = InputType.TYPE_CLASS_TEXT
        layout.addView(input2)

        builder.setView(layout)
        // Tlačítka dialogu
        builder.setPositiveButton("Add") { _, _ ->
            val taskName = input1.text.toString()
            val taskWho = input2.text.toString()
            if (taskName.isNotBlank()) {
                addTask(taskName, taskWho)
            } else {
                Toast.makeText(this, "Task name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun addTask(name: String, assignedTo: String = "") {
        val newTask = Task(
            id = firestore.collection("tasks").document().id, // Vygenerujeme ID
            name = name,
            isCompleted = false,
            assignedTo = assignedTo
        )

        // Uložíme úkol do Firestore
        firestore.collection("tasks").document(newTask.id).set(newTask)
            .addOnSuccessListener {
//                tasks.add(newTask)
//                taskAdapter.notifyItemInserted(tasks.size - 1)
                println("Task added to Firestore: $name")
            }
            .addOnFailureListener { e ->
                println("Error adding task: ${e.message}")
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listenToTaskUpdates() {
        firestore.collection("tasks").addSnapshotListener { snapshots, e ->
            if (e != null) {
                println("Listen failed: ${e.message}")
                return@addSnapshotListener
            }

            tasks.clear()
            for (document in snapshots!!) {
                val task = document.toObject(Task::class.java)
                tasks.add(task)
            }
            taskAdapter.notifyDataSetChanged()
        }
    }

}