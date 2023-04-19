package com.example.trabajopracticoanexoc.ui.llamada;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.Manifest;
import com.example.trabajopracticoanexoc.R;
import com.example.trabajopracticoanexoc.databinding.FragmentLlamadaBinding;

public class LlamadaFragment extends Fragment {

    private static final int CALL_PHONE_PERMISSION_REQUEST_CODE = 100;
    private LlamadaViewModel viewModel;
    private EditText phoneNumberEditText;

    public LlamadaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_llamada, container, false);

        // Traer numero y boton
        phoneNumberEditText = view.findViewById(R.id.phone_number);
        Button callButton = view.findViewById(R.id.call_button);

        // Chequear si tenemos permisos para realizar llamadas
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Si no, pedir permiso
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_REQUEST_CODE);
        } else {
            // Si tenemos permiso para llamar, activar el boton
            callButton.setEnabled(true);
        }

        viewModel = new ViewModelProvider(this).get(LlamadaViewModel.class);

        // Listener para la llamada
        callButton.setOnClickListener(v -> {
            String phoneNumber = phoneNumberEditText.getText().toString();
            viewModel.hacerLlamada(phoneNumber, requireContext());
        });

        // Observar el mensade de error del LiveData y mostrarlo
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
