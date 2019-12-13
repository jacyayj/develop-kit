package com.jacy.kit.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat.startActivityForResult
import com.jacy.kit.config.Action.INSTALL_APK
import com.jacy.kit.config.toast
import com.jacy.kit.utils.log
import com.vondear.rxtool.RxActivityTool
import com.vondear.rxtool.RxAppTool

class DownLoadReceiver :
    BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            val query = DownloadManager.Query()
            query.setFilterById(id)
            val dm by lazy {
                context.getSystemService(Context.DOWNLOAD_SERVICE)?.let { it as DownloadManager }
            }
            toast("下载完成")
            dm?.query(query)?.let {
                try {
                    if (it.moveToFirst()) {
                        val filename = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                            Uri.parse(it.getString(it.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)))
                                .path
                        } else {
                            it.getString(it.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME))
                        }
                        val status = it.getInt(it.getColumnIndex(DownloadManager.COLUMN_STATUS))
                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                if (context.packageManager.canRequestPackageInstalls())
                                    RxAppTool.installApp(
                                        RxActivityTool.currentActivity(),
                                        filename,
                                        0x01
                                    )
                                else {
                                    toast("安装APK需打开相应权限，请前往开启")
                                    startActivityForResult(
                                        RxActivityTool.currentActivity(),
                                        Intent(
                                            Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                                            Uri.parse("package:${context.packageName}")
                                        ), INSTALL_APK, null
                                    )
                                }
                            } else {
                                RxAppTool.installApp(
                                    RxActivityTool.currentActivity(),
                                    filename,
                                    0x01
                                )
                            }
                        }
                    }
                } catch (e: Exception) {
                    toast("安装异常")
                    e.printStackTrace()
                    log.v(e.message)
                    return
                } finally {
                    it.close()
                }
            } ?: toast("安装包已被删除")
        }
    }
}