package com.chanel.tool;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.chanel.BuildConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that provides three methods of root checking. In static methods.
 */
public final class RootChecker {

    /**Shell command for superuser access with su binary. */
    public static final String[] CHECK_SU_BINARY = new String[]{"/system/xbin/which", "su"};

    /**Unable to instantiate. */
    private RootChecker() {
        throw new AssertionError("Unable to instantiate.");
    }

    /** Check build tags for test-keys.*/
    public static boolean checkTestKeys() {
        return (!TextUtils.isEmpty(Build.TAGS)
                && Build.TAGS.contains("test-keys"));
    }

    /** Check if SuperUser.apk was installed. */
    public static boolean checkSuperUserApk() {
        File apk = new File("/system/app/Superuser.apk");
        return apk.exists();
    }

    /**
     * Execute shell commands, if it success - device is rooted.
     * Check for returned logs.
     */
    public static boolean executeSuBinary(){
        List<String> executed = new LinkedList<>();

        try {
            Process process = Runtime.getRuntime().exec(CHECK_SU_BINARY);
            if (process == null) { return  false; }
            InputStreamReader streamReader = new InputStreamReader(process.getInputStream());
            BufferedReader in = new BufferedReader(streamReader);
            while (in.readLine() != null) {
                String line = in.readLine();
                executed.add(line);
            }

        } catch (IOException ex) {
            if (BuildConfig.DEBUG) {
                Log.e("RootChecker", TextUtils.isEmpty(ex.getMessage())
                        ? "Unable to run su binary command"
                        : ex.getMessage());
            }
        }
        return (!executed.isEmpty());
    }
}
