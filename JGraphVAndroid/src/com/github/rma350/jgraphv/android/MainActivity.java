package com.github.rma350.jgraphv.android;

import java.util.ArrayList;
import java.util.List;

import com.github.rma350.jgraphv.android.R;
import com.github.rma350.jgraphv.android.coredeps.AndroidLog;
import com.github.rma350.jgraphv.core.demo.AllDemos;
import com.github.rma350.jgraphv.core.demo.Demo;
import com.github.rma350.jgraphv.core.portable.Log;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {
	
	private GraphView mGraphView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.out = new AndroidLog();

    final ActionBar actionBar = getActionBar();
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

    List<String> demoNames = new ArrayList<String>();
    for (Demo demo : AllDemos.allDemos()) {
      demoNames.add(demo.getName());
    }
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(actionBar.getThemedContext(),
        android.R.layout.simple_spinner_item, demoNames);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    actionBar.setListNavigationCallbacks(adapter,
        new OnNavigationListener() {

          @Override
          public boolean onNavigationItemSelected(int position, long id) {
            getGraphView().onDemoSelected(position);
            return true;
          }
        });

    mGraphView = new GraphView(this);
    setContentView(mGraphView);
  }

  private GraphView getGraphView() {
    return mGraphView;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
