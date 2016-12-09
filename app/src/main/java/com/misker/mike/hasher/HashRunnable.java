package com.misker.mike.hasher;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;


/**
 * Created by Mike Bailey on 12/9/16.
 */

public class HashRunnable extends AsyncTask<Uri, Void, String> {

    private String type;
    private String output = "Nice job buddy, you broke my code!";
    private ContentResolver cr;

    public HashRunnable(String hashtype, ContentResolver cr){
        type = hashtype;
        this.cr = cr;
    }

    @Override
    public void onPreExecute() {
        Main.hashOutput.setText("Please wait...");
        Main.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPostExecute(String result) {
        Main.progress.setVisibility(View.INVISIBLE);
        Main.hashOutput.setText(result);
    }

    @Override
    protected String doInBackground(Uri... uris) {
        try {
            InputStream is = cr.openInputStream(uris[0]);

            if (type.equals("MD5"))
                output = new String(Hex.encodeHex(DigestUtils.md5(is)));
            else if (type.equals("SHA1"))
                output = new String(Hex.encodeHex(DigestUtils.sha1(is)));
            else if (type.equals("SHA256"))
                output = new String(Hex.encodeHex(DigestUtils.sha256(is)));
            else if (type.equals("SHA384"))
                output = new String(Hex.encodeHex(DigestUtils.sha384(is)));
            else if (type.equals("CRC32")) {
                CheckedInputStream cis = new CheckedInputStream(is, new CRC32());
                byte[] tempBuf = new byte[128];
                while (cis.read(tempBuf) >= 0)
                {}
                long checksum = cis.getChecksum().getValue();
                output = Long.toHexString(checksum);
            }
            else if (type.equals("Adler32")){
                CheckedInputStream cis = new CheckedInputStream(is, new Adler32());
                byte[] tempBuf = new byte[128];
                while (cis.read(tempBuf) >= 0)
                {}
                long checksum = cis.getChecksum().getValue();
                output = Long.toHexString(checksum);
            }
            else
                output = new String(Hex.encodeHex(DigestUtils.sha512(is)));
        }
        catch (FileNotFoundException e){
            Log.e("FileDebug", e.getMessage());
        }
        catch (IOException e) {Log.e("FileDebug", e.getMessage());}

        return output;
    }
}
