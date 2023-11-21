package com.saralpe.app.view.activities.bbps

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.saralpe.app.R
import com.saralpe.app.databinding.ActivityBbpsBinding
import com.saralpe.app.view.activities.BaseActivity

class BbpsActivity : BaseActivity() {

    private lateinit var binding:ActivityBbpsBinding

    private lateinit var context: Context

    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBbpsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        if(intent != null && intent.hasExtra("TYPE")){
            type = intent.getIntExtra("TYPE",0)
            displayRequiredScreen(type)
        }

        binding.backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun displayRequiredScreen(type:Int){

        when(type){
            1->{
              binding.bbpsHeading.text = "DTH Recharge"
              binding.bbpsDthRechargeLayout.visibility = View.VISIBLE
            }
            2->{
                binding.bbpsHeading.text = "Electricity"
                binding.bbpsElectricityLayout.visibility = View.VISIBLE
            }
            3->{
                binding.bbpsHeading.text = "Cable TV"
                binding.bbpsCableTvLayout.visibility = View.VISIBLE
            }
            4->{
                binding.bbpsHeading.text = "Postpaid Recharge"
                binding.bbpsPostPaidLayout.visibility = View.VISIBLE
            }
            5->{
                binding.bbpsHeading.text = "Gas booking"
                binding.bbpsGasBookingLayout.visibility = View.VISIBLE
            }
            6->{
                binding.bbpsHeading.text = "Water Bill"
                binding.bbpsWaterBillLayout.visibility = View.VISIBLE
            }
            7->{
                binding.bbpsHeading.text = "Broadband"
                binding.bbpsBroadbandLayout.visibility = View.VISIBLE
            }
            8->{
                binding.bbpsHeading.text = "Loan Repayment"
                binding.bbpsLoanRepaymentLayout.visibility = View.VISIBLE
            }
            9->{
                binding.bbpsHeading.text = "Prepaid Recharge"
                binding.bbpsPrepaidRechargeLayout.visibility = View.VISIBLE
            }
            10->{
                binding.bbpsHeading.text = "Fastag"
                binding.bbpsFastagLayout.visibility = View.VISIBLE
            }
            11->{
                binding.bbpsHeading.text = "Piped Gas"
                binding.bbpsPipedGasLayout.visibility = View.VISIBLE
            }
            12->{
                binding.bbpsHeading.text = "Credit Card"
                binding.bbpsCreditCardLayout.visibility = View.VISIBLE
            }
            13->{
                binding.bbpsHeading.text = "Insurance"
                binding.bbpsInsuranceLayout.visibility = View.VISIBLE
            }
            14->{
                binding.bbpsHeading.text = "Municpal Taxes"
                binding.bbpsMunicipalTaxesLayout.visibility = View.VISIBLE
                binding.bbpsPayBtn.text = "View Bill"
            }
            15->{
                binding.bbpsHeading.text = "Digital Voucher"
                binding.bbpsDigitalVoucherLayout.visibility = View.VISIBLE
            }
            16->{
                binding.bbpsHeading.text = "Traffic Challan"
                binding.bbpsTrafficChallanLayout.visibility = View.VISIBLE
                binding.bbpsPayBtn.text = "View Bill"
            }
            17->{
                binding.bbpsHeading.text = "EMI"
                binding.bbpsEmiLayout.visibility = View.VISIBLE
            }
            18->{
                binding.bbpsHeading.text = "Datacard Prepaid"
                binding.bbpsDatacardPrepaidLayout.visibility = View.VISIBLE
                binding.bbpsPayBtn.text = "View Bill"
            }
            19->{

            }
            else->{

            }
        }
    }
}