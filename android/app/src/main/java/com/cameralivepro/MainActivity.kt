package com.cameralivepro

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    private lateinit var previewView: PreviewView
    private lateinit var status: TextView
    private var useFrontCamera = false
    private var provider: ProcessCameraProvider? = null

    private val permissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) startCamera() else status.text = "Permissão de câmera negada."
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.rgb(16, 21, 29)
        window.navigationBarColor = android.graphics.Color.rgb(16, 21, 29)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 18, 20, 18)
            setBackgroundColor(android.graphics.Color.rgb(16, 21, 29))
        }
        val title = TextView(this).apply {
            text = "CAMERA LIVE PRO"
            textSize = 24f
            setTextColor(android.graphics.Color.WHITE)
            gravity = Gravity.CENTER
        }
        status = TextView(this).apply {
            text = "Preparando câmera…"
            textSize = 14f
            setTextColor(android.graphics.Color.rgb(40, 199, 250))
            gravity = Gravity.CENTER
            setPadding(0, 10, 0, 10)
        }
        previewView = PreviewView(this).apply {
            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
        }
        val flip = Button(this).apply {
            text = "Alternar câmera"
            setOnClickListener {
                useFrontCamera = !useFrontCamera
                startCamera()
            }
        }
        val note = TextView(this).apply {
            text = "Versão inicial: pré-visualização local. A transmissão para PC/NDI ainda não está disponível."
            textSize = 12f
            setTextColor(android.graphics.Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 10, 0, 0)
        }
        root.addView(title, LinearLayout.LayoutParams(-1, -2))
        root.addView(status, LinearLayout.LayoutParams(-1, -2))
        root.addView(previewView, LinearLayout.LayoutParams(-1, 0, 1f))
        root.addView(flip, LinearLayout.LayoutParams(-1, -2))
        root.addView(note, LinearLayout.LayoutParams(-1, -2))
        setContentView(root)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED) startCamera()
        else permissionRequest.launch(Manifest.permission.CAMERA)
    }

    private fun startCamera() {
        status.text = "Iniciando câmera…"
        val future = ProcessCameraProvider.getInstance(this)
        future.addListener({
            try {
                provider = future.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val selector = if (useFrontCamera) CameraSelector.DEFAULT_FRONT_CAMERA
                    else CameraSelector.DEFAULT_BACK_CAMERA
                provider?.unbindAll()
                provider?.bindToLifecycle(this, selector, preview)
                status.text = if (useFrontCamera) "Câmera frontal ativa" else "Câmera traseira ativa"
            } catch (e: Exception) {
                status.text = "Não foi possível iniciar a câmera: ${e.localizedMessage ?: "erro"}"
            }
        }, ContextCompat.getMainExecutor(this))
    }

    override fun onDestroy() {
        provider?.unbindAll()
        super.onDestroy()
    }
}
