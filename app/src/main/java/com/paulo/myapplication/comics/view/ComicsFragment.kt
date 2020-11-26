package com.paulo.myapplication.comics.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.paulo.myapplication.R

class ComicsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        context?.theme?.applyStyle(R.style.AppTheme_Comics, true)

        val view = inflater.inflate(R.layout.fragment_comics, container, false)

        return view
    }
}