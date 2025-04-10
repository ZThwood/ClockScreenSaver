package com.clockscreensaver.learn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.ui.AppBarConfiguration
import com.example.clockscreensaver.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val account = binding.accountEdit.text.toString()
            val password = binding.passwordEdit.text.toString()

            if (account == "admin" && password == "123456") {
                // 获取 MainActivity 的 Class 对象
                val intent = Intent(this, MainActivity::class.java)
                // 启动 Activity
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "账号或密码错误!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}