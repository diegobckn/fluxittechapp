package com.diegobckn.fluxit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.diegobckn.fluxit.R
import kotlinx.android.synthetic.main.fragment_aditional.*


class AditionalFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aditional, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val search: String = activity?.intent?.extras?.get("search") as String
        val url: String = "https://www.google.com/search?q=$search"
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl(url)
        super.onViewCreated(view, savedInstanceState)
    }
}