package net.lpscream.weatherstudio.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import net.lpscream.weatherstudio.R
import net.lpscream.weatherstudio.databinding.AddCountryFragmentBinding

class AddCountryFragment : DialogFragment() {


    private var _binding: AddCountryFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddCountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AddCountryFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, AddCountryViewModelFactory(requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE))).get(AddCountryViewModel::class.java)
        binding.addCountryTextVeiw.setOnKeyListener { view, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_UP) {
                when (i) {
                    KeyEvent.KEYCODE_ENTER -> {
                        if (binding.addCountryTextVeiw.text.toString().length > 2) {
                            setFragmentResult("COUNTRY_ITEM", bundleOf("country_item" to binding.addCountryTextVeiw.text.toString()))
                            dismiss()
                            return@setOnKeyListener false
                        }
                    }
                    KeyEvent.KEYCODE_DPAD_CENTER -> {
                        val imm: InputMethodManager =
                            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(binding.addCountryTextVeiw, InputMethodManager.SHOW_IMPLICIT)
                    }
                }
            }

            false
        }
    }

}