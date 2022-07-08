package com.example.bestplace_jav2.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bestplace_jav2.R;
import com.example.bestplace_jav2.databinding.FragmentMapBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class MapFragment extends Fragment {

    private MapViewModel dashboardViewModel;
    private FragmentMapBinding binding;
    private MapView mapview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        mapview = (MapView) root.findViewById(R.id.mapview);
        mapview.getMap().move(
                new CameraPosition(new Point(54.992000, 73.368600), 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);

        mapview.getMap().getMapObjects().addPlacemark(new Point(54.992000, 73.368600), ImageProvider.fromResource(getActivity(), R.drawable.green_mark));
        mapview.getMap().getMapObjects().addPlacemark(new Point(55.005525, 73.333273), ImageProvider.fromResource(getActivity(), R.drawable.yellow_mark));
        mapview.getMap().getMapObjects().addPlacemark(new Point(54.987961, 73.358990), ImageProvider.fromResource(getActivity(), R.drawable.red_mark));
        mapview.getMap().getMapObjects().addPlacemark(new Point(54.985494, 73.386712), ImageProvider.fromResource(getActivity(), R.drawable.green_mark));

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
        super.onStart();
    }

    @Override
    public void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }
}