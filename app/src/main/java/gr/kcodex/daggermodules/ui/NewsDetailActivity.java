package gr.kcodex.daggermodules.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gr.kcodex.applib.io.model.Item;
import gr.kcodex.daggermodules.R;
import gr.kcodex.daggermodules.ui.mvp.CommentsListView;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);


    }

}
