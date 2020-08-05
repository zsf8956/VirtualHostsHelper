package com.zzz.library_virtual_hosts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.zzz.library_virtual_hosts.utils.SharePreferenceMgr;
import com.zzz.library_virtual_hosts.vservice.VhostsService;


public class HostsHelper {
    public static final String IP_VALUE_KEY = "ip_value_key";
    public static final String DOMAIN_VALUE_KEY = "domain_value_key";

//    public static final int VPN_REQUEST_CODE = 0x0F;

//    /**
//     * 初始化vpn服务
//     * @param activity
//     */
//    public static void prepareVPN(Activity activity){
//
//        Intent vpnIntent = VhostsService.prepare(activity);
//        if (vpnIntent != null){
//            activity.startActivityForResult(vpnIntent, VPN_REQUEST_CODE);
//        }else{
//            startVPN(activity);
//        }
//
//    }
//
//    /**
//     * 打开vpn服务
//     * @param activity
//     */
//    public static void startVPN(Activity activity){
//        activity.startService(new Intent(activity, VhostsService.class).setAction(VhostsService.ACTION_CONNECT));
//
//    }
//
    /**
     * 关闭vpn服务
     * @param activity
     */
    public static void shutdownVPN(Activity activity){
        if (VhostsService.isRunning()){
            activity.startService(new Intent(activity, VhostsService.class).setAction(VhostsService.ACTION_DISCONNECT));
        }
    }


    public static void startHostsActivity(Context context,String ipValue,String domainValue){
        if (TextUtils.isEmpty(ipValue) || TextUtils.isEmpty(domainValue)){
            Toast.makeText(context, "IP或域名信息有误", Toast.LENGTH_SHORT).show();
            return;
        }
        SharePreferenceMgr.getInstance(context).put(IP_VALUE_KEY,ipValue);
        SharePreferenceMgr.getInstance(context).put(DOMAIN_VALUE_KEY,domainValue);

        Intent intent = new Intent(context,VHostsActivity.class);
        context.startActivity(intent);
    }
}
