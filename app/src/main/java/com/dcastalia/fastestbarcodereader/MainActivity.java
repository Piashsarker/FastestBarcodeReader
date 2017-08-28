package com.dcastalia.fastestbarcodereader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.google.android.gms.vision.barcode.Barcode;

import static junit.framework.Assert.assertNotNull;

/** This Example is Make From the Library https://github.com/EdwardvanRaak/MaterialBarcodeScanner?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=3439
 * Thanks to MaterialBarcodeScanner For a fastest barcode library .
 */


public class MainActivity extends AppCompatActivity {

    public static final String BARCODE_KEY = "BARCODE";
    private Barcode barcodeResult;
    private TextView result;
    FloatingActionButton fab ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScan();
            }
        });
        if(savedInstanceState != null){
            Barcode restoredBarcode = savedInstanceState.getParcelable(BARCODE_KEY);
            if(restoredBarcode != null){
                result.setText(restoredBarcode.rawValue);
                barcodeResult = restoredBarcode;
            }
        }
    }

    private void startScan() {
        /**
         * Build a new MaterialBarcodeScanner
         */
        final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                .withActivity(MainActivity.this)
                .withEnableAutoFocus(true)
                .withBleepEnabled(true)
                .withBackfacingCamera()
                .withCenterTracker()
                .withText("Scanning...")
                .withResultListener(new MaterialBarcodeScanner.OnResultListener() {
                    @Override
                    public void onResult(Barcode barcode) {
                        barcodeResult = barcode;
                        result.setText(barcode.rawValue);
                    }
                })
                .build();
        materialBarcodeScanner.startScan();
    }

    private void initializeViews() {
        result = (TextView) findViewById(R.id.barcodeResult);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        assertNotNull(result);
        assertNotNull(fab);
    }
}
