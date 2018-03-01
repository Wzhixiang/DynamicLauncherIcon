
### 动态更换APP桌面图标<br />
<br />
准备工作：多套Launcher图标（本例中另外添加了ic_launcher_1111和ic_launcher_1212两套启动图标）<br />
<br />
实现原理：通过设置启动组件显示、隐藏桌面图标以达到目的。在Manifest.xml中体现在enabled属性上，<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 在代码中是通过PackageManager的setComponentEnabledSetting（）方法控制<br />
<br />
实现步骤：<br />
&nbsp; &nbsp; 1.在Manifest.xml的application节点下配置启动标签<br />
&nbsp; &nbsp; &nbsp; &nbsp; &lt;activity-alias<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; android:name=".MainActivity1111"<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; android:label="@string/app_name"<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; android:enabled="false"<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; android:icon="@mipmap/ic_launcher_1111"<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; android:targetActivity=".MainActivity" &gt;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;intent-filter&gt;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;action android:name="android.intent.action.MAIN"/&gt;<br />
<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;category android:name="android.intent.category.LAUNCHER"/&gt;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/intent-filter&gt;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &lt;/activity-alias&gt;<br />
<br />
&nbsp; &nbsp; &nbsp; &nbsp; &lt;activity-alias<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; android:name=".MainActivity1212"<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; android:label="@string/app_name"<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; android:enabled="false"<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; android:icon="@mipmap/ic_launcher_1212"<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; android:targetActivity=".MainActivity" &gt;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;intent-filter&gt;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;action android:name="android.intent.action.MAIN"/&gt;<br />
<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;category android:name="android.intent.category.LAUNCHER"/&gt;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &lt;/intent-filter&gt;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &lt;/activity-alias&gt;<br />
<br />
&nbsp; &nbsp; 2.更换桌面图标方法，通过PackageManager的setComponentEnabledSetting（）方法设置启动组件显示和隐藏<br />
&nbsp; &nbsp; 本例中的更换桌面图标方法：<br />
&nbsp; &nbsp; &nbsp; &nbsp; kotlin写法<br />
&nbsp; &nbsp; &nbsp; &nbsp; fun changeLauncherIcon(activity: Activity, newComponentName: ComponentName) {<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; var packageManager: PackageManager = activity.packageManager<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; packageManager.setComponentEnabledSetting(activity.componentName,<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; PackageManager.COMPONENT_ENABLED_STATE_DISABLED,<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; PackageManager.DONT_KILL_APP)<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; packageManager.setComponentEnabledSetting(newComponentName,<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; PackageManager.COMPONENT_ENABLED_STATE_ENABLED,<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; PackageManager.DONT_KILL_APP)<br />
&nbsp; &nbsp; &nbsp; &nbsp; }<br />
&nbsp; &nbsp; &nbsp; &nbsp; Java写法<br />
&nbsp; &nbsp; &nbsp; &nbsp; public static void changeLauncherIcon(Activity activity, ComponentName newComponentName){<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; PackageManager packageManager = activity.getPackageManager;<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; packageManager.setComponentEnabledSetting(activity.componentName,<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; PackageManager.COMPONENT_ENABLED_STATE_DISABLED,<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; PackageManager.DONT_KILL_APP);<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; packageManager.setComponentEnabledSetting(newComponentName,<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; PackageManager.COMPONENT_ENABLED_STATE_ENABLED,<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; PackageManager.DONT_KILL_APP);<br />
&nbsp; &nbsp; &nbsp; &nbsp; }<br />
&nbsp; &nbsp; 3.通过事件触发更换桌面图标方法（建议通过访问服务端的方法触发）<br />
&nbsp; &nbsp; 本例在MainActivity的按钮事件中调用更换桌面图标方法changeLauncherIcon（）<br />
<br />
&nbsp; &nbsp; 具体请看Demo，Demo为kotlin写法<br />
<br />
知识点：<br />
&nbsp; &nbsp; 1.关于Manifest.xml属性说明<br />
&nbsp; &nbsp; name&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 可任意取值，只要能保证是唯一标识即可，为了方便管理建议有规律一些<br />
&nbsp; &nbsp; targetActivity<span> </span>这个属性的值就是代表指向的是哪个Activity，而这个标签本身代表是该Activity的别名，<br />
&nbsp; &nbsp; 记得指向的Activity要在该标签之前申明，否则可能运行不起来<br />
&nbsp; &nbsp; icon<span> </span>&nbsp; &nbsp; &nbsp; &nbsp; 指的是该别名对应的应用图标<br />
&nbsp; &nbsp; label<span> </span>&nbsp; &nbsp; &nbsp; &nbsp; 指的是该别名对应的应用名字<br />
&nbsp; &nbsp; enabled<span> </span>&nbsp; &nbsp; &nbsp; &nbsp; 默认是true，true就会显示在桌面上，这里为了保证桌面只显示一个图标，则中的属性都<br />
&nbsp; &nbsp; 是false，而在之后代码中动态控制这个属性，来显示和隐藏对应的图标<br />
&nbsp; &nbsp; 2.kotlin中使用databing<br />
&nbsp; &nbsp; 需要在app的build.gradle中添加以下配置<br />
&nbsp; &nbsp; android{<br />
&nbsp; &nbsp; &nbsp; &nbsp; ……<br />
&nbsp; &nbsp; &nbsp; &nbsp; kapt {<br />
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; generateStubs = true<br />
&nbsp; &nbsp; &nbsp; &nbsp; }<br />
&nbsp; &nbsp; }<br />
<br />
&nbsp; &nbsp; dependencies {<br />
&nbsp; &nbsp; &nbsp; &nbsp; ……<br />
&nbsp; &nbsp; &nbsp; &nbsp; kapt "com.android.databinding:compiler:3.0.1"<br />
&nbsp; &nbsp; }<br />
发现：<br />
1.更换桌面图标不是马上生效，需要一段时间才生效（10S内）<br />
2.当app不是默认图标时，再run 'app'，就会报错 'Session "app":Error Launching activity'<br />
	<div>
		<br />
	</div>
</p>
<p>
<br />
