package com.saralpe.app.view.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textview.MaterialTextView
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.saralpe.app.R
import com.saralpe.app.adapters.*
import com.saralpe.app.databinding.FragmentHomeBinding
import com.saralpe.app.utils.WrapContentGridLayoutManager
import com.saralpe.app.utils.WrapContentLinearLayoutManager
import com.saralpe.app.view.activities.BaseActivity
import com.saralpe.app.view.activities.QRCodeScannerActivity
import com.saralpe.app.view.activities.WebViewActivity
import com.saralpe.app.view.activities.aeps.AepsServicesActivity
import com.saralpe.app.view.activities.aeps.BalanceEnquiryActivity
import com.saralpe.app.view.activities.aeps.CashWithdrawalActivity
import com.saralpe.app.view.activities.aeps.MiniStatementActivity
import com.saralpe.app.view.activities.bbps.BbpsActivity


open class HomeFragment : Fragment(), PaymentListAdapter.OnItemClickListener {

    interface OnDrawerListener{
        fun changeDrawer()
    }

    var listener:OnDrawerListener?=null
    private lateinit var binding:FragmentHomeBinding

    private val itemList = mutableListOf<Pair<Int,String>>()

    private val itemPromoList = mutableListOf<String>()

    private var moreLayoutType = 0

    private val homeBankingServiceList = mutableListOf<Pair<Int,String>>()
    private val homeRechargeBillPaymentList = mutableListOf<Pair<Int,String>>()
    private val homeGovernanceServicesList = mutableListOf<Pair<Int,String>>()
    private val homeInsurancePaymentList = mutableListOf<Pair<Int,String>>()
    private val homeTicketBookingList = mutableListOf<Pair<Int,String>>()
    private val homeInvestTradeStocksList = mutableListOf<Pair<Int,String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemList.add(Pair(R.drawable.electricity_icon,"Electricity"))
        itemList.add(Pair(R.drawable.wifi_recharge_icon,"Broadband"))
        itemList.add(Pair(R.drawable.voucher_icon,"Voucher"))
        itemList.add(Pair(R.drawable.insurance_icon,"Insurance"))
        itemList.add(Pair(R.drawable.shop_icon,"Shop"))
        itemList.add(Pair(R.drawable.mobile_recharge_icon,"Mobile\nRecharge"))
        itemList.add(Pair(R.drawable.utility_icon,"Utility"))
        itemList.add(Pair(R.drawable.more_icon,"More"))

        // HOME BANKING SERVICE LIST ITEMS
        homeBankingServiceList.add(Pair(R.drawable.aeps_services_icon,"Aeps\nServices"))
        homeBankingServiceList.add(Pair(R.drawable.micro_atm_icon,"Micro\nATM"))
        homeBankingServiceList.add(Pair(R.drawable.mini_statement_icon,"Mini\nStatement"))
        homeBankingServiceList.add(Pair(R.drawable.balance_enquiry_icon,"Balance\nEnquiry"))
        homeBankingServiceList.add(Pair(R.drawable.money_transfer_icon,"Money\nTransfer"))
        homeBankingServiceList.add(Pair(R.drawable.saral_transfer_icon,"Saral\nTransfer"))
        homeBankingServiceList.add(Pair(R.drawable.upi_transfer_icon,"UPI\nTransfer"))
        homeBankingServiceList.add(Pair(R.drawable.scan_pay_icon,"Scan &\nPay"))

        // HOME RECHARGE & BILL PAYMENTS LIST ITEMS
        homeRechargeBillPaymentList.add(Pair(R.drawable.mobile_recharge_icon_1,"Mobile\nRecharge"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.education_icon,"Education"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.dth_recharge_icon,"DTH\nRecharge"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.electricity_bill_icon,"Electricity\nBill"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.credit_card_payment_icon,"Credit Card\nPayment"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.mobile_postpaid_icon,"Mobile\nPostpaid"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.book_gas_cylinder_icon,"Book Gas\nCylinder"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.water_icon,"Water"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.broadband_icon,"Broadband"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.fastag_icon,"FasTag"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.cable_tv_icon,"Cable TV"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.insurance_icon_1,"Insurance"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.lic_premium_icon,"LIC Premium"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.subscription_icon,"Subscription"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.piped_gas_icon,"Piped Gas"))
        homeRechargeBillPaymentList.add(Pair(R.drawable.loan_repayment_icon,"Loan Repayment"))

