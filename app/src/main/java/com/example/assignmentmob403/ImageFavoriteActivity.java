package com.example.assignmentmob403;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import dmax.dialog.SpotsDialog;

public class ImageFavoriteActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1000;
    private ImageView imgImageFavorite;
    private FloatingActionButton btnDownload, fabDefaut, fabSmall, fabBig, fabsetwall, fabshare;
    private TextView tvNameImageFavorite;
    private String URLC = "";
    private String NAME = "";
    private String URLS = "";
    private String URLO = "";
    private boolean anhien = false;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private ShareLinkContent content;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_image_favorite);


        callbackManager = CallbackManager.Factory.create();
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.example.assignmentmob403",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }


        imgImageFavorite = findViewById(R.id.imgImageFavorite);
        btnDownload = findViewById(R.id.btnDownload);
        tvNameImageFavorite = findViewById(R.id.tvNameImageFavorite);
        fabDefaut = findViewById(R.id.fab_Defaut);
        fabBig = findViewById(R.id.fab_Big);
        fabSmall = findViewById(R.id.fab_Small);
        fabsetwall = findViewById(R.id.fab_setwall);
        fabshare = findViewById(R.id.fab_share);
        Intent intent = getIntent();
        URLC = intent.getStringExtra("URLC");
        NAME = intent.getStringExtra("NAME");
        URLS = intent.getStringExtra("URLS");
        URLO = intent.getStringExtra("URLO");

        fabsetwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Target target = new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Log.e("ABCb", "OnBitmapLoaded");
                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(ImageFavoriteActivity.this);
                        try {
                            wallpaperManager.setBitmap(bitmap);
                            Toast.makeText(ImageFavoriteActivity.this, "Set Wallpaper Sucssefully", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("TAG", "IOException->" + e.getMessage());
                        }
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Log.e("TAG", "" + e.getMessage());
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        Log.e("TAG", "OnPrepareLoad");
                    }
                };
                Picasso.get().load(URLC).into(target);
            }
        });


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, PERMISSION_REQUEST_CODE);

        }


        fabBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(ImageFavoriteActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ImageFavoriteActivity.this, "You should grant permission", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{

                            Manifest.permission.WRITE_EXTERNAL_STORAGE

                    }, PERMISSION_REQUEST_CODE);
                    return;
                } else {

                    AlertDialog dialog = new SpotsDialog(ImageFavoriteActivity.this);

                    dialog.show();
                    dialog.setMessage("Downloading...");

                    String fileName = UUID.randomUUID().toString() + ".jpg";
                    Picasso.get().load(URLC).into(new SavingImage(getBaseContext(), dialog, getApplicationContext().getContentResolver(), fileName, "Image description"));
                }
            }
        });

        fabSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // down ảnh với size nhỏ
                if (ActivityCompat.checkSelfPermission(ImageFavoriteActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ImageFavoriteActivity.this, "You should grant permission", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{

                            Manifest.permission.WRITE_EXTERNAL_STORAGE

                    }, PERMISSION_REQUEST_CODE);
                    return;
                } else {

                    AlertDialog dialog = new SpotsDialog(ImageFavoriteActivity.this);

                    dialog.show();
                    dialog.setMessage("Downloading...");

                    String fileName = UUID.randomUUID().toString() + ".jpg";

                    Picasso.get().load(URLS).into(new SavingImage(getBaseContext(), dialog, getApplicationContext().getContentResolver(), fileName, "Image description"));
                }

            }
        });

        fabDefaut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // down ảnh với size gốc

                if (ActivityCompat.checkSelfPermission(ImageFavoriteActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ImageFavoriteActivity.this, "You should grant permission", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{

                            Manifest.permission.WRITE_EXTERNAL_STORAGE

                    }, PERMISSION_REQUEST_CODE);
                    return;
                } else {

                    AlertDialog dialog = new SpotsDialog(ImageFavoriteActivity.this);

                    dialog.show();
                    dialog.setMessage("Downloading...");

                    String fileName = UUID.randomUUID().toString() + ".jpg";
                    Picasso.get().load(URLO).into(new SavingImage(getBaseContext(), dialog, getApplicationContext().getContentResolver(), fileName, "Image description"));

                }
            }
        });

        tvNameImageFavorite.setText(NAME);
        Picasso.get().load(URLC).into(imgImageFavorite);

        fabshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse(URLC))
                        .build();

                ShareDialog shareDialog = new ShareDialog(ImageFavoriteActivity.this);
                shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);

            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (anhien == false) {
                    hien();
                    anhien = true;
                } else {
                    an();
                    anhien = false;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void hien() {
        fabsetwall.show();
        fabSmall.show();
        fabBig.show();
        fabDefaut.show();
        fabshare.show();
    }

    private void an() {
        fabDefaut.hide();
        fabBig.hide();
        fabSmall.hide();
        fabsetwall.hide();
        fabshare.hide();
    }
}