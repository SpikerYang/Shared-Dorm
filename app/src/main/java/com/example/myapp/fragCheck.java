package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class fragCheck extends Fragment implements View.OnClickListener {
    private EditText etCheckPos;
    private ListView lvCheck;
    private appUser app;
    private Handler handler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_check, container, false);
        etCheckPos = view.findViewById(R.id.etCheckPos);
        lvCheck = view.findViewById(R.id.lvCheck);
        app = (appUser) getActivity().getApplication();
        view.findViewById(R.id.btnCheckLog).setOnClickListener(this);
        view.findViewById(R.id.btnCheckProperties).setOnClickListener(this);
        view.findViewById(R.id.btnCheckRequests).setOnClickListener(this);
        view.findViewById(R.id.btnCheckRooms).setOnClickListener(this);
        view.findViewById(R.id.btnCheckUsers).setOnClickListener(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                lvCheck.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.drawer_list_item, (String[]) msg.obj));
            }
        };

        return view;
    }


    @Override
    public void onClick(View v) {
        OkHttpClient client = new OkHttpClient();
        check(v.getId(), client);
    }

    private String[] jsonArrayToStringArray(JSONArray jsonArray) {
        int arraySize = jsonArray.length();
        String[] stringArray = new String[arraySize];
        for (int i = 0; i < arraySize; i++) {
            try {
                stringArray[i] = jsonArray.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return stringArray;
    }

    private void check(int id, OkHttpClient client) {
        String address = null;
        switch (id) {
            case  R.id.btnCheckLog:
                address = getString(R.string.checkLog);
                break;
            case R.id.btnCheckProperties:
                address = getString(R.string.checkProperties);
                break;
            case R.id.btnCheckRequests:
                address = getString(R.string.checkRequests);
                break;
            case R.id.btnCheckRooms:
                address = getString(R.string.checkRooms);
                break;
            case R.id.btnCheckUsers:
                address = getString(R.string.checkUsers);
                break;
        }
        JSONObject jsonPost = new JSONObject();
        try {
            jsonPost.put("jwt", app.getUser().getJwt());
            jsonPost.put("pos", etCheckPos.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , jsonPost.toString());
        Request request = new Request.Builder()
                .url(app.Server + address)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Check Log failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strRes = response.body().string();
                System.out.println(strRes);

                try {
                    JSONObject state = new JSONObject(strRes);
                    JSONArray log = state.getJSONArray("payload");
                    if (state.get("result").equals("success")) {
                        System.out.println(log);
                        Message msg = Message.obtain();
                        msg.obj = jsonArrayToStringArray(log);
                        handler.sendMessage(msg);
                    } else {
                        Toast.makeText(getActivity(), "Check log failed!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void checkProperties(OkHttpClient client) {
        JSONObject jsonPost = new JSONObject();
        try {
            jsonPost.put("jwt", app.getUser().getJwt());
            jsonPost.put("pos", etCheckPos.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , jsonPost.toString());
        Request request = new Request.Builder()
                .url(app.Server + getString(R.string.checkLog))
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Check Log failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strRes = response.body().string();
                System.out.println(strRes);

                try {
                    JSONObject state = new JSONObject(strRes);
                    JSONArray log = state.getJSONArray("payload");
                    if (state.get("result").equals("success")) {
                        System.out.println(log);
                        Message msg = Message.obtain();
                        msg.obj = jsonArrayToStringArray(log);
                        handler.sendMessage(msg);
                    } else {
                        Toast.makeText(getActivity(), "Check log failed!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
