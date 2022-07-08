package com.example.bestplace_jav2.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bestplace_jav2.R;
import com.example.bestplace_jav2.RegActivity;
import com.example.bestplace_jav2.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private ProfileViewModel homeViewModel;
    private FragmentProfileBinding binding;
    View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //view = inflater.inflate(R.layout.fragment_profile, container, false);
        Button button=binding.button;
        button.setOnClickListener(this);
        final TextView textView = binding.textProfile;

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Вы не авторизовались.Хотите войти в профиль?");

            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(),"ttt",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getActivity(),RegActivity.class);
        startActivity(intent);
    }
}