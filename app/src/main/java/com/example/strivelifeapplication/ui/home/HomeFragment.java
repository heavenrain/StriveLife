package com.example.strivelifeapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.strivelifeapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import com.example.strivelifeapplication.R;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ArrayList<Task> taskList;
    TaskAdapter taskAdapter = null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView listView = root.findViewById(R.id.listView1);

        taskList = new ArrayList<>();
        Task task = new Task("睡覺", "再睡五分鐘", 2, false, 0, null);
        taskList.add(task);
        task = new Task("運動", "健身大佬", 0, false, 0, null);
        taskList.add(task);
        task = new Task("喝水", "台南缺水", 1, false, 2000, null);
        taskList.add(task);

        taskAdapter = new TaskAdapter(requireContext(), taskList);
        listView.setAdapter(taskAdapter);

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}