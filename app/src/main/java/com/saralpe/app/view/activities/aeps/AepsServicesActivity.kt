package com.saralpe.app.view.activities.aeps

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saralpe.app.R
import com.saralpe.app.databinding.ActivityAepsServicesBinding
import com.saralpe.app.view.activities.BaseActivity

class AepsServicesActivity : BaseActivity() {

    private lateinit var binding: ActivityAepsServicesBinding

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAepsServicesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this


        binding.backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.aadhaarPayBtn.setOnClickListener {
            startActivity(Intent(context, AadhaarPayActivity::class.java))
        }

        binding.cashWithdrawalBtn.setOnClickListener {
            startActivity(Intent(context, CashWithdrawalActivity::class.java))
        }

        binding.miniStatementBtn.setOnClickListener {

            startActivity(Intent(context, MiniStatementActivity::class.java))
        }

        binding.balanceEnquiryBtn.setOnClickListener {
            startActivity(Intent(context, BalanceEnquiryActivity::class.java))

        }

    }
}