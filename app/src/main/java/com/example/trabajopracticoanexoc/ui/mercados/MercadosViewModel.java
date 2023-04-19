package com.example.trabajopracticoanexoc.ui.mercados;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MercadosViewModel extends ViewModel {

    private static final LatLng CARREFOUR = new LatLng(-32.89417246773614, -68.8450548700735);
    private static final LatLng COTO = new LatLng(-32.87178995345943, -68.8439649732252);
    private static final LatLng CHANGOMAS = new LatLng(-32.95176261420922, -68.85886732574677);
    private MutableLiveData<MapaActual> mapa;

    public LiveData<MapaActual> getMapa() {
        if (mapa == null) {
            mapa = new MutableLiveData<MapaActual>();
        }
        return mapa;
    }

    public void marcar() { this.mapa.setValue(new MapaActual()); }

    public class MapaActual implements OnMapReadyCallback {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.addMarker(new MarkerOptions().position(CARREFOUR).title("Carrefour Market Colón"));
            googleMap.addMarker(new MarkerOptions().position(COTO).title("Coto Parque Central"));
            googleMap.addMarker(new MarkerOptions().position(CHANGOMAS).title("Changomas Palmares"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(CARREFOUR)
                    .zoom(15)
                    .bearing(45)
                    .tilt(70)
                    .build();

            CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(update);
        }
    }
}