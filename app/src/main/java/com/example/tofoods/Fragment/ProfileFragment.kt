package com.example.tofoods.Fragment

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.database.getStringOrNull
import com.example.tofoods.DB.DBHelper
import com.example.tofoods.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var tvNama: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvNoTelp: TextView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedLogin = this.requireContext().getSharedPreferences("Login", AppCompatActivity.MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()

        // get user info to array
        val userDBHelper = DBHelper(this.requireContext())
        val cursor: List<String> = userDBHelper.getUserInfo()

        // set textview
        tvNama = view.findViewById(R.id.tv_name_profile)
        tvNama.text = cursor[2]
        tvEmail = view.findViewById(R.id.tv_email_profile)
        tvEmail.text = cursor[0]
        tvNoTelp = view.findViewById(R.id.tv_phone_profile)
        tvNoTelp.text = cursor[3]

        btnLogout = view.findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            editSavedLogin.putString("Status", null)
            editSavedLogin.putString("Email", null)
            editSavedLogin.putString("Password", null)
            editSavedLogin.commit()
            Intent(this.requireContext(), com.example.tofoods.LoginActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
