package com.misker.mike.hasher;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class Main extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 420;
    ClipboardManager clipboard;
    private Uri fileURI;
    private InputStream is;
    private String hashtype = "MD5";
    private Button hashButton;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button fileButton = (Button) findViewById(R.id.fileButton);
        hashButton = (Button) findViewById(R.id.hashButton);
        final TextView hashOutput = (TextView) findViewById(R.id.hashOutput);
        final Spinner selector = (Spinner) findViewById(R.id.hashSelectionSpinner);
        final TextView HashCmpText = (TextView) findViewById(R.id.hashCmpText);
        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        HashCmpText.setText("Tap to paste reference hash");

        hashButton.setEnabled(false);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5863757662079397~1363106066");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().setGender(AdRequest.GENDER_MALE).addKeyword("Encryption").build();
        mAdView.loadAd(adRequest);

        //Done setting up

        fileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showFileChooser();
            }
        });

        hashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    String hash;

                    if (hashtype.equals("MD5"))
                        hash = new String(Hex.encodeHex(DigestUtils.md5(is)));
                    else if (hashtype.equals("SHA1"))
                        hash = new String(Hex.encodeHex(DigestUtils.sha1(is)));
                    else if (hashtype.equals("SHA256"))
                        hash = new String(Hex.encodeHex(DigestUtils.sha256(is)));
                    else
                        hash = new String(Hex.encodeHex(DigestUtils.sha512(is)));

                    hashOutput.setText(hash);
                }  catch (java.io.IOException ex) {
                    Log.e("FileDebug", ex.getMessage());
                } finally {
                    ContentResolver cr = getContentResolver();
                    try {
                        is = cr.openInputStream(fileURI);
                    }
                    catch (FileNotFoundException e){
                        Log.e("FileDebug", e.getMessage());
                    }
                }

            }
        });

        HashCmpText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast;
                Context context = getApplicationContext();



                if(clipboard.hasPrimaryClip())
                    HashCmpText.setText(clipboard.getPrimaryClip().toString());
                else {
                    toast = Toast.makeText(context, "Clipboard empty.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if(HashCmpText.getText().toString().equals(hashOutput.getText().toString()))
                    toast = Toast.makeText(context, "Hashes match!", Toast.LENGTH_SHORT);
                else
                    toast = Toast.makeText(context, "Hashes do not match.", Toast.LENGTH_SHORT);

                toast.show();
            }
        });

        HashCmpText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                Toast toast;
                Context context = getApplicationContext();

                if(clipboard.hasText())
                    HashCmpText.setText(clipboard.getText().toString());
                else {
                    toast = Toast.makeText(context, "Clipboard empty.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if(HashCmpText.getText().toString().equals(hashOutput.getText().toString()))
                    toast = Toast.makeText(context, "Hashes match!", Toast.LENGTH_SHORT);
                else
                    toast = Toast.makeText(context, "Hashes do not match.", Toast.LENGTH_SHORT);

                toast.show();
            }
        });

        selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hashtype = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });

    }

    private void showFileChooser() {

        if (Build.VERSION.SDK_INT <19){
            Intent intent = new Intent();
            intent.setType("*/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select a file"),
                    READ_REQUEST_CODE);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent, READ_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        super.onActivityResult(requestCode, resultCode, resultData);

      //  requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
          //      MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK && resultData != null) {

            if (resultData != null) {
                fileURI = resultData.getData();
                // Check for the freshest data.
                getContentResolver().takePersistableUriPermission(fileURI,
                        (Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION));

                ContentResolver cr = getContentResolver();
                try {
                    is = cr.openInputStream(fileURI);

                    if(is != null)
                        hashButton.setEnabled(true);

                }
                catch (FileNotFoundException e) {
                    Log.e("FileDebug", e.getMessage());
                }

            }
        }
    }

}