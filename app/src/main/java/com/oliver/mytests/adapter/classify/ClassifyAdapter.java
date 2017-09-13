package com.oliver.mytests.adapter.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.oliver.mytests.R;
import com.oliver.mytests.data.DataModelFour;
import com.oliver.mytests.data.DataModelOne;
import com.oliver.mytests.data.DataModelTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_1 on 2017/1/5.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private static final int TYPE_THREE = 3;
    private static final int TYPE_FOUR = 4;

    public Context context;
    private LayoutInflater layoutInflater;


    private List<DataModelOne> dataModelOneList;
    private List<DataModelTwo> dataModelTwoList;
    private List<DataModelFour> dataModelFourList;
    private List<Object> allList = new ArrayList<>();
    private List<Integer> types = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();

    private ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }
    public ClassifyAdapter(){}

    public ClassifyAdapter(Context context, List<DataModelOne> list1, List<DataModelTwo> list2,
                           List<DataModelFour> list4) {
        this.dataModelOneList = list1;
        this.dataModelTwoList = list2;
        this.dataModelFourList = list4;



        allList.addAll(list1);
        allList.addAll(list2);
        list3.add("TAG");
        allList.addAll(list3);
        allList.addAll(list4);
        Log.d("onBindViewHolder", "ClassifyAdapter: alllist size    "+allList.size());
        addListByType(TYPE_ONE, list1);
        addListByType(TYPE_TWO, list2);
        addListByType(TYPE_THREE, list3);
        addListByType(TYPE_FOUR, list4);
        Log.d("onBindViewHolder", "ClassifyAdapter:  types size   "+types.size());
        for(int i =0;i<types.size();i++){
            Log.d("onBindViewHolder", "ClassifyAdapter: type  "+types.get(i));
        }
        this.context = context;
        layoutInflater = LayoutInflater.from(context);


    }

    public void addListByType(int type, List list) {
        Log.d("addListByType", "addListByType: "+list.size());
//        if(type==1){
//            Toast.makeText(context, ""+list.size(), Toast.LENGTH_SHORT).show();
////        Toast.makeText(context, ""+((DataModelOne)list.get(0)).getImgUrl(), Toast.LENGTH_SHORT).show();
//        }
        if(list.size()==0){
            return ;
        }
        for (int i = 0; i < list.size(); i++) {
            types.add(type);
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("onBindViewHolder", "onCreateViewHolder: ++++++++    " +viewType);
        switch (viewType) {
            case TYPE_ONE:
                Log.d("onBindViewHolder", "onCreateViewHolder:   one");

                return new ViewHolderOne(layoutInflater.inflate(R.layout.classify_item_one,null));
            case TYPE_TWO:
                Log.d("onBindViewHolder", "onCreateViewHolder:   two");
                return new ViewHolderTwo(layoutInflater.inflate(R.layout.classify_item_two, parent,false));
            case TYPE_THREE:
                Log.d("onBindViewHolder", "onCreateViewHolder:   three");
                return new ViewHolderThree(layoutInflater.inflate(R.layout.classify_item_three, parent,false));
            case TYPE_FOUR:
                Log.d("onBindViewHolder", "onCreateViewHolder:   four");
                return new ViewHolderFour(layoutInflater.inflate(R.layout.classify_item_four, parent,false));
        }
//
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        Object object = allList.get(position);
        int realPosition = 0;
        if (object instanceof DataModelOne) {
            for (int i = 0; i < dataModelOneList.size(); i++) {
                if (object == dataModelOneList.get(i)) {
                    Log.d("onBindViewHolder", "viewtype   : "+viewType);
                    Log.d("onBindViewHolder", "i   : "+i);
                    realPosition = i;
                }
            }
        } else if (object instanceof DataModelTwo) {
            for (int i = 0; i < dataModelTwoList.size(); i++) {
                if (object == dataModelTwoList.get(i)) {
                    Log.d("onBindViewHolder", "viewtype   : "+viewType);
                    Log.d("onBindViewHolder", "i   : "+i);
                    realPosition = i;
                }
            }
        } else if (object instanceof DataModelFour) {
            for (int i = 0; i < dataModelFourList.size(); i++) {
                if (object == dataModelFourList.get(i)) {
                    Log.d("onBindViewHolder", "viewtype   : "+viewType);
                    Log.d("onBindViewHolder", "i   : "+i);
                    realPosition = i;
                }
            }
        }

        switch (viewType) {
            case TYPE_ONE:
                Log.d("onBindViewHolder", ": realposition   " + realPosition);
                ((ViewHolderOne) holder).bindHolder(dataModelOneList.get(realPosition), context, itemClick, realPosition);
                break;
            case TYPE_TWO:
                ((ViewHolderTwo) holder).bindHolder(dataModelTwoList.get(realPosition), context, itemClick, realPosition);
                break;
            case TYPE_THREE:
                ((ViewHolderThree) holder).bindHolder(null, context, null, realPosition);
                break;
            case TYPE_FOUR:
                ((ViewHolderFour) holder).bindHolder(dataModelFourList.get(realPosition), context, itemClick, realPosition);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }
}
