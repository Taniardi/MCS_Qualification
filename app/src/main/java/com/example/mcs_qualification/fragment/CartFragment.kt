package com.example.mcs_qualification.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mcs_qualification.R
import com.example.mcs_qualification.activity.JokesActivity
import com.example.mcs_qualification.activity.MainActivity
import com.example.mcs_qualification.adapter.CartAdapter
import com.example.mcs_qualification.repository.CartRepository
import com.example.mcs_qualification.utils.Core
import com.example.mcs_qualification.utils.TransactionDetailHelper
import com.example.mcs_qualification.utils.TransactionHeaderHelper
import java.sql.Date
import java.util.Calendar

class CartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        val linEmpty = view.findViewById<LinearLayout>(R.id.linearEmpty)
        val recycleCart = view.findViewById<RecyclerView>(R.id.recycleCart)
        val btnCheckout = view.findViewById<Button>(R.id.btnCheckout)

        if (CartRepository.instance!!.getItemList().size > 0) {
            linEmpty.visibility = ViewGroup.INVISIBLE
        } else {
            linEmpty.visibility = ViewGroup.VISIBLE
        }

        recycleCart.adapter =
            CartAdapter(CartRepository.instance!!.getItemList(), activity as MainActivity)

        btnCheckout.setOnClickListener { x: View? ->
            val thHelper: TransactionHeaderHelper = TransactionHeaderHelper(view.context)
            thHelper.open()
            thHelper.addTransactionHeader(
                Core.user.id,
                Date(Calendar.getInstance().time.time)
            )
            thHelper.close()

            thHelper.open()
            val transactionId: Int = thHelper.lastId
            thHelper.close()

            val tdHelper: TransactionDetailHelper = TransactionDetailHelper(view.context)
            tdHelper.open()

            for (item in CartRepository.instance!!.getItemList()) {
                tdHelper.addTransactionDetail(transactionId, item.id)
            }

            tdHelper.close()
            CartRepository.instance!!.clearItem()

            val itent =
                Intent(activity, JokesActivity::class.java)
            startActivity(itent)
        }

        return view
    }
}