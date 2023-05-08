package com.inviz.base.ui

import android.app.AlertDialog
import android.app.Dialog
import androidx.fragment.app.Fragment
import com.inviz.base.R
import com.inviz.base.presentation.BaseViewModel

abstract class BaseFragment : Fragment() {
    protected var dialog: Dialog? = null
    protected abstract val viewModel: BaseViewModel

    protected fun showErrorDialog(error: Throwable) {
        hideErrorDialog()
        dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.error_dialog_title)
            .setMessage(error.message ?: getString(R.string.error_dialog_unknown_error))
            .setPositiveButton(R.string.error_dialog_ok) { _, _ ->
                onErrorDialogDismissed()
            }
            .setCancelable(false)
            .create()
        dialog?.show()
    }

    protected fun hideErrorDialog() {
        dialog?.dismiss()
        dialog = null
    }

    protected open fun onErrorDialogDismissed() {
        hideErrorDialog()
    }

}