package com.smkth.app3_recycleview

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smkth.app3_recycleview.adapter.StudentAdapter
import com.smkth.app3_recycleview.model.Student
import com.smkth.app3_recycleview.utils.DummyData


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val studentList = mutableListOf<Student>()
    private lateinit var searchView: SearchView
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        btnAdd = findViewById(R.id.btnAdd)

        // Dummy data awal
        studentList.addAll(DummyData.getStudentList())

        adapter = StudentAdapter(
            context = this,
            studentList = studentList,
            onEdit = { student, position ->
                showEditStudentDialog(student, position)
            },
            onDelete = { position ->
                studentList.removeAt(position)
                adapter.updateList(studentList)
                Toast.makeText(this, "Data siswa dihapus", Toast.LENGTH_SHORT).show()
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Fitur Search
        searchView.queryHint = "Cari siswa..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText ?: "")
                return true
            }
        })

        // Fitur Tambah Siswa
        btnAdd.setOnClickListener {
            showAddStudentDialog()
        }
    }

    private fun showAddStudentDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null)
        val etNama = dialogView.findViewById<EditText>(R.id.etNama)
        val etNis = dialogView.findViewById<EditText>(R.id.etNis)
        val etKelas = dialogView.findViewById<EditText>(R.id.etKelas)

        AlertDialog.Builder(this)
            .setTitle("Tambah Siswa Baru")
            .setView(dialogView)
            .setPositiveButton("Tambah") { _, _ ->
                val nama = etNama.text.toString()
                val nis = etNis.text.toString()
                val kelas = etKelas.text.toString()

                if (nama.isNotEmpty() && nis.isNotEmpty() && kelas.isNotEmpty()) {
                    studentList.add(Student(nama, nis, kelas))
                    adapter.updateList(studentList)
                    Toast.makeText(this, "Siswa berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    // 🛠️ Dipindah ke luar showAddStudentDialog
    private fun showEditStudentDialog(student: Student, position: Int) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null)
        val etNama = dialogView.findViewById<EditText>(R.id.etNama)
        val etNis = dialogView.findViewById<EditText>(R.id.etNis)
        val etKelas = dialogView.findViewById<EditText>(R.id.etKelas)

        etNama.setText(student.nama)
        etNis.setText(student.nis)
        etKelas.setText(student.kelas)

        AlertDialog.Builder(this)
            .setTitle("Edit Siswa")
            .setView(dialogView)
            .setPositiveButton("Simpan") { _, _ ->
                val newName = etNama.text.toString()
                val newNis = etNis.text.toString()
                val newClass = etKelas.text.toString()

                if (newName.isNotEmpty() && newNis.isNotEmpty() && newClass.isNotEmpty()) {
                    studentList[position] = Student(newName, newNis, newClass)
                    adapter.updateList(studentList)
                    Toast.makeText(this, "Data siswa berhasil diupdate", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
