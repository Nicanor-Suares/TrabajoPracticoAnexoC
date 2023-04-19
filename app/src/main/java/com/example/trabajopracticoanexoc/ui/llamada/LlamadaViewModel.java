package com.example.trabajopracticoanexoc.ui.llamada;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.Manifest;

public class LlamadaViewModel extends ViewModel {

    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void hacerLlamada(String phoneNumber, Context context) {

        if (TextUtils.isEmpty(phoneNumber)) {
            errorMessage.setValue("Por favor ingrese un numero de telefono.");
            return;
        }

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            errorMessage.setValue("Permiso para realizar llamadas no concedido.");
            return;
        }
        context.startActivity(callIntent);
    }
}
