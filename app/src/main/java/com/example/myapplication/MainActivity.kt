package com.example.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = PlantAdapter()
    private var editLauncher: ActivityResultLauncher<Intent>? = null
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.PlantList.observe(this){
            adapter.submitList(it)
        }
        //val intent = Intent(this, EditActivity::class.java)
        //intent.putExtra("extra_mode", "mode_add")
        //startActivity(intent)
        //Log.d("Main", it.toString())
        //Log.d("Mains", it.toString())
        //Log.d("sdfds", "fsdfdsfds")


        //viewModel.getList()
        //editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        //    if(it.resultCode == RESULT_OK){
        //        viewModel.addPlant(it.data?.getSerializableExtra("plant") as Plant)
        //var plant : Plant = it.data?.getSerializableExtra("plant") as Plant
        //        var int = it.data?.getIntExtra("plant2", 0)
        //        Log.d("log", int.toString())
        //    }

        // }
        init()
        setupClickListener()
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter
            button3.setOnClickListener {
               val intent = EditActivity.addNewItemIntent(this@MainActivity)
                startActivity(intent)
                //  val intent = Intent(this@MainActivity, EditActivity::class.java)
                //   startActivity(intent)
                //editLauncher?.launch(Intent(this@MainActivity, EditActivity::class.java) )
            }
            //setupClickListener()
        }
        // adapter.onPlantLongClickListener = {
        //editLauncher?.launch(Intent(this@MainActivity, EditActivity::class.java))

        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                   val element = adapter.currentList[viewHolder.adapterPosition]
                   viewModel.removePlant(element)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(binding.rcView)
    }
    private fun setupClickListener() {
        adapter.onItemClickListener = {
            val intent = EditActivity.editItemIntent(this@MainActivity, it.id)
            startActivity(intent)
        }


    }
}
   // }
//}