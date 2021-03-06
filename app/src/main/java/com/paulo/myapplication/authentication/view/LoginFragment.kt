package com.paulo.myapplication.authentication.view

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.paulo.myapplication.R
import com.paulo.myapplication.authentication.utils.AuthUtils

class LoginFragment : Fragment() {
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        val argEmail = arguments?.getString("email")

        if (argEmail != null) {
            setEmail(argEmail)
        }

        checkPref()
        btnSignup()
        btnLogin()
    }

    private fun checkPref() {
        if (AuthUtils.checkedSavedPref(requireActivity())) {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_comicsFragment)
        }
    }

    private fun btnLogin() {
        val loginBtb = _view.findViewById<MaterialButton>(R.id.btnLoginLogin)
        val checked = _view.findViewById<CheckBox>(R.id.cbCheckBoxLogin)

        loginBtb.setOnClickListener {
            errorHandler()
            navLogin()
            AuthUtils.savePref(requireActivity(), checked.isChecked)
        }
    }

    private fun errorHandler() {
        val emailText = _view.findViewById<TextInputEditText>(R.id.tietEmailLogin)
        val emailLayout = _view.findViewById<TextInputLayout>(R.id.tilEmailLogin)
        val passwordText = _view.findViewById<TextInputEditText>(R.id.tietPasswordLogin)
        val passwordLayout = _view.findViewById<TextInputLayout>(R.id.tilPasswordLogin)

        putError(emailText, emailLayout)
        clearError(emailText, emailLayout)
        putError(passwordText, passwordLayout)
        clearError(passwordText, passwordLayout)
    }

    private fun putError(text: TextInputEditText, layout: TextInputLayout) {
        if (text.text.toString().isEmpty()) {
            layout.isErrorEnabled = true
            layout.error = getString(R.string.error_empty)
        }
    }

    private fun clearError(text: TextInputEditText, layout: TextInputLayout) {
        text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                layout.isErrorEnabled = false
                layout.error = ""
            }
        })
    }

    private fun navLogin() {
        val email = _view.findViewById<TextInputEditText>(R.id.tietEmailLogin)
        val password = _view.findViewById<TextInputEditText>(R.id.tietPasswordLogin)

        if (email.text.toString().isEmpty() || password.text.toString().isEmpty()) {
            hideKeyboard()
            val toast =
                Toast.makeText(_view.context, getString(R.string.empty_fields), Toast.LENGTH_SHORT)
            toast.show()
        } else {
            hideKeyboard()
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_comicsFragment)
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            _view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(_view.windowToken, 0)
    }

    private fun setEmail(emailString: String) {
        val emailText = _view.findViewById<TextInputEditText>(R.id.tietEmailLogin)
        emailText.setText(emailString)
        val passwordText = _view.findViewById<TextInputEditText>(R.id.tietPasswordLogin)
        passwordText.requestFocus()
    }


    private fun btnSignup() {
        val btn = _view.findViewById<MaterialButton>(R.id.btnSignupLogin)
        btn.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }
}