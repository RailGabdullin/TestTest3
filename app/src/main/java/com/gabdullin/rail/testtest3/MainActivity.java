package com.gabdullin.rail.testtest3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.gabdullin.rail.testtest3.data.WinnersAppModel;
import com.gabdullin.rail.testtest3.ui.RecyclerFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSION_REQUEST_CODE = 530;
    private Bundle savedInstanceStateBundle;

    @Inject
    Presenter presenter;
    @Inject
    WinnersAppModel winnersAppModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstanceStateBundle = savedInstanceState;
        if(checkPermissions()){
            startApp(savedInstanceStateBundle);
        }
        //Проверка хешкода от Facebook
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.gabdullin.rail.testtest3",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
    }

    private boolean checkPermissions() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_CODE);
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allPermissionsGranted = true;
            for (int i : grantResults) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) allPermissionsGranted = false;
            }
            if (grantResults.length > 0 && allPermissionsGranted) {
                startApp(savedInstanceStateBundle);
            }
        }
    }

    private void startApp(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            App.getComponents().injectsMainActivity(this);
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, RecyclerFragment.initInstance(presenter)).commit();
        }
    }
}
