package proofofconcept.dontsuemeplz.timber;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import org.json.JSONObject;



public class Main implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.tinder")){
            return;
        }


        //XposedBridge.log("We are in Tinder!");

        findAndHookMethod("com.tinder.activities.ActivityMain", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Activity profile_activity = (Activity)param.thisObject;
                Toast.makeText(profile_activity.getApplicationContext(), "Timber is enabled :D", Toast.LENGTH_SHORT).show();
            }
        });

        findAndHookMethod("com.tinder.managers.r", lpparam.classLoader, "ad", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //XposedBridge.log("Tinder KEY_TINDER_PLUS_ENABLED boolean");
                param.setResult(true);
            }
        });

        findAndHookMethod("com.tinder.managers.r", lpparam.classLoader, "ab", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //XposedBridge.log("Tinder KEY_HAS_PLUS_SUBSCRIPTION_ENABLED boolean");
                param.setResult(true);
            }
        });

        findAndHookMethod("com.tinder.managers.r", lpparam.classLoader, "A", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //XposedBridge.log("Tinder LONGITUDE double:"+param.getResult());
            }
        });

        findAndHookMethod("com.tinder.managers.r", lpparam.classLoader, "z", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //XposedBridge.log("Tinder LATITUDE double:"+param.getResult());
            }
        });

        findAndHookMethod("com.tinder.managers.l", lpparam.classLoader, "d", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //XposedBridge.log("userTraveling boolean:"+param.getResult());
                //param.setResult(true);
            }
        });

        findAndHookMethod("com.tinder.activities.ActivityMain", lpparam.classLoader, "a", int.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //XposedBridge.log("Tinder Swipe Limit Roadblock boolean");
                param.setResult(true);
            }
        });

        //findAndHookMethod("com.tinder.parse.e", lpparam.classLoader, "c", org.json.JSONObject.class, new XC_MethodHook() {
        //    @Override
        //    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        //        JSONObject var0 = (JSONObject) param.thisObject;
        //        if(var0 != null && var0.toString()!= null) {
        //            XposedBridge.log("Tinder JSON");
        //            XposedBridge.log(var0.toString());
        //        }
        //var0.put("administrative_area_level_2","Value");
        //var0.put("locality","Value");
        //var0.put("street_address","Tinder Land ;)");
        //var0.put("lat",33.811113D);
        //var0.put("lon",-117.921584D);
        //    }
        //});
    }
}