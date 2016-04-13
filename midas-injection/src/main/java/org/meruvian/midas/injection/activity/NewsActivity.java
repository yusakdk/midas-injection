package org.meruvian.midas.injection.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.meruvian.midas.injection.MidasApplication;
import org.meruvian.midas.injection.R;
import org.meruvian.midas.injection.adapter.NewsAdapter;
import org.meruvian.midas.injection.repository.db.News;
import org.meruvian.midas.injection.repository.db.NewsDao;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by meruvian on 12/02/16.
 */
public class NewsActivity extends AppCompatActivity {
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.edit_title)
    EditText title;
    @Bind(R.id.edit_content)
    EditText content;
    @Bind(R.id.list_news)
    ListView listNews;

    private NewsAdapter newsAdapter;
    private News news;

    @Inject
    NewsDao newsDao;

    private String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        ((MidasApplication) getApplication()).getDaoComponent().inject(this);

        newsAdapter = new NewsAdapter(this, newsDao.queryBuilder().build().list());
        listNews.setAdapter(newsAdapter);

        listNews.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialogAction(position);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                newsAdapter.clear();
                newsAdapter.addNews(newsDao.queryBuilder().where(NewsDao.Properties.Title.like(s)).build().list());

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            if (news == null) {
                news = new News();
            }

            news.setStatus(1);
            news.setContent(content.getText().toString());
            news.setTitle(title.getText().toString());
            news.setDbCreateDate(new Date());

            newsDao.insertOrReplace(news);
            refreshList();

            title.setText("");
            content.setText("");
            news = new News();
            return true;
        } else if (id == R.id.action_refresh) {
            refreshList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dialogAction(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.action));
        builder.setItems(new String[]{getString(R.string.edit), getString(R.string.delete)}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int location) {
                Long id = ((News) newsAdapter.getItem(position)).getDbId();
                news = newsDao.queryBuilder().where(NewsDao.Properties.DbId.eq(id)).build().uniqueOrThrow();
                if (location == 0) {
                    if (news != null) {
                        Log.d(TAG, "EDIT - News ID: " + news.getDbId());
                        title.setText(news.getTitle());
                        content.setText(news.getContent());

                        title.requestFocus();
                    }
                } else if (location == 1) {
                    if (news != null) {
                        confirmDelete(news);
                    }
                }
            }
        });
        builder.create().show();
    }

    private void confirmDelete(final News news) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete));
        builder.setMessage(getString(R.string.confirm_delete) + " '" + news.getTitle() + "' ?");
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                newsDao.delete(news);
                refreshList();
            }
        });
        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void refreshList(){
        newsAdapter.clear();
        newsAdapter.addNews(newsDao.queryBuilder().build().list());
    }

}