package com.example.best_place_jav_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Circle;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polygon;
import com.yandex.mapkit.geometry.Polyline;
import com.yandex.mapkit.map.BaseMapObjectCollection;
import com.yandex.mapkit.map.Callback;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CircleMapObject;
import com.yandex.mapkit.map.ClusterListener;
import com.yandex.mapkit.map.ClusterizedPlacemarkCollection;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectCollectionListener;
import com.yandex.mapkit.map.MapObjectDragListener;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.MapObjectVisitor;
import com.yandex.mapkit.map.ModelStyle;
import com.yandex.mapkit.map.PlacemarkAnimation;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.PlacemarksStyler;
import com.yandex.mapkit.map.PolygonMapObject;
import com.yandex.mapkit.map.PolylineMapObject;
import com.yandex.mapkit.map.TextStyle;
import com.yandex.mapkit.map.internal.PlacemarkMapObjectBinding;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.AnimatedImageProvider;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.runtime.model.AnimatedModelProvider;
import com.yandex.runtime.model.ModelProvider;
import com.yandex.runtime.ui_view.ViewProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MapView mapview;
    private MapObjectCollection mapObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("ad3baf25-3c25-434c-a9b9-adc1f6be7441");
        MapKitFactory.initialize(this);

        setContentView(R.layout.activity_main);
        mapview=findViewById(R.id.mapview); //создание пустой карты
        mapview.getMap().move(
                new CameraPosition(new Point(54.992000,73.368600),13.0f,0.0f,0.0f),
                new Animation(Animation.Type.LINEAR,0),null);
        mapObjects = mapview.getMap().getMapObjects().addCollection();

        final TextView textView = new TextView(this);  //создание текста поверх неё (скорей всего бесполезно)
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setTextColor(Color.RED);
        textView.setText("Hellu ");

        final ViewProvider viewProvider = new ViewProvider(textView); //погугли про placemarkobject это должно помочь создать маркер
        final PlacemarkMapObject viewPlacemark =
                mapObjects.addPlacemark(new Point(54.992000, 73.368600), viewProvider);


        viewProvider.snapshot();     //не нужные 2 строчки я не понял что онидолжны делать
        viewPlacemark.setView(viewProvider);


        



    }


    @Override
    protected void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapview.onStart();
        MapKitFactory.getInstance().onStart();
    }
}