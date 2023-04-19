package com.example.trabajopracticoanexoc;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Alerts {

    public static void showExitDialog(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Salir")
                .setMessage("¿Desea cerrar sesión?")
                .setPositiveButton("Si", (dialog, which) -> ((AppCompatActivity) context).finishAndRemoveTask())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
