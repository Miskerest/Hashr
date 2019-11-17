package com.misker.mike.hasher;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.misker.mike.hasher.hashers.Hasher;
import com.misker.mike.hasher.hashers.HasherFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


/**
 * Created by Mike Bailey on 12/9/16.
 * Class for calculating hashes of Strings or file-like objects
 */

class HashRunnable extends AsyncTask<Uri, Void, String> {

    private final MainView mainView;
    private final String type;
    private String toHash;
    private ContentResolver cr;


    HashRunnable(String hashtype, ContentResolver cr, MainView mainView){
        type = hashtype;
        this.cr = cr;
        this.mainView = mainView;
    }

    HashRunnable(String hashtype, String toHash, MainView mainView){
        type = hashtype;
        this.toHash = toHash;
        this.mainView = mainView;
    }

    @Override
    public void onPreExecute() {
        mainView.displayWaitProgress();
    }

    @Override
    public void onPostExecute(String result) {
        mainView.displayResults(result);
    }

    @Override
    protected String doInBackground(Uri... uris) {
        String output = "Nice job buddy, you broke my code!";
        if(cr != null) {
            try {
                InputStream is = cr.openInputStream(uris[0]);

                if(is == null)
                    throw new IOException();

                output = createHash(type, is);
            } catch (IOException e) {
                Log.e("FileDebug", Objects.requireNonNull(e.getMessage()));
            }
        } else {
            try {
                InputStream is = new ByteArrayInputStream(toHash.getBytes(StandardCharsets.UTF_8));
                output = createHash(type, is);
            }
            catch (IOException  e){
                Log.e("FileDebug", Objects.requireNonNull(e.getMessage()));
            }
        }

        return output;
    }

    private String createHash(String hasherType, InputStream inputStream) throws IOException {
        Hasher hasher = HasherFactory.createHasher(hasherType);
        return hasher.hash(inputStream);
    }

}
