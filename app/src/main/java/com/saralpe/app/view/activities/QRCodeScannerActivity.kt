package com.saralpe.app.view.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.BarcodeFormat
import com.saralpe.app.databinding.ActivityQrcodeScannerBinding
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.util.*


class QRCodeScannerActivity : BaseActivity(), ZXingScannerView.ResultHandler {

    private lateinit var binding:ActivityQrcodeScannerBinding

    private lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrcodeScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scanner.setFormats(Collections.singletonList(BarcodeFormat.QR_CODE))
        binding.scanner.setSquareViewFinder(true)
        binding.scanner.setBorderStrokeWidth(1)
        binding.scanner.setLaserEnabled(true)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1001)
        }

    }

    override fun onResume() {
        super.onResume()
        binding.scanner.setResultHandler(this)
        binding.scanner.startCamera()
    }

    override fun onPause() {
        super.onPause()
        binding.scanner.stopCamera()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start the camera.
                binding.scanner.setAutoFocus(true)
                binding.scanner.setResultHandler(this)
                binding.scanner.startCamera()
            } else {
                Toast.makeText(this, "Camera permission is required for QR code scanning.", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    override fun handleResult(result: Result?) {
        val scanResult: String = result!!.text
        val resultIntent = Intent()
        resultIntent.putExtra("SCAN_RESULT", scanResult)
        setResult(RESULT_OK, resultIntent)
        finish()
//        Toast.makeText(this, "Scan Result: $scanResult", Toast.LENGTH_SHORT).show()
    }
}