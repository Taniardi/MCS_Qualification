package com.example.mcs_qualification.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mcs_qualification.R
import com.example.mcs_qualification.activity.MainActivity
import com.example.mcs_qualification.adapter.HistoryAdapter
import com.example.mcs_qualification.model.TransactionHeader
import com.example.mcs_qualification.utils.Core
import com.example.mcs_qualification.utils.TransactionHeaderHelper

class HistoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val helper: TransactionHeaderHelper = TransactionHeaderHelper(view.context)
        helper.open()
        val thList: ArrayList<TransactionHeader> = helper.getTransactionHeader(Core.user.id)
        helper.close()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycleHistory)
        recyclerView.adapter = HistoryAdapter(thList, activity as MainActivity)

        return view
    }
}