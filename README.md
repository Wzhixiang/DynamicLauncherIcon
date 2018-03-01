动态更换APP桌面图标

准备工作：多套Launcher图标（本例中另外添加了ic_launcher_1111和ic_launcher_1212两套启动图标）

实现原理：通过设置启动组件显示、隐藏桌面图标以达到目的。在Manifest.xml中体现在enabled属性上，
          在代码中是通过PackageManager的setComponentEnabledSetting（）方法控制

实现步骤：
    1.在Manifest.xml的application节点下配置启动标签
        <activity-alias
            android:name=".MainActivity1111"
            android:label="@string/app_name"
            android:enabled="false"
            android:icon="@mipmap/ic_launcher_1111"
            android:targetActivity=".MainActivity" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity-alias>

        <activity-alias
            android:name=".MainActivity1212"
            android:label="@string/app_name"
            android:enabled="false"
            android:icon="@mipmap/ic_launcher_1212"
            android:targetActivity=".MainActivity" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity-alias>

    2.更换桌面图标方法，通过PackageManager的setComponentEnabledSetting（）方法设置启动组件显示和隐藏
    本例中的更换桌面图标方法：
        kotlin写法
        fun changeLauncherIcon(activity: Activity, newComponentName: ComponentName) {
            var packageManager: PackageManager = activity.packageManager
            packageManager.setComponentEnabledSetting(activity.componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP)
            packageManager.setComponentEnabledSetting(newComponentName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP)
        }
        Java写法
        public static void changeLauncherIcon(Activity activity, ComponentName newComponentName){
            PackageManager packageManager = activity.getPackageManager;
            packageManager.setComponentEnabledSetting(activity.componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
            packageManager.setComponentEnabledSetting(newComponentName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
        }
    3.通过事件触发更换桌面图标方法（建议通过访问服务端的方法触发）
    本例在MainActivity的按钮事件中调用更换桌面图标方法changeLauncherIcon（）

    具体请看Demo，Demo为kotlin写法

知识点：
1.关于Manifest.xml属性说明
    name            可任意取值，只要能保证是唯一标识即可，为了方便管理建议有规律一些
    targetActivity	这个属性的值就是代表指向的是哪个Activity，而这个标签本身代表是该Activity的别名，记得指向的Activity要在该标签之前申明，否则可能运行不起来
    icon	        指的是该别名对应的应用图标
    label	        指的是该别名对应的应用名字
    enabled	        默认是true，true就会显示在桌面上，这里为了保证桌面只显示一个图标，则中的属性都是false，而在之后代码中动态控制这个属性，来显示和隐藏对应的图标
2.kotlin中使用databing
    需要在app的build.gradle中添加以下配置
    android{
        ……
        kapt {
            generateStubs = true
        }
    }

    dependencies {
        ……
        kapt "com.android.databinding:compiler:3.0.1"
    }
发现：
1.更换桌面图标不是马上生效，需要一段时间才生效（10S内）
2.当app不是默认图标时，再run 'app'，就会报错 'Session "app":Error Launching activity'