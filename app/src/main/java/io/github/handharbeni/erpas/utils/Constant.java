package io.github.handharbeni.erpas.utils;

import android.Manifest;
import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Constant {
//    public static final String BASE_URL = "http://36.66.115.131:8000/API_Retribusi_Pasar/";
    public static final String BASE_URL = "http://simpatda.bekasikota.go.id/simpatda_bekasi/api_retribusi_pasar/";
    public static final String BASE_IMAGE = "https://via.placeholder.com/100.png?text=";
//    public static final String UUID = "0b000001-0000-0001-1010-000000";
    public static final String UUID = "00001101-0000-1000-8000-00805F9B34FB";
    public static final String DB_NAME = "ERPASDB";
    public static final int DB_VERSION = 1;
    public static final boolean DB_EXPORT = false;
    public static final int RC_PERMISSION = 123;
    public static final int REQUEST_CHECK_SETTINGS = 111;
    public static final String BLUETOOTH_SCAN_REQUEST = "BLUETOOTH_SCAN_REQUEST";
    public static final String BLUETOOTH_CONNECT_REQUEST = "BLUETOOTH_CONNECT_REQUEST";
    public static final String BLUETOOTH_CONNECT_STATUS = "BLUETOOTH_CONNECT_STATUS";
    public static final String BLUETOOTH_PRINT = "BLUETOOTH_PRINT";
    public static final String BLUETOOTH_SEND_COMMAND = "BLUETOOTH_SEND_COMMAND";
    public static final String BLUETOOTH_DEVICE = "BLUETOOTH_DEVICE";
    public static final String BLUETOOTH_CONNECTED = "BLUETOOTH_CONNECTED";
    public static final String BLUETOOTH_CONNECTED_STRING = "BLUETOOTH_CONNECTED_STRING";
    public static final String REQUEST_PERMISSION = "REQUEST_PERMISSION";
    public static final ArrayList<String> LIST_PERMISSION = new ArrayList<>(
            Arrays.asList(
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.VIBRATE
            )
    );
    public static final int REQUEST_CODE_ENABLE_BLUETOOTH = 200;
    public static final int REQUEST_CODE_PERMISSION = 300;
    public static int REQUEST_CODE = 0;

    public static String KEY_DETAIL_TIKET = "io.github.handharbeni.erpas.fragments.DETAIL_TIKET";
    public static String KEY_DETAIL_PURPOSE = "io.github.handharbeni.erpas.fragments.DETAIL_TIKET_PURPOSE";
    public static String KEY_DETAIL_QR = "io.github.handharbeni.erpas.fragments.DETAIL_QR";

    public static final String FETCH_DATA = "io.github.handharbeni.erpas.cores.BaseFragment.FETCH_DATA";

    public static final String TOKEN = "io.github.handharbeni.erpas.apis.TOKEN";
    public static final String ISLOGGEDIN = "io.github.handharbeni.erpas.apis.ISLOGGEDIN";
    public static final String ME = "io.github.handharbeni.erpas.apis.ME";
    public static final String PRICE = "io.github.handharbeni.erpas.apis.PRICE";
    public static final String STATS = "io.github.handharbeni.erpas.apis.STATS";

    public static final String ARG_MASKED_NOP = "io.github.handharbeni.erpas.fragments.MASKED_NOP";
    public static final String ARG_UNMASKED_NOP = "io.github.handharbeni.erpas.fragments.UNMASKED_NOP";

    public static final String CI_USERNAME = "USERNAME";
    public static final String CI_PASSWORD = "PASSWORD";

    public static final String CI_NOP = "nop";
    public static final String CI_TAHUN = "tahun";
    public static final String CI_INTITUTION = "intitution";
    public static final String CI_AMOUNT = "amount";
    public static final String CI_KDTAGIHAN = "kd_tagihan";
    public static final String CI_DESC = "amount";
    public static final String CI_ISPBB = "isPbb";


    public static final int CONNECT_TIMEOUT = 1;
    public static final int READ_TIMEOUT = 1;
    public static final int WRITE_TIMEOUT = 1;
    public static final int CALL_TIMEOUT = 1;

    public static final TimeUnit TIMEUNIT_TIMEOUT = TimeUnit.MINUTES;




    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            LIST_PERMISSION.add(Manifest.permission.BLUETOOTH_CONNECT);
            LIST_PERMISSION.add(Manifest.permission.BLUETOOTH_SCAN);
        }
    }
}
