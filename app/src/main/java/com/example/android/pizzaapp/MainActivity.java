package com.example.android.pizzaapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Attach the SectionsPagerAdapter to ViewPager
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        //Attach the ViewPager to TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {

        // Inflate the menu; this adds item to the app bar
        getMenuInflater().inflate(R.menu.menu_main,menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Want to Join us for Pizza ?");

        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String text) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.lk
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                        case R.id.action_create_order:
                                Intent  intent = new Intent(this,Order_Activity.class);
                                startActivity(intent);
                        default :
                                return super.onOptionsItemSelected(item);


                        }
                }


    private  class SectionsPagerAdapter extends FragmentPagerAdapter {

            public SectionsPagerAdapter (FragmentManager fm) {

                super(fm);
            }

            @Override
            public int getCount(){

                return 4;

            }

            @Override
            public Fragment getItem (int position){

            switch (position) {

                case 0 :
                    return new TopFragment();

                case 1:
                    return new PizzaFragment();

                case 2:
                    return new PastaFragment();

                case 3:
                    return new StoresFragment();

            }

            return null;

            }

            @Override
            public CharSequence getPageTitle(int position) {

                switch(position) {
                    case 0 :
                        return getResources().getText(R.string.home_tab);

                    case 1:
                        return getResources().getText(R.string.pizza_tab);

                    case 2:
                        return getResources().getText(R.string.pasta_tab);

                    case 3:
                        return getResources().getText(R.string.stores_tab);
                }

                return null;
            }
        }

    }
