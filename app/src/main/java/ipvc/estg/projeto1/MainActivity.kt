package ipvc.estg.projeto1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.view.Menu
import android.view.MenuInflater
import androidx.room.Insert
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.projeto1.adapters.NotaAdapter
import ipvc.estg.projeto1.entities.Nota
import ipvc.estg.projeto1.viewModel.NotaViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var notaViewModel: NotaViewModel
    private val newNotaActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
            val adapter = NotaAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //view model
        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        notaViewModel.allNotas.observe(this, Observer { notas ->
            notas?.let { adapter.setNotas(it)}

        })

        //Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this@MainActivity, AddNota::class.java)
            startActivityForResult(intent, newNotaActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newNotaActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val pnota = data?.getStringExtra(AddNota.EXTRA_REPLY_nota)
            val pdesc = data?.getStringExtra(AddNota.EXTRA_REPLY_desc)
            if (pnota!= null && pdesc != null) {
                val nota = Nota(nota = pnota, descicao = pdesc)
                notaViewModel.insert(nota)
            }

        }else{
            Toast.makeText(
                applicationContext,
                "Nota Vazia",
                Toast.LENGTH_LONG).show()

        }
    }


}