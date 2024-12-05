package com.example.timecapsuleApp.Model

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timecapsuleApp.R
import com.example.timecapsuleApp.Transactions.models.Transaction

class TransactionAdapter(private val transactionList: List<Transaction>,     private val onDeleteClick: (Transaction) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {



    // hämtar och sätter alla itemViews med default värden
    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date_item)
        val message: TextView = itemView.findViewById(R.id.message_item)
        val removeBtn: Button = itemView.findViewById(R.id.delete_button)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return TransactionViewHolder(view)
    }

    // binder data till itemViews i listan
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.date.text = transaction.createdAt
        holder.message.text = transaction.message

        holder.removeBtn.setOnClickListener {
            onDeleteClick(transaction)
        }

        holder.removeBtn.setOnClickListener {
            onDeleteClick(transaction)
        }
    }

    // storlek på lista
    override fun getItemCount(): Int {
        return transactionList.size
    }

}
