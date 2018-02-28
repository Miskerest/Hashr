package com.misker.mike.hasher;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.misker.mike.hasher.hashers.Hasher;
import com.misker.mike.hasher.hashers.HasherFactory;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;


/**
 * Created by Mike Bailey on 12/9/16.
 * Class for calculating hashes of Strings or file-like objects
 */

class HashRunnable extends AsyncTask<Uri, Void, String> {

    private final MainView mainView;
    private final String type;
    private String toHash;
    private ContentResolver cr;


    public HashRunnable(String hashtype, ContentResolver cr, MainView mainView){
        type = hashtype;
        this.cr = cr;
        this.mainView = mainView;
    }

    public HashRunnable(String hashtype, String toHash, MainView mainView){
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
                Log.e("FileDebug", e.getMessage());
            }
        } else {
            try {
                InputStream is = new ByteArrayInputStream(toHash.getBytes("UTF-8"));
                output = createHash(type, is);
            }
            catch (IOException e){
                Log.e("FileDebug", e.getMessage());
            }
        }

        return output;
    }

    private String createHash(String hasherType, InputStream is) throws IOException {
        String input = convertInputStreamToString(is);
        Hasher hasher = HasherFactory.createHasher(hasherType);
        return hasher.hash(input);
    }

    private String convertInputStreamToString(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer, "UTF-8");
        return writer.toString();
    }

}
