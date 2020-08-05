package com.zzz.library_virtual_hosts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.zzz.library_virtual_hosts.utils.SharePreferenceMgr;
import com.zzz.library_virtual_hosts.vservice.VhostsService;

public class VHostsActivity extends AppCompatActivity implements SwitchButton.OnCheckedChangeListener {

    private static final int VPN_REQUEST_CODE = 0x0A;
    private boolean waitingForVPNStart;

    private TextView ipTv,domainTv;
    private SwitchButton vpnButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_vhosts);

        initViews();

        initData();
    }

    private void initViews() {
        ipTv = findViewById(R.id.hosts_ip_tv);
        domainTv = findViewById(R.id.hosts_domain_tv);

        vpnButton = findViewById(R.id.button_start_vpn);
        vpnButton.setOnCheckedChangeListener(this);
    }

    private void initData(){
        String ipValue = (String) SharePreferenceMgr.getInstance(this).get(HostsHelper.IP_VALUE_KEY,"");
        String domainValue = (String) SharePreferenceMgr.getInstance(this).get(HostsHelper.DOMAIN_VALUE_KEY,"");

        ipTv.setText("IP：" + ipValue);
        domainTv.setText("域名：" + domainValue);
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        if (isChecked){
            startVPN();
        }else{
            shutdownVPN();
        }
    }


    private void startVPN() {
        waitingForVPNStart = false;
        Intent vpnIntent = VhostsService.prepare(this);
        if (vpnIntent != null)
            startActivityForResult(vpnIntent, VPN_REQUEST_CODE);
        else
            onActivityResult(VPN_REQUEST_CODE, RESULT_OK, null);
    }

    private void shutdownVPN() {
        if (VhostsService.isRunning()){
            startService(new Intent(this, VhostsService.class).setAction(VhostsService.ACTION_DISCONNECT));
        }
        setButton(true);
    }


    private void setButton(boolean enable) {
        if (enable) {
            vpnButton.setChecked(false);
        } else {
            vpnButton.setChecked(true);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VPN_REQUEST_CODE && resultCode == RESULT_OK) {
            waitingForVPNStart = true;
            startService(new Intent(this, VhostsService.class).setAction(VhostsService.ACTION_CONNECT));
            setButton(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setButton(!waitingForVPNStart && !VhostsService.isRunning());
    }
}
