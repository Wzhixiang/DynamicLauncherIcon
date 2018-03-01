package com.wzx.dynamiclaunchericon

import android.app.Activity
import android.content.ComponentName
import android.content.pm.PackageManager

/**
 * 描述 TODO
 * Created by Administrator on 2018/3/1.
 */
fun changeLauncherIcon(activity: Activity, newComponentName: ComponentName) {
    var packageManager: PackageManager = activity.packageManager
    packageManager.setComponentEnabledSetting(activity.componentName,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP)
    packageManager.setComponentEnabledSetting(newComponentName,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP)
}