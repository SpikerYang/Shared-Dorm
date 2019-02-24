package com.example.myapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ServiceConnection {
    private BluetoothAdapter mBluetoothAdapter;
    private TextView tvDevices;
    private TextView result;
    private Intent i_service;
    private EditText etData;
    private testService.Binder binder;
    private TextView tvOut;
    private MyReceiver1 myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* bluetooth test */
//        tvDevices = findViewById(R.id.tvDevices);
//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();                    // create a default Adapter
//        Set<BluetoothDevice> pairedBluetooth = mBluetoothAdapter.getBondedDevices(); // create a set to include devices which had been bonded
//        if (pairedBluetooth.size() > 0) {
//            for (BluetoothDevice device: pairedBluetooth) {
//                tvDevices.append(device.getName() + ":" + device.getAddress());
//            }
//        }
//        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        this.registerReceiver(receiver, filter);
//        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//        this.registerReceiver(receiver, filter);
//        findViewById(R.id.btnSearchBt).setOnClickListener(this);
        /* bluetooth test */
        super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_main);
        setContentView(R.layout.my_layout);
        result = findViewById(R.id.result);
//        findViewById(R.id.btnStartAnotherAty).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,AnotherAty.class);
////                i.putExtra("data","hello 12345");
////                Bundle b = new Bundle();
////                b.putString("name","yqr")0;
////                b.putInt("age",2);
////                i.putExtras(b);
//
//                i.putExtra("user", new User("222",4));
//
////                startActivity(i);
//
//                startActivityForResult(i,0);
//            }
//        });
        /* test Service */
        findViewById(R.id.btnStartAnotherAty).setOnClickListener(this);
        i_service = new Intent(MainActivity.this, testService.class);
        etData = findViewById(R.id.editText);
        tvOut = findViewById(R.id.tvOut);
        findViewById(R.id.btnStartService).setOnClickListener(this);
        findViewById(R.id.btnStopService).setOnClickListener(this);
        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnbindService).setOnClickListener(this);
        findViewById(R.id.btnSyncData).setOnClickListener(this);
        /* test Service */
        /* test Broadcaster Receiver */
        findViewById(R.id.btnSendMsg).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
        findViewById(R.id.btnUnregister).setOnClickListener(this);
        /* test Broadcaster Receiver */
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        result.setText("send back:"+data.getStringExtra("data"));
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartAnotherAty:
                startActivity(new Intent(MainActivity.this, AnotherAty.class));
                break;
            case R.id.btnStartService:
                i_service.putExtra("i",Integer.parseInt(etData.getText().toString()));
                startService(i_service);
                break;
            case R.id.btnStopService:
                stopService(i_service);
                break;
            case R.id.btnBindService:

                bindService(i_service, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                break;
            case R.id.btnSyncData:
                if (binder != null) {
                    binder.setData(etData.getText().toString());
                }
//          case R.id.btnSearchBt:
//              onClick_search();
            case R.id.btnSendMsg:
                Intent i = new Intent(MyReceiver1.ACTION);
                sendBroadcast(i);
                break;
            case R.id.btnRegister:
                if (myReceiver == null) {
                    myReceiver = new MyReceiver1();
                    registerReceiver(myReceiver, new IntentFilter(MyReceiver1.ACTION));
                }
                break;
            case R.id.btnUnregister:
                unregisterReceiver(myReceiver);
                break;
        }
    }
//    public void onClick_search() {
//        setProgressBarIndeterminateVisibility(true);
//        setTitle("scanning...");
//        if (mBluetoothAdapter.isDiscovering()) {
//            mBluetoothAdapter.cancelDiscovery();
//        }
//        mBluetoothAdapter.startDiscovery();
//    }
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction())) {
//                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
//                    tvDevices.append(device.getName() + ":" + device.getName());
//                }
//            }
//            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(intent.getAction())) {
//                setProgressBarVisibility(false);
//                setTitle("search completed!");
//            }
//        }
//    };
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("Service connected!");
        binder = (testService.Binder)service;
    }
    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}









