package ipvc.estg.projeto1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddNota : AppCompatActivity() {

    private lateinit var notaText: EditText
    private lateinit var descricaoText: EditText

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nota)

        notaText = findViewById(R.id.edit_nota)
        descricaoText =findViewById(R.id.edit_desc)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener{
            val replyIntent = Intent()
            if(TextUtils.isEmpty(notaText.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                val nota = notaText.text.toString()
                val descricao = descricaoText.text.toString()
                replyIntent.putExtra(EXTRA_REPLY_nota, nota)
                replyIntent.putExtra(EXTRA_REPLY_desc, nota)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
    companion object{
        const val EXTRA_REPLY_nota = "com.example.android.nota"
        const val EXTRA_REPLY_desc = "com.example.android.desc"
    }

}