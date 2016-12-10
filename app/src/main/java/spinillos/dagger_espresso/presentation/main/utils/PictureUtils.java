package spinillos.dagger_espresso.presentation.main.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import spinillos.dagger_espresso.framework.Constants;

public class PictureUtils {

    @Inject
    public PictureUtils() {

    }

    public Uri saveBitmapToFile(Bitmap bitmap) {
        File file = null;

        try {
            file = createTempFile();
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.parse(file.getAbsolutePath());
    }

    private File createTempFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(new Date());
        String fileName = Constants.PICTURE_PREFIX + timestamp + "_";
        File storageDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        return File.createTempFile(fileName, Constants.PICTURE_EXTENSION, storageDir);

    }

}
