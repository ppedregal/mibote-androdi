package com.thinknowa.botin.components.slidemenu;

import com.thinknowa.botin.components.slidemenu.fragments.SlideOne;
import com.thinknowa.botin.components.slidemenu.fragments.SlideTwo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

	public PagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			
		return new SlideOne();
		case 1:
			return new SlideTwo();

		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
