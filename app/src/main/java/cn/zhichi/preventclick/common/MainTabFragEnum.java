package cn.zhichi.preventclick.common;

import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import cn.zhichi.preventclick.fragment.GeneralFragment;
import cn.zhichi.preventclick.fragment.IpFragment;
import cn.zhichi.preventclick.fragment.KeyWordFragment;
import cn.zhichi.preventclick.fragment.MainFragment;
import cn.zhichi.preventclick.fragment.VisterFragment;

public enum MainTabFragEnum {


	page0(0) {
		@Override
		public Fragment createFrag() {

			Fragment frag = new MainFragment();
			setFrag(frag);

			return frag;
		}

	}, // 书架

	page1(1) {
		@Override
		public Fragment createFrag() {

			Fragment frag = new GeneralFragment();
			setFrag(frag);

			return frag;
		}

	}, // 书城

	page2(2) {
		@Override
		public Fragment createFrag() {

			Fragment frag = new VisterFragment();
			setFrag(frag);

			return frag;
		}

	},//排行

	page3(3) {
		@Override
		public Fragment createFrag() {

			Fragment frag = new IpFragment();
			setFrag(frag);

			return frag;
		}

	}, // 个人中心
	page4(4){
		@Override
		public Fragment createFrag() {
			Fragment frag = new KeyWordFragment();
			setFrag(frag);

			return frag;
		}
	}
	;

	/* 排序 */
	private int index;
	private Fragment frag;

	private MainTabFragEnum(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public Fragment getFrag() {
		return frag;
	}

	public void setFrag(Fragment frag) {
		this.frag = frag;
	}

	/**
	 * 得到当前片段实例
	 * 
	 * @return
	 */
	public abstract Fragment createFrag();

	/**
	 * 片段跳转
	 * 
	 * @return
	 */
	public void goTo(RadioGroup tabs) {
		tabs.getChildAt(getIndex()).performClick();
	}

	/**
	 * 通过索引得到实例
	 * 
	 * @param index
	 * @return
	 */
	public static MainTabFragEnum getByIndex(int index) {
		return MainTabFragEnum.values()[index];
	}

}
