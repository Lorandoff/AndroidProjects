package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMain2Binding
import com.example.myapplication.databinding.ActivityMainBinding
import java.lang.RuntimeException

class EditActivity : AppCompatActivity() {
    lateinit var binding : ActivityMain2Binding
    private var indexImage = 0
    private var imageId = 0
    private lateinit var viewModel: EditViewModel
    private val imageIdList = listOf(
        R.drawable.plant1,
        R.drawable.plant2,
        R.drawable.plant3,
        R.drawable.plant4,
       // R.drawable.plant5,
    )
    private var Id = 0
    private var mode = ""
    private var mode_regim = ""
    //private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)
        //val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        viewModel =ViewModelProvider(this)[EditViewModel::class.java]
        Log.d("EditActivity", mode.toString())
        binding.edTitlein.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resErrorData()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.edDescription2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resErrorDesc()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        initButtons()
        initIntent()
        when (mode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
        viewModel.errorDataInfoLV.observe(this) {
            val message = if(it) {
                "Jib,jxrf"
            }else {
                null
            }
            binding.edTitle.error = message
        }
        viewModel.errorDataInfoDescLV.observe(this) {
            val message = if(it) {
                "Jib,jxrf"
            }else {
                null
            }
            binding.edDescription.error = message
        }
        viewModel.resultInfo.observe(this) {
            finish()

        }
        //val mode_edit = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, 0)
        //Log.d("EditActivity_Edit", mode_edit.toString())
    //initButtons()

    }
    private fun launchEditMode() {
        viewModel.getItem(Id)
        viewModel.PlantItem.observe(this){
            binding.edTitlein.setText(it.title)
            binding.edDescription2.setText(it.desc)
            binding.Done.setOnClickListener {
                viewModel.editPlant(binding.edTitlein.text?.toString(), imageId.toString(),
                binding.edDescription2.text?.toString())
            Log.d("EditActivity", Id.toString())
                Log.d("EditActivity", imageId.toString())
                }

        }
    }
    private fun launchAddMode() {
        binding.Done.setOnClickListener {
            viewModel.addPlantItem(binding.edTitlein.text?.toString(), imageId.toString(),
                binding.edDescription2.text?.toString())
            Log.d("EditActivity", viewModel.PlantItem.toString() )
        }
    }
    companion object {
        const val EXTRA_SCREEN_MODE = "extra_mode"
        const val MODE_ADD = "mode_add"
        const val MODE_EDIT = "mode_edit"
        const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        fun addNewItemIntent(context: Context): Intent {
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }
        fun editItemIntent(context: Context, id : Int) : Intent {
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, id)
            return intent
        }
    }
    private fun initIntent() {
        if(!intent.hasExtra(EXTRA_SCREEN_MODE)){
            throw RuntimeException("no extra intent")//Log.d("EditActivity", "no extra intent")
        }
        mode = intent.getStringExtra(EXTRA_SCREEN_MODE).toString()
        if(mode != MODE_ADD && mode != MODE_EDIT){
            throw RuntimeException("no extra mode intent")
        }
        mode_regim = mode
        if(mode == MODE_EDIT) {
            if(!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("noe extra mode intent")
            }
            Id = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, -1)

        }
       // if(mode == MODE_ADD) {
      //
//
      //  }
    }
    private fun initButtons() = with(binding) {
        button.setOnClickListener {
            indexImage++
            if(indexImage > imageIdList.size - 1) indexImage = 0
            imageId = imageIdList[indexImage]
            im.setImageResource(imageId)
        }
       // Done.setOnClickListener {
           // viewModel.PlantList.observe(this@EditActivity) {

               // viewModel.addPlant(plant = Plant(imageId, indexImage, edTitle.text.toString(),
            //        edDescription.text.toString()))
          //  }
            //val plant = Plant(imageId, edTitle.text.toString(), edDescription.text.toString())
            //val editIntent = Intent().apply {
                //putExtra("plant", plant)
                //putExtra("plant2", indexImage)

        //    }
            //setResult(RESULT_OK, editIntent)
            //finish()
        }
    }