        // HOME GOVERNANCE SERVICES LIST ITEMS
        homeGovernanceServicesList.add(Pair(R.drawable.uti_pan_icon,"UTI PAN"))
        homeGovernanceServicesList.add(Pair(R.drawable.nsdl_pan_icon,"NSDL PAN"))
        homeGovernanceServicesList.add(Pair(R.drawable.income_tax_filing_icon,"Income Tax\nFiling"))
        homeGovernanceServicesList.add(Pair(R.drawable.gst_registration_icon,"GST Registration"))

        // HOME INSURANCE PAYMENT LIST ITEMS
        homeInsurancePaymentList.add(Pair(R.drawable.bike_insurance_icon,"Bike\nInsurance"))
        homeInsurancePaymentList.add(Pair(R.drawable.car_insurance_icon,"Car\nInsurance"))
        homeInsurancePaymentList.add(Pair(R.drawable.health_insurance_icon,"Health\nInsurance"))
        homeInsurancePaymentList.add(Pair(R.drawable.accidental_insurance_icon,"Accidental\nInsurance"))
        homeInsurancePaymentList.add(Pair(R.drawable.hospital_cash_icon,"Hospital\nCash"))
        homeInsurancePaymentList.add(Pair(R.drawable.commercial_icon,"Commercial"))
        homeInsurancePaymentList.add(Pair(R.drawable.shop_icon_1,"Shop"))
        homeInsurancePaymentList.add(Pair(R.drawable.device_genie_icon,"Device\nGenie"))

        // HOME TICKET BOOKING LIST ITEMS
        homeTicketBookingList.add(Pair(R.drawable.flight_ticket_icon,"Flight Tickets"))
        homeTicketBookingList.add(Pair(R.drawable.bus_ticket_icon,"Bus Tickets"))
        homeTicketBookingList.add(Pair(R.drawable.train_ticket_icon,"Train Tickets"))
        homeTicketBookingList.add(Pair(R.drawable.movie_ticket_icon,"Movie Tickets"))
        homeTicketBookingList.add(Pair(R.drawable.event_ticket_icon,"Event Tickets"))
        homeTicketBookingList.add(Pair(R.drawable.metro_ticket_icon,"Metro Tickets"))
        homeTicketBookingList.add(Pair(R.drawable.hotel_booking_icon,"Hotel Booking"))

        // HOME INVEST TRADE STOCKS LIST ITEMS
        homeInvestTradeStocksList.add(Pair(R.drawable.open_demat_account_icon,"Open Demat\nAccount"))
        homeInvestTradeStocksList.add(Pair(R.drawable.invest_with_500_icon,"Invest with\n₹500"))
        homeInvestTradeStocksList.add(Pair(R.drawable.sip_icon,"SIP"))
        homeInvestTradeStocksList.add(Pair(R.drawable.mutual_fund_icon,"Mutual Funds"))

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnDrawerListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

                binding.homePaymentListRecyclerview.layoutManager = WrapContentGridLayoutManager(requireActivity(),4)
        binding.homePaymentListRecyclerview.hasFixedSize()
        val adapter = PaymentListAdapter(requireActivity(), itemList as ArrayList<Pair<Int, String>>)
        binding.homePaymentListRecyclerview.adapter = adapter
        adapter.setOnItemClickListener(this)

        binding.homePromoDiscountListRecyclerview.isNestedScrollingEnabled = false
        binding.homePromoDiscountListRecyclerview.layoutManager = WrapContentLinearLayoutManager(requireActivity(),
            LinearLayoutManager.HORIZONTAL,false)
        binding.homePromoDiscountListRecyclerview.hasFixedSize()
        val promoAdapter = PromoDiscountListAdapter(requireActivity(), itemPromoList as ArrayList<String>)
        binding.homePromoDiscountListRecyclerview.adapter = promoAdapter
        promoAdapter.setOnItemClickListener(object :PromoDiscountListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

            }

