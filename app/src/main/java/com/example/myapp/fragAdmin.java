package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class fragAdmin extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_admin, container, false);
        appUser app = new appUser();
        TextView tvAdminUsername = view.findViewById(R.id.tvAdminUsername);
        TextView tvAdminId = view.findViewById(R.id.tvAdminId);
        TextView tvAdminEmail = view.findViewById(R.id.tvAdminEmail);
        TextView tvAdminPhone = view.findViewById(R.id.tvAdminPhone);
        if (app.isLogin()) {
            tvAdminUsername.setText("Username: " + app.getUser().getUsername());
            tvAdminId.setText("stdId: " + app.getUser().getId());
            tvAdminEmail.setText("Email: " + app.getUser().getEmail());
            tvAdminPhone.setText("Phone: " + app.getUser().getPhone());
        } else {
            Toast.makeText(getActivity(),"Login failed!",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), atyLogin.class));
        }
        return view;
    }
}
