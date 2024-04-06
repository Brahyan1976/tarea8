package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Asegúrate de importar tus clases Event, EventDao y AppDatabase.

public class AddEventActivity extends AppCompatActivity {

    private EditText editTextEventTitle, editTextEventDate, editTextEventDescription;
    private Button buttonSaveEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        editTextEventTitle = findViewById(R.id.editTextEventTitle);
        editTextEventDate = findViewById(R.id.editTextEventDate);
        editTextEventDescription = findViewById(R.id.editTextEventDescription);
        buttonSaveEvent = findViewById(R.id.buttonSaveEvent);

        buttonSaveEvent.setOnClickListener(view -> saveEvent());
    }

    private void saveEvent() {
        String title = editTextEventTitle.getText().toString().trim();
        String date = editTextEventDate.getText().toString().trim();
        String description = editTextEventDescription.getText().toString().trim();

        // Validar los datos de entrada
        if (!validateInput(title, date, description)) {
            return; // Detiene la ejecución si la validación falla
        }

        // Crear el objeto Event con los datos recogidos
        Event event = new Event(title, date, description);

        // Insertar el evento en la base de datos en un nuevo hilo
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.eventDao().insert(event);

            // Mostrar mensaje de éxito y cerrar actividad
            runOnUiThread(() -> {
                Toast.makeText(getApplicationContext(), "Evento guardado con éxito", Toast.LENGTH_LONG).show();
                finish(); // Cierra la actividad y regresa a la anterior
            });
        }).start();
    }

    private boolean validateInput(String title, String date, String description) {
        if (title.isEmpty()) {
            editTextEventTitle.setError("El título no puede estar vacío");
            editTextEventTitle.requestFocus();
            return false;
        }
        if (date.isEmpty()) {
            editTextEventDate.setError("La fecha no puede estar vacía");
            editTextEventDate.requestFocus();
            return false;
        }
        if (description.isEmpty()) {
            editTextEventDescription.setError("La descripción no puede estar vacía");
            editTextEventDescription.requestFocus();
            return false;
        }
        // Aquí puedes añadir más validaciones según sea necesario
        return true;
    }
}
