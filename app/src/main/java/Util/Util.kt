package Util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import kgpa.finflow.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.Objects

class Util {
    companion object {
        var apiURL = "http://delicias-001-site4.rtempurl.com/"

        fun openActivity(context: Context, objClass: Class<*>){
            val intent = Intent(context, objClass)
            context.startActivity(intent)
        }

        fun parseStringToDateModern(dateString: String, pattern: String): LocalDate? {
            return try {
                val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
                LocalDate.parse(dateString, formatter)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        fun parseDateToString(date: LocalDate, pattern: String): String {
            val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
            return date.format(formatter)
        }

        fun showDialogCondition(context: Context, questionText: String, callback: () ->  Unit){
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage(questionText)
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.TextYes), DialogInterface.OnClickListener{
                        dialog, id -> callback()
                })
                .setNegativeButton(context.getString(R.string.TextNo), DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.setTitle(context.getString(R.string.TextTitleDialogQuestion))
            alert.show()
        }
    }
}
