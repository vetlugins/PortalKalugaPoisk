package ru.kaluga_poisk.portalkalugapoisk.Adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;

import ru.kaluga_poisk.portalkalugapoisk.Model.Item;
import ru.kaluga_poisk.portalkalugapoisk.R;

class MyViewHolderWithoutChild extends RecyclerView.ViewHolder {

    public TextView textView;

    public MyViewHolderWithoutChild(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textView);

    }
}

class MyViewHolderWithChild extends RecyclerView.ViewHolder {

    public TextView textView, textViewChild;
    public RelativeLayout button;
    public ExpandableLinearLayout expandableLayout;

    public MyViewHolderWithChild(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textView);
        textViewChild = itemView.findViewById(R.id.textViewChild);
        button = itemView.findViewById(R.id.button);
        expandableLayout = itemView.findViewById(R.id.expandableLayout);
    }
}

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Item> items;
    Context context;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public MyAdapter(List<Item> items) {
        this.items = items;

        for (int i = 0; i < items.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position).isExpandable())
            return 1;
        else
            return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        if(viewType == 0) // Without item
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.layout_without_child,parent,false);
            return new MyViewHolderWithoutChild(view);
        } else {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.layout_with_child,parent,false);
            return new MyViewHolderWithChild(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case 0: {
                MyViewHolderWithoutChild viewHolder = (MyViewHolderWithoutChild)holder;
                Item item = items.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText((CharSequence) item.getText()); //TODO cho-to tut ne tak
            } break;
            case 1: {
                final MyViewHolderWithChild viewHolder = (MyViewHolderWithChild)holder;
                Item item = items.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText((CharSequence) item.getText());

                viewHolder.expandableLayout.setInRecyclerView(true);
                viewHolder.expandableLayout.setExpanded(expandState.get(position));
                viewHolder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {

                    @Override
                    public void onPreOpen() {
                        changeRotate(viewHolder.button, 0f,018f).start();
                        expandState.put(position,true);
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolder.button, 180f,0f).start();
                        expandState.put(position,false);
                    }

                });

                viewHolder.button.setRotation(expandState.get(position) ? 180f: 0f);
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewHolder.expandableLayout.toggle();
                    }
                });

                viewHolder.textViewChild.setText((CharSequence) items.get(position).getSubText()); //TODO cho-to tut ne tak

            } break;
            default: break;
        }

    }

    private ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(button,"rotation",from,to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));

        return animator;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


