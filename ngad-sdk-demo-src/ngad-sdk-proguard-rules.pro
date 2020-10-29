# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\env\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepattributes SourceFile,LineNumberTable
-keepattributes Signature
-keepattributes *Annotation*

## common
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service

-keep class android.app.**{*;}
-dontwarn  android.app.**

-keep class android.support.v7.media.*{public *;}
-keep class android.support.v4.** { *; }
-dontwarn android.support.**

## network libs
-keep class android.net.http.** { *; }
-dontwarn android.net.**
-dontnote android.net.http.*

-keep class org.apache.http.** { *; }
-dontwarn org.apache.**
-dontnote org.apache.commons.codec.**
-dontnote org.apache.http.**

# Keep native methods
-keepclasseswithmembers class * {
    native <methods>;
}

### utdid
-keep class com.ta.utdid2.**{*;}
-keep class com.ut.device.**{*;}
-dontwarn com.ta.utdid2.**
-dontwarn com.ut.device.**

# Keep ngad-sdk classes
-keep interface cn.sirius.nga.** {*; }
-keep class cn.sirius.nga.** {*; }
-dontwarn cn.sirius.nga.**

-keep class cn.ninegame.library.** {*; }
-dontwarn cn.ninegame.library.**

-keep class cn.uc.gamesdk.** {*; }
-dontwarn cn.uc.gamesdk.**

-keep class com.qq.e.** {*; }
-dontwarn com.qq.e.**

-keep class com.taobao.** {*; }
-dontwarn com.taobao.**
-keep class android.taobao.** {*; }
-dontwarn android.taobao.**

-keep class com.UCMobile.Apollo.**{*;}

-dontwarn com.mobvista.**
-keep class com.mobvista.** {*; }
-keep interface com.mobvista.** {*; }
-keep class **.R$* { public static final int mobvista*; }
-keep class com.alphab.** {*; }
-keep interface com.alphab.** {*; }

-dontwarn com.lm.**
-keep class com.lm.** { *; }

-dontwarn com.uniplay.**
-keep class com.uniplay.** { *; }
-dontwarn com.joomob.**
-keep class com.joomob.** { *; }

-keep class com.inmobi.** { *; }
-dontwarn com.inmobi.**
-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**
-dontwarn com.squareup.picasso.**
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{
     public *;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{
     public *;
}
# skip the Picasso library classes
-keep class com.squareup.picasso.** {*;}
-dontwarn com.squareup.picasso.**
-dontwarn com.squareup.okhttp.**
# skip Moat classes
-keep class com.moat.** {*;}
-dontwarn com.moat.**
# skip AVID classes
-keep class com.integralads.avid.library.** {*;}

-keep class com.bytedance.sdk.openadsdk.** { *; }
-keep public interface com.bytedance.sdk.openadsdk.downloadnew.** {*;}
-keep class com.pgl.sys.ces.* {*;}

-keep class cn.huawei.** {
    *;
}
-keep class com.huawei.** {
    *;
}