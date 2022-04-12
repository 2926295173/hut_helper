package com.wolfaonliu.cardreader;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class AboutActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Toolbar toolbar;


    private AboutAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = findViewById(R.id.about_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        initData();
        initView();
    }


    private void initData() {
        mAdapter = new AboutAdapter(getSet(), this);
    }

    private ArrayList<String[]> getSet() {
        ArrayList<String[]> settings = new ArrayList<>();
        String[][] s = new String[4][2];
        s[0][0] = getString(R.string.version);
        s[0][1] = Util.getVersion(this.getApplicationContext());
        s[1][0] = getString(R.string.developer);
        s[1][1] = "aoian";
        s[2][0] = getString(R.string.contact);
        s[2][1] = "wolfaonliuxgmailcom";
        s[3][0] = getString(R.string.github);
        s[3][1] = "https://github.com/2926295173/hut_helper";
        settings.addAll(Arrays.asList(s));
        return settings;
    }

    private void initView() {
        ListView mView = findViewById(R.id.set_list);
        // 设置adapter
        mView.setAdapter(mAdapter);
        mView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Uri uri;
        Intent intent;
        switch (position) {
            case 2:
                showEmailDialog();
                break;
            case 3:
                uri = Uri.parse("https://github.com/2926295173/hut_helper");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            default:
                break;
        }
        Toast.makeText(this, "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
    }


    private void showEmailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.Report)
                .setMessage(R.string.report_atten);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("message/rfc822");
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"wolfaonliuxgmailcom"});
            email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name) + getString(R.string.report));
            startActivity(Intent.createChooser(email, getString(R.string.email_clint)));
        });
        builder.setNegativeButton(R.string.no, null);

        builder.show();
    }
}
