package com.zzz.virtualhostshelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.zzz.library_virtual_hosts.HostsHelper;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private Context mContext;
    private Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        initViews();

    }

    private void initViews(){

        findViewById(R.id.main_open_lib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开lib自带的控制界面
                HostsHelper.startHostsActivity(mContext,"47.104.156.81","abc.com");
            }
        });

        mSwitch = findViewById(R.id.main_switch);
        mSwitch.setOnCheckedChangeListener(this);
        findViewById(R.id.main_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
//            startVPN();

//            HostsHelper.prepareVPN(MainActivity.this);

//            HostsHelper.startVPN(MainActivity.this);

        }else{
//            shutdownVPN();
//            HostsHelper.shutdownVPN(MainActivity.this);
        }
    }

//    private void startVPN() {
//        Intent vpnIntent = VhostsService.prepare(this);
//        if (vpnIntent != null)
//            startActivityForResult(vpnIntent, VPN_REQUEST_CODE);
//        else
//            onActivityResult(VPN_REQUEST_CODE, RESULT_OK, null);2ce26e7a
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == HostsHelper.VPN_REQUEST_CODE && resultCode == RESULT_OK) {
//            HostsHelper.startVPN(MainActivity.this);
//        }else{
//            mSwitch.setChecked(false);
//        }
    }

//    private void shutdownVPN() {
//        if (VhostsService.isRunning())
//            startService(new Intent(this, VhostsService.class).setAction(VhostsService.ACTION_DISCONNECT));
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        HostsHelper.shutdownVPN(MainActivity.this);
    }
}
