package com.smkth.app3_recycleview.adapter

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog
import com.smkth.app3_recycleview.DetailActivity
import com.smkth.app3_recycleview.R
import com.smkth.app3_recycleview.model.Student
import java.util.*

class StudentAdapter(
    private val context: Context,
    private var studentList: MutableList<Student>,
    private val onEdit: (Student, Int) -> Unit,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>(), Filterable {

    private var filteredList: MutableList<Student> = studentList.toMutableList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvNama)
        val tvNis: TextView = itemView.findViewById(R.id.tvNis)
        val tvKelas: TextView = itemView.findViewById(R.id.tvKelas)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = filteredList[position]

        holder.tvName.text = student.nama
        holder.tvNis.text = "NIS: ${student.nis}"
        holder.tvKelas.text = "Kelas: ${student.kelas}"

        // Klik item → buka detail
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("student_nama", student.nama)
                putExtra("student_nis", student.nis)
                putExtra("student_kelas", student.kelas)
            }
            context.startActivity(intent)
        }

        // Tombol edit
        holder.btnEdit.setOnClickListener {
            onEdit(student, position)
        }

        // Tombol hapus
        holder.btnDelete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Hapus Siswa")
                .setMessage("Yakin ingin menghapus ${student.nama}?")
                .setPositiveButton("Hapus") { _, _ ->
                    onDelete(position)
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val keyword = query.toString().lowercase(Locale.ROOT)
                filteredList = if (keyword.isEmpty()) {
                    studentList.toMutableList()
                } else {
                    studentList.filter {
                        it.nama.lowercase(Locale.ROOT).contains(keyword) ||
                                it.nis.contains(keyword) ||
                                it.kelas.lowercase(Locale.ROOT).contains(keyword)
                    }.toMutableList()
                }
                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as MutableList<Student>
                notifyDataSetChanged()
            }
        }
    }

    fun updateList(newList: MutableList<Student>) {
        studentList = newList
        filteredList = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun getOriginalList(): List<Student> = studentList
}
