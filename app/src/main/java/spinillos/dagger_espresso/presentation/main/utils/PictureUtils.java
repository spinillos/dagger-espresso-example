package spinillos.dagger_espresso.presentation.main.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import spinillos.dagger_espresso.framework.Constants;

public class PictureUtils {

    public static File createTempFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(new Date());
        String fileName = Constants.PICTURE_PREFIX + timestamp + "_";
        File storageDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        return File.createTempFile(fileName, Constants.PICTURE_EXTENSION, storageDir);

    }

}
