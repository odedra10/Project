**Java File:-*  package com.example.videocapture;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int VIDEO_CAPTURE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button recordVideoButton = findViewById(R.id.recordVideoButton);

        recordVideoButton.setOnClickListener(v -> {
            Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (videoIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(videoIntent, VIDEO_CAPTURE_REQUEST_CODE);
            } else {
                Toast.makeText(this, "No camera app available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VIDEO_CAPTURE_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            Toast.makeText(this, "Video saved to: " + videoUri, Toast.LENGTH_LONG).show();
            // Do something with the videoUri (e.g., play it, upload it, etc.)
        } else {
            Toast.makeText(this, "Video recording canceled", Toast.LENGTH_SHORT).show();
        }
    }
}
