package com.example.bestplace_jav2.ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bestplace_jav2.R;
import com.example.bestplace_jav2.databinding.FragmentMapBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.location.FilteringMode;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.runtime.ui_view.ViewProvider;

public class MapFragment extends Fragment implements View.OnClickListener{

    private MapViewModel dashboardViewModel;
    private MapObjectCollection mapObjects;
    private FragmentMapBinding binding;
    private MapView mapview;
    Double longitude;
    Double latitude;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            findUserLocation();
        }else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        dashboardViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        mapview = (MapView) root.findViewById(R.id.mapview);
        mapview.getMap().move(
                new CameraPosition(new Point(latitude, longitude), 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        final TextView textView = new TextView(getActivity());  //создание текста поверх неё
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setTextColor(Color.RED);
        textView.setText("You are here");

        final ViewProvider viewProvider = new ViewProvider(textView); //погугли про placemarkobject это должно помочь
        //final PlacemarkMapObject viewPlacemark =
        mapObjects.addPlacemark(new Point(latitude, longitude), viewProvider);


        mapview.getMap().getMapObjects().addPlacemark(new Point(54.992000, 73.368600), ImageProvider.fromResource(getActivity(), R.drawable.green_mark));//надо навесить clicklistener
        mapview.getMap().getMapObjects().addPlacemark(new Point(55.005525, 73.333273), ImageProvider.fromResource(getActivity(), R.drawable.yellow_mark));
        mapview.getMap().getMapObjects().addPlacemark(new Point(54.987961, 73.358990), ImageProvider.fromResource(getActivity(), R.drawable.red_mark));
        mapview.getMap().getMapObjects().addPlacemark(new Point(54.985494, 73.386712), ImageProvider.fromResource(getActivity(), R.drawable.green_mark));

        return root;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {


        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    findUserLocation();


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void findUserLocation() {
        MapKit mapKit = MapKitFactory.getInstance();
        mapKit.createLocationManager().subscribeForLocationUpdates(0,0, 0, true, FilteringMode.ON, new LocationListener() {

            @Override
            public void onLocationUpdated(@NonNull Location location) {
                latitude=location.getPosition().getLatitude();
                longitude=location.getPosition().getLongitude();

            }

            @Override
            public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {

            }
        });
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

    @Override
    public void onClick(View view) {

    }
}