package com.example.timecapsuleApp.Transactions.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.timecapsuleApp.R
import com.example.timecapsuleApp.Transactions.models.Transaction
import kotlinx.coroutines.launch

class AddTransactionFragment : DialogFragment() {


    // TODO: abstrakta klasser!

    interface OnTransactionAddedListener {
         fun onTransactionAdded(transaction: Transaction)
    }

    private var listener: OnTransactionAddedListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_add_transaction, null)
        val addBtn = view.findViewById<android.widget.Button>(R.id.addTransactionBtn)

        builder.setView(view)

        addBtn.setOnClickListener {
            val nameEditText = view.findViewById<EditText>(R.id.nameEditText)

            val name = nameEditText.text.toString()
            if (name.isNotEmpty()) {

                val transaction = Transaction(message = name)
                Log.d("AddTransactionFragment", "Message added")

                lifecycleScope.launch {
                    listener?.onTransactionAdded(transaction)
                }
                dismiss()
            }
        }
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as OnTransactionAddedListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnTransactionAddedListener")
        }
    }
}