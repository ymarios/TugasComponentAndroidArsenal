package com.adl.belajarsharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import it.ngallazzi.fancyswitch.FancyState
import it.ngallazzi.fancyswitch.FancySwitch
import kotlinx.android.synthetic.main.activity_uji_lab.*

class UjiLab : AppCompatActivity() {

     var sliderValue:Int = 0
     var groupCombo:Int = 0
    var switchComp:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uji_lab)
        val sharedPreference = getSharedPreferences("uji_lab_setting", Context.MODE_PRIVATE)

        seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            txtSlider.setText("Nilainya adalah : ${p1.toString()}")
                sliderValue = p1

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        radioGroup.setOnCheckedChangeListener { p0, p1 ->
            val resultt = when (p1) {
                R.id.radioButton -> "Option 1"
                R.id.radioButton2 -> "Option 2"
                R.id.radioButton3 -> "Option 3"
                else -> "No Option"
            }
            Toast.makeText(this@UjiLab, "Pilihannya adalah ${resultt}", Toast.LENGTH_LONG).show()
            groupCombo = p1
        }

        switch1.setOnCheckedChangeListener { p0, p1 ->
            Toast.makeText(this@UjiLab, "nilainya adalah ${p1}", Toast.LENGTH_SHORT).show()
            switch1.setText(p1.toString())
            switchComp = p1
        }

        fancySwitch.setSwitchStateChangedListener(object : FancySwitch.StateChangedListener{
            override fun onChanged(newState: FancyState) {

                if(newState.id == FancyState.State.ON){
                    Toast.makeText(this@UjiLab,"Nilainya adalah on",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@UjiLab,"Nilainya adalah off",Toast.LENGTH_SHORT).show()
                }



            }
        })

        btnSave.setOnClickListener({

            var editor = sharedPreference.edit()
            editor.putInt("slider",sliderValue)
            editor.putInt("combo",groupCombo)
            editor.putBoolean("switch",switchComp)
            editor.putString("text",txtInput.text.toString())
            editor.commit()

        })

        loadSave(sharedPreference)

    }

    private fun loadSave(sharedPreference:SharedPreferences) {
        sliderValue = sharedPreference.getInt("slider",0)
        groupCombo = sharedPreference.getInt("combo",0)
        switchComp = sharedPreference.getBoolean("switch",false)
        txtInput.setText(sharedPreference.getString("text",""))

        seekBar.setProgress(sliderValue)
        radioGroup.check(groupCombo)
        switch1.setChecked(switchComp)


    }
}