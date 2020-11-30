package com.paulo.myapplication.signup.view

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.paulo.myapplication.R


class SignupFragment : Fragment() {
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        navSignup()
        backBtn()
    }

    private fun navSignup() {
        val btnSignup = _view.findViewById<MaterialButton>(R.id.btnSignupSignup)
        btnSignup.setOnClickListener {
            errorHandler()
            navListener()
        }
    }

    private fun errorHandler() {
        val nameText = _view.findViewById<TextInputEditText>(R.id.tietNameSignup)
        val nameLayout = _view.findViewById<TextInputLayout>(R.id.tilNameSignup)
        val emailText = _view.findViewById<TextInputEditText>(R.id.tietEmailSignup)
        val emailLayout = _view.findViewById<TextInputLayout>(R.id.tilEmailSignup)
        val passwordText = _view.findViewById<TextInputEditText>(R.id.tietPasswordSignup)
        val passwordLayout = _view.findViewById<TextInputLayout>(R.id.tilPasswordSignup)

        putError(nameText, nameLayout)
        clearError(nameText, nameLayout)
        putError(emailText, emailLayout)
        clearError(emailText, emailLayout)
        putError(passwordText, passwordLayout)
        clearError(passwordText, passwordLayout)
    }

    private fun putError(text: TextInputEditText?, layout: TextInputLayout?) {
        if (text?.text.toString().isEmpty()) {
            layout?.isErrorEnabled = true
            layout?.error = getString(R.string.error_vazio)
        }
    }

    private fun clearError(text: TextInputEditText?, layout: TextInputLayout?) {
        text?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                layout!!.isErrorEnabled = false
                layout.error = ""
            }
        })
    }


    private fun navListener() {
        val name =
            _view.findViewById<TextInputEditText>(R.id.tietNameSignup)
        val email =
            _view.findViewById<TextInputEditText>(R.id.tietEmailSignup)
        val password =
            _view.findViewById<TextInputEditText>(R.id.tietPasswordSignup)


        if (name.text.toString().isEmpty() ||
            email.text.toString().isEmpty() ||
            password.text.toString().isEmpty()
        ) {
            val toast =
                Toast.makeText(context, getString(R.string.error_vazio), Toast.LENGTH_SHORT)
            toast.show()
        } else {
            val bundle = bundleOf("email" to email.text.toString())

            val navController = findNavController()
            navController.navigate(R.id.action_signupFragment_to_loginFragment, bundle)

            val toast =
                Toast.makeText(context, getString(R.string.conta_criada), Toast.LENGTH_SHORT)
            toast.show()

            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            _view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(_view.windowToken, 0)
    }

    private fun backBtn() {
        val btn = _view.findViewById<ImageButton>(R.id.btnBackbtnSignup)
        btn.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }
}