            override fun onItemLongClick(position: Int) {

            }

        })

        binding.bankingServicesWrapper.visibility = View.GONE
        binding.homeBankingServicesRecyclerview.isNestedScrollingEnabled = false
        binding.homeBankingServicesRecyclerview.layoutManager = WrapContentGridLayoutManager(requireActivity(), 4)
        binding.homeBankingServicesRecyclerview.hasFixedSize()
        val bankingServiceAdapter = BankingServiceListAdapter(requireActivity(), homeBankingServiceList as ArrayList<Pair<Int,String>>)
        binding.homeBankingServicesRecyclerview.adapter = bankingServiceAdapter
        bankingServiceAdapter.setOnItemClickListener(object :BankingServiceListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                  when(position){
                      0->{
                          startActivity(Intent(activity,AepsServicesActivity::class.java))
                      }
                      1->{
                          startActivity(Intent(activity,CashWithdrawalActivity::class.java))
                      }
                      2->{
                          startActivity(Intent(activity,MiniStatementActivity::class.java))
                      }
                      3->{
                          startActivity(Intent(activity,BalanceEnquiryActivity::class.java))
                      }
                      4->{
                          BaseActivity.showAlert(activity!!,"Coming soon")
                      }
                      5->{
                          BaseActivity.showAlert(activity!!,"Coming soon")
                      }
                      6->{
                          BaseActivity.showAlert(activity!!,"Coming soon")
                      }
                      7->{
                          openQrCodeScanner()
                      }
                      else->{

                      }
                  }
            }

            override fun onItemLongClick(position: Int) {

            }

        })

        binding.rechargeBillPaymentWrapper.visibility = View.GONE
        binding.homeRechargeBillPaymentRecyclerview.isNestedScrollingEnabled = false
        binding.homeRechargeBillPaymentRecyclerview.layoutManager = WrapContentGridLayoutManager(requireActivity(), 4)
        binding.homeRechargeBillPaymentRecyclerview.hasFixedSize()
        val rechargeBillPaymentListAdapter = RechargeBillPaymentListAdapter(requireActivity(), homeRechargeBillPaymentList as ArrayList<Pair<Int,String>>)
        binding.homeRechargeBillPaymentRecyclerview.adapter = rechargeBillPaymentListAdapter
        rechargeBillPaymentListAdapter.setOnItemClickListener(object :RechargeBillPaymentListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                 if(position == 1 || position == 12 || position == 13){
                     BaseActivity.showAlert(activity!!,"Coming soon")
                 }
                else{
                     val intent = Intent(activity,BbpsActivity::class.java)
                     when(position){
                         0->{
                             intent.putExtra("TYPE",9)
                         }
                         2->{
                             intent.putExtra("TYPE",1)
                         }
                         3->{
                             intent.putExtra("TYPE",2)
                         }
                         4->{
                             intent.putExtra("TYPE",12)
                         }
                         5->{
                             intent.putExtra("TYPE",4)
                         }
                         6->{
                             intent.putExtra("TYPE",5)
                         }
                         7->{
                             intent.putExtra("TYPE",6)
                         }
                         8->{
                             intent.putExtra("TYPE",7)
                         }
                         9->{
                             intent.putExtra("TYPE",10)
                         }
                         10->{
                             intent.putExtra("TYPE",3)
                         }
                         11->{
                             intent.putExtra("TYPE",13)
                         }
                         14->{
                             intent.putExtra("TYPE",11)
                         }
                         15->{
                             intent.putExtra("TYPE",8)
                         }
                         else->{

                         }
                     }
                     startActivity(intent)
                 }

            }

            override fun onItemLongClick(position: Int) {

            }

        })
        binding.egovtServicesWrapper.visibility = View.GONE
        binding.homeEGovernaceServicesRecyclerview.isNestedScrollingEnabled = false
        binding.homeEGovernaceServicesRecyclerview.layoutManager = WrapContentGridLayoutManager(requireActivity(), 4)
        binding.homeEGovernaceServicesRecyclerview.hasFixedSize()
        val governanceServicesListAdapter = GovernanceServicesListAdapter(requireActivity(), homeGovernanceServicesList as ArrayList<Pair<Int,String>>)
        binding.homeEGovernaceServicesRecyclerview.adapter = governanceServicesListAdapter
        governanceServicesListAdapter.setOnItemClickListener(object :GovernanceServicesListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
               when(position){
                   0->{
                       BaseActivity.showAlert(activity!!,"Coming soon")
                   }
                   1->{
                       BaseActivity.showAlert(activity!!,"Coming soon")
                   }
                   2->{
                       BaseActivity.showAlert(activity!!,"Coming soon")
                   }
                   3->{
                       BaseActivity.showAlert(activity!!,"Coming soon")
                   }
                   else->{

                   }
               }
            }

            override fun onItemLongClick(position: Int) {

            }

        })

        binding.insuranceWrapper.visibility = View.GONE
        binding.homeInsurancePaymentRecyclerview.isNestedScrollingEnabled = false
        binding.homeInsurancePaymentRecyclerview.layoutManager = WrapContentGridLayoutManager(requireActivity(), 4)
        binding.homeInsurancePaymentRecyclerview.hasFixedSize()
        val insurancePaymentListAdapter = InsurancePaymentListAdapter(requireActivity(), homeInsurancePaymentList as ArrayList<Pair<Int,String>>)
        binding.homeInsurancePaymentRecyclerview.adapter = insurancePaymentListAdapter
        insurancePaymentListAdapter.setOnItemClickListener(object :InsurancePaymentListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(activity,WebViewActivity::class.java)
                when(position){
                    0->{
//                        BaseActivity.showAlert(activity!!,"Coming soon")
                         intent.putExtra("URL","https://niyoin.com/")
                    }
                    1->{
//                        BaseActivity.showAlert(activity!!,"Coming soon")
                        intent.putExtra("URL","https://niyoin.com/")
                    }
                    2->{
//                        BaseActivity.showAlert(activity!!,"Coming soon")
                        intent.putExtra("URL","https://niyoin.com/")
                    }
                    3->{
//                        BaseActivity.showAlert(activity!!,"Coming soon")
                        intent.putExtra("URL","https://niyoin.com/")
                    }
                    4->{
//                        BaseActivity.showAlert(activity!!,"Coming soon")
                        intent.putExtra("URL","https://niyoin.com/")
                    }
                    5->{
//                        BaseActivity.showAlert(activity!!,"Coming soon")
                        intent.putExtra("URL","https://niyoin.com/")
                    }
                    6->{
//                        BaseActivity.showAlert(activity!!,"Coming soon")
                        intent.putExtra("URL","https://niyoin.com/")
                    }
                    7->{
//                        BaseActivity.showAlert(activity!!,"Coming soon")
                        intent.putExtra("URL","https://niyoin.com/")
                    }
                    else->{

                    }
                }
                startActivity(intent)
            }

            override fun onItemLongClick(position: Int) {

            }

        })

        binding.ticketBookingWrapper.visibility = View.GONE
        binding.homeTicketBookingRecyclerview.isNestedScrollingEnabled = false
        binding.homeTicketBookingRecyclerview.layoutManager = WrapContentGridLayoutManager(requireActivity(), 4)
        binding.homeTicketBookingRecyclerview.hasFixedSize()
        val ticketBookingListAdapter = TicketBookingListAdapter(requireActivity(), homeTicketBookingList as ArrayList<Pair<Int,String>>)
        binding.homeTicketBookingRecyclerview.adapter = ticketBookingListAdapter
        ticketBookingListAdapter.setOnItemClickListener(object :TicketBookingListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                 when(position){
                     0->{
                         BaseActivity.showAlert(activity!!,"Coming soon")
                     }
                     1->{
                         BaseActivity.showAlert(activity!!,"Coming soon")
                     }
                     2->{
                         BaseActivity.showAlert(activity!!,"Coming soon")
                     }
                     3->{
                         BaseActivity.showAlert(activity!!,"Coming soon")
                     }
                     4->{
                         BaseActivity.showAlert(activity!!,"Coming soon")
                     }
                     5->{
                         BaseActivity.showAlert(activity!!,"Coming soon")
                     }
                     6->{
                         BaseActivity.showAlert(activity!!,"Coming soon")
                     }
                     else->{

                     }
                 }
            }

            override fun onItemLongClick(position: Int) {

            }

        })
        binding.investTradeStocksWrapper.visibility = View.GONE
        binding.homeInvestTradeStocksRecyclerview.isNestedScrollingEnabled = false
        binding.homeInvestTradeStocksRecyclerview.layoutManager = WrapContentGridLayoutManager(requireActivity(), 4)
        binding.homeInvestTradeStocksRecyclerview.hasFixedSize()
        val investTradeStocksListAdapter = InvestTradeStocksListAdapter(requireActivity(), homeInvestTradeStocksList as ArrayList<Pair<Int,String>>)
        binding.homeInvestTradeStocksRecyclerview.adapter = investTradeStocksListAdapter
        investTradeStocksListAdapter.setOnItemClickListener(object :InvestTradeStocksListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                  when(position){
                      0->{
                          BaseActivity.showAlert(activity!!,"Coming soon")
                      }
                      1->{
                          BaseActivity.showAlert(activity!!,"Coming soon")
                      }
                      2->{
                          BaseActivity.showAlert(activity!!,"Coming soon")
                      }
                      3->{
                          BaseActivity.showAlert(activity!!,"Coming soon")
                      }
                      else->{

                      }
                  }
            }

            override fun onItemLongClick(position: Int) {

            }

        })


        binding.menuDrawer.setOnClickListener {
            listener!!.changeDrawer()
        }

        return binding.root
    }

    private fun openQrCodeScanner() {
        Permissions.check(
            requireActivity(),
            Manifest.permission.CAMERA,
            null,
            object : PermissionHandler() {
                override fun onGranted() {
                    // do your task.
                    resultLauncher.launch(Intent(context,QRCodeScannerActivity::class.java))
                }

                override fun onDenied(
                    context: Context?,
                    deniedPermissions: java.util.ArrayList<String>?
                ) {
                    BaseActivity.showAlert(
                        requireActivity(),
                        "Camera permission is required for QR code scanning."
                    )
                }
            })
//        val packageManager: PackageManager = activity!!.packageManager
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
////        intent.setPackage("com.google.zxing.client.android") // Specify the package name of the scanner app (ZXing)
//
//        if (packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
//            resultLauncher.launch(intent) // You can use a different request code if needed
//        } else {
//            // If the QR code scanner app is not installed, you can handle this case accordingly
//            // For example, you can prompt the user to install a QR code scanner app from the Play Store.
//            val marketUri: Uri = Uri.parse("market://details?id=com.google.zxing.client.android")
//            val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
//            startActivity(marketIntent)
//        }
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

                val contents =
                    data!!.getStringExtra("SCAN_RESULT") // This will contain the scanned QR code content
                // Handle the QR code content as needed
            BaseActivity.showAlert(activity!!,contents.toString())
        }
    }

    override fun onItemClick(position: Int,view:MaterialTextView) {
//        if(position == 7){
//
//        }
        val intent = Intent(activity,BbpsActivity::class.java)
        when(position){

            0->{
                 intent.putExtra("TYPE",2)
                startActivity(intent)
            }
            1->{
                intent.putExtra("TYPE",7)
                startActivity(intent)
            }
            2->{
                intent.putExtra("TYPE",15)
                startActivity(intent)
            }
            3->{
                intent.putExtra("TYPE",13)
                startActivity(intent)
            }
            4->{
                BaseActivity.showAlert(activity!!,"Coming soon")
            }
            5->{
                intent.putExtra("TYPE",9)
                startActivity(intent)
            }
            6->{
                BaseActivity.showAlert(activity!!,"Coming soon")
            }
            7->{
                if(moreLayoutType == 0){
                    view.text = "Less"
                    toggleMore()
                }
                else{
                    view.text = "More"
                    toggleMore()
                }
            }
            else->{

            }
        }
    }

    private fun toggleMore(){
        if(moreLayoutType == 0){
            moreLayoutType = 1
            binding.bankingServicesWrapper.visibility = View.VISIBLE
            binding.rechargeBillPaymentWrapper.visibility = View.VISIBLE
            binding.egovtServicesWrapper.visibility = View.VISIBLE
            binding.insuranceWrapper.visibility = View.VISIBLE
            binding.investTradeStocksWrapper.visibility = View.VISIBLE
            binding.ticketBookingWrapper.visibility = View.VISIBLE
        }
        else{
            moreLayoutType = 0
            binding.bankingServicesWrapper.visibility = View.GONE
            binding.rechargeBillPaymentWrapper.visibility = View.GONE
            binding.egovtServicesWrapper.visibility = View.GONE
            binding.insuranceWrapper.visibility = View.GONE
            binding.investTradeStocksWrapper.visibility = View.GONE
            binding.ticketBookingWrapper.visibility = View.GONE
        }
    }

    override fun onItemLongClick(position: Int) {

    }
}