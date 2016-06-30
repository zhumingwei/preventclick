package cn.zhichi.preventclick.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.zhichi.preventclick.R;
import cn.zhichi.preventclick.util.ViewUtils;

/**
 * Created by zhumingwei on 16/6/30.
 */
public class TabButtomLayout extends LinearLayout implements View.OnClickListener{
    private int size=5;
    private List<Holder> dataList= new ArrayList<>();




    public TabButtomLayout(Context context) {
        super(context);
        init();
    }

    public TabButtomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabButtomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        Holder holder1 =new Holder();
        holder1.textString="首页";
        holder1.clickColor=getResources().getColor(R.color.tab_click_color);
        holder1.unclickColor=getResources().getColor(R.color.tab_unclick_color);
        holder1.imgResClick=R.drawable.main_page1_click;
        holder1.imgResUnclick=R.drawable.main_page1_unclick;
        dataList.add(holder1);

        Holder holder2 =new Holder();
        holder2.textString="首页";
        holder2.clickColor=getResources().getColor(R.color.tab_click_color);
        holder2.unclickColor=getResources().getColor(R.color.tab_unclick_color);
        holder2.imgResClick=R.drawable.main_page2_click;
        holder2.imgResUnclick=R.drawable.main_page2_unclick;
        dataList.add(holder2);
        Holder holder3 =new Holder();
        holder3.textString="首页";
        holder3.clickColor=getResources().getColor(R.color.tab_click_color);
        holder3.unclickColor=getResources().getColor(R.color.tab_unclick_color);
        holder3.imgResClick=R.drawable.main_page3_click;
        holder3.imgResUnclick=R.drawable.main_page3_unclick;
        dataList.add(holder3);
        Holder holder4 =new Holder();
        holder4.textString="首页";
        holder4.clickColor=getResources().getColor(R.color.tab_click_color);
        holder4.unclickColor=getResources().getColor(R.color.tab_unclick_color);
        holder4.imgResClick=R.drawable.main_page4_click;
        holder4.imgResUnclick=R.drawable.main_page4_unclick;
        dataList.add(holder4);
        Holder holder5 =new Holder();
        holder5.textString="首页";
        holder5.clickColor=getResources().getColor(R.color.tab_click_color);
        holder5.unclickColor=getResources().getColor(R.color.tab_unclick_color);
        holder5.imgResClick=R.drawable.main_page5_click;
        holder5.imgResUnclick=R.drawable.main_page5_unclick;
        dataList.add(holder5);



        setOrientation(LinearLayout.HORIZONTAL);
        for(int i=0;i<dataList.size();i++){
            View view=LayoutInflater.from(getContext()).inflate(R.layout.item_main_tab_bottom,null);
            dataList.get(i).parentView=view;
            dataList.get(i).imageView=((ImageView)view.findViewById(R.id.item_main_imageview));
            dataList.get(i).textView=((TextView)view.findViewById(R.id.item_main_textview));
            dataList.get(i).textView.setText(dataList.get(i).textString);
            dataList.get(i).imageView.setImageDrawable(getResources().getDrawable(dataList.get(i).imgResUnclick));
            view.setBackgroundDrawable(this.getBackground());
            LayoutParams layoutParams=new LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
            view.setTag(i);
            view.setOnClickListener(this);
            layoutParams.weight=1;
            addView(view,layoutParams);
        }

        selectTab(0);
    }

    @Override
    public void onClick(View view) {
        if(view.getTag()!=null){
            Integer integer= (Integer) view.getTag();
            selectTab(integer);
        }
    }
    ViewPager viewPager;
    public void setViewPager(ViewPager viewPager){
        this.viewPager=viewPager;
    }
    private void selectTab(int index) {
        for (int i=0;i<dataList.size();i++){
            Holder holder=dataList.get(i);

            if(index!=i){
                holder.imageView.setImageDrawable(getResources().getDrawable(holder.imgResUnclick));
                holder.textView.setTextColor(holder.unclickColor);
            } else {
                holder.imageView.setImageDrawable(getResources().getDrawable(holder.imgResClick));
                holder.textView.setTextColor(holder.clickColor);
            }
        }
        if(viewPager!=null)
        viewPager.setCurrentItem(index,false);
        ViewUtils.toastShort("index=="+index);
    }


    class Holder {
        String textString;
        int clickColor;
        int unclickColor;
        int imgResUnclick;
        int imgResClick;
        ImageView imageView;
        TextView textView;
        View parentView;

    }
}
