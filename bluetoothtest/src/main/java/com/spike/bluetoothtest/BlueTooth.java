package com.spike.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BlueTooth extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView tvDevice;
    private ListView lvDevices;
    private BluetoothAdapter mBluetoothAdapter;
    private List<String> bluetoothDevices = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    private final UUID MY_UUID = UUID.randomUUID();

    private BluetoothSocket clientSocket;
    private BluetoothDevice device;
    private OutputStream os;  // client OutputStream
    private InputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); //create a adapter
        tvDevice = findViewById(R.id.tvDevices);

        lvDevices = findViewById(R.id.lvDevices);
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for(BluetoothDevice device:pairedDevices) {
                bluetoothDevices.add(device.getName() + ":" + device.getAddress());

            }
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1, bluetoothDevices);
        lvDevices.setAdapter(arrayAdapter);
        lvDevices.setOnItemClickListener(this);


        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);
        findViewById(R.id.btnSearch).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction())) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    bluetoothDevices.add(device.getName() + ":" + device.getAddress());
                    arrayAdapter.notifyDataSetChanged();
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(intent.getAction())) {
                setProgressBarVisibility(false);
                setTitle("search completed!");
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                setProgressBarIndeterminateVisibility(true);
                setTitle("scanning...");
                if (mBluetoothAdapter.isDiscovering()) {
                    mBluetoothAdapter.cancelDiscovery();
                }
                mBluetoothAdapter.startDiscovery();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = arrayAdapter.getItem(position);                  // get the list of devices
        String address  = s.substring(s.indexOf(":") + 1).trim();   // get the address of devices

        try {
            if(mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
            try {
                if (device == null) {
                    device = mBluetoothAdapter.getRemoteDevice(address);              // create BT device 获取需要连接的设备的地址
                }
                if (clientSocket == null) {
                    clientSocket = device.createRfcommSocketToServiceRecord(MY_UUID); // create BT server
                    clientSocket.connect();                                           // make connection if device and server had been bonded 发起连接请求

//                    is = clientSocket.getInputStream();
                    os = clientSocket.getOutputStream();                              //获得输出流 通过os发送数据到服务端
                    is = clientSocket.getInputStream();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (os != null) {
                os.write("msg go !".getBytes("utf-8"));                  // send msg
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
