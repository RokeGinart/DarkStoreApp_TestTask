package com.example.darkstoreapp_testtask.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.darkstoreapp_testtask.R
import com.example.darkstoreapp_testtask.databinding.InfoMessageLayoutBinding

class InfoMessage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding: InfoMessageLayoutBinding = InfoMessageLayoutBinding
        .inflate(LayoutInflater.from(context), this)

    fun showMessage(text: String, isError: Boolean) {
        with(binding) {
            if (isError) {
                messageText.apply {
                    this.text = text
                    this.setTextColor(ContextCompat.getColor(context, R.color.red))
                }
                messageBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
                messageIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_error_message
                    )
                )
            } else {
                messageText.apply {
                    this.text = text
                    this.setTextColor(ContextCompat.getColor(context, R.color.green))
                }
                messageBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.green
                    )
                )
                messageIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_success_message
                    )
                )
            }
        }
    }
}