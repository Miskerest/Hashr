package com.misker.mike.hasher;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    static protected ProgressBar progress;
    static protected TextView hashOutput;
    ClipboardManager clipboard;
    private Uri fileURI;
    private String hashtype = "MD5";
    private Button hashButton;
    private TextView hashCmpText;
    private TextView hashText;
    private AdView mAdView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setupFileHashPane(getApplicationContext());

    }
    private void setupFileHashPane(Context context){

        final Button fileButton = findViewById(R.id.fileButton);
        final View contentView = findViewById(R.id.activity_main);
        final Spinner selector = findViewById(R.id.hashSelectionSpinner);

        hashButton = findViewById(R.id.hashButton);
        hashOutput = findViewById(R.id.hashOutput);
        hashCmpText = findViewById(R.id.hashCmpText);
        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        progress = findViewById(R.id.progress);
        hashText = findViewById(R.id.hashText); //input for text-hashing

        //setup tabs
        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager pager = findViewById(R.id.viewpager);
        FixedTabsPagerAdapter adapter = new FixedTabsPagerAdapter(getSupportFragmentManager());

        adapter.setContext(context);

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);


        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 1) {
                    fileButton.setVisibility(View.INVISIBLE);
                    hashText.setVisibility(View.VISIBLE);
                    hashButton.setEnabled(true);
                }
                else {
                    fileButton.setVisibility(View.VISIBLE);
                    hashText.setVisibility(View.INVISIBLE);
                    hashButton.setEnabled(false);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                fileButton.setVisibility(View.VISIBLE);
                hashText.setVisibility(View.INVISIBLE);
                hashButton.setEnabled(true);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //set visibility and initialize labels/buttons
        hashCmpText.setText(getString(R.string.compare_hashes));
        hashButton.setEnabled(false);
        progress.setVisibility(View.INVISIBLE);

        //ad init
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-5863757662079397/8723627780");

        MobileAds.initialize(this, "ca-app-pub-5863757662079397/8723627780");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().setGender(AdRequest.GENDER_MALE)
                .addKeyword("Crypto").addKeyword("Cipher").build();
        mAdView.loadAd(adRequest);

        //Done setting up

        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                contentView.getWindowVisibleDisplayFrame(r);
                int screenHeight = contentView.getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;

                Log.d("debug", "keypadHeight = " + keypadHeight);

                if (keypadHeight > screenHeight * 0.15) { // to determine keypad height.
                    selector.setVisibility(View.INVISIBLE);
                    hashCmpText.setVisibility(View.INVISIBLE);
                }
                else {
                    selector.setVisibility(View.VISIBLE);
                    hashCmpText.setVisibility(View.VISIBLE);
                }
            }
        });

        fileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showFileChooser();
            }
        });

        hashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (v != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                if(fileButton.getVisibility() == View.VISIBLE) {
                    ContentResolver cr = getContentResolver();
                    HashRunnable hasher = new HashRunnable(hashtype, cr);

                    hasher.execute(fileURI);

                }
                else if(hashText.getVisibility() == View.VISIBLE){
                    String toHash = hashText.getText().toString();
                    HashRunnable hasher = new HashRunnable(hashtype, toHash);

                    hasher.execute(fileURI);

                }
            }
        });

        // handlers for comparing hash text when hashCmpText is clicked
        hashCmpText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                compareHashes();
            }
        });
        hashCmpText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                compareHashes();
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

    private void compareHashes(){
        Toast toast;
        Context context = getApplicationContext();

        if(!clipboard.hasPrimaryClip()){
            toast = Toast.makeText(context, getString(R.string.emptyClipboard), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        hashCmpText.setText(clipboard.getPrimaryClip()
                .getItemAt(0).coerceToText(getApplicationContext()).toString().toLowerCase());

        if(hashCmpText.getText().toString().toUpperCase()
                .equals(hashOutput.getText().toString().toUpperCase())) {
            toast = Toast.makeText(context, getString(R.string.hashesMatch), Toast.LENGTH_SHORT);
            hashCmpText.setTextColor(Color.GREEN);
        }
        else {
            toast = Toast.makeText(context, getString(R.string.hashesDontMatch), Toast.LENGTH_SHORT);
            hashCmpText.setTextColor(Color.RED);
        }


        toast.show();
    }

    /* File chooser for selecting files */
    private void showFileChooser() {

        if (Build.VERSION.SDK_INT < 19){
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

    /* For selecting files for file-hashing */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        super.onActivityResult(requestCode, resultCode, resultData);

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK && resultData != null) {

            if (resultData != null) {
                fileURI = resultData.getData();
                //grant permission in advance to prevent SecurityException
                try {
                    grantUriPermission(getPackageName(), fileURI, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                catch (NullPointerException e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Failed to get file reading permissions.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                // Check for the freshest data.
                try {
                    getContentResolver().takePersistableUriPermission(fileURI,
                            (Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION));
                }
                catch (SecurityException e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Opening file failed- report to developer!", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                ContentResolver cr = getContentResolver();
                try {
                    InputStream is = cr.openInputStream(fileURI);

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