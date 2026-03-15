package baZiSever;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybazi.R;
import com.example.mybazi.databinding.ItemDayunBinding;
import com.nlf.calendar.eightchar.DaYun;

import java.util.List;


public class DaYunAdapter extends RecyclerView.Adapter<DaYunAdapter.ViewHolder> {

    private List<YunNianInfo> daYunList;//这里是写大运的数据，存储的十个大运的数据
    private int selectedPosition = 0;   // 当前选中第几个，默认第0个//这里是选择第几个大运的顺序默认第0个
    private OnDaYunClickListener listener;  //这里是点击事件   点击大运触发事件就可以了
    private DaYun dayun ;


    static class ViewHolder extends RecyclerView.ViewHolder {//这个类里面存放的是格子里面放置的textview的集合， 这是一个继承了recyclerview的子类函数
        ItemDayunBinding binding;//这个是绑定大运的，通过binding来找到每个textview  只有把super传出去了，父类才知道是哪个view，才能初始化
        ViewHolder(ItemDayunBinding binding) {
            super(binding.getRoot());//getroot返回的是一个view类型的  他是所有组件的父类  而super是recycleview viewhold的构造函数
            this.binding = binding;
        }
    }




    public interface OnDaYunClickListener {
         void onDaYunClick(YunNianInfo yunNianInfo,int Position);
         //这是点击事件触发的方法，当你点击的时候返回某个大运的信息（年龄，干支，几几年）还有位置（第几个大运）
    }



    public DaYunAdapter(List<YunNianInfo> daYunList, OnDaYunClickListener listener) {
        this.daYunList = daYunList;
        this.listener = listener;//这是构造函数啊，必须得传进来大运的信息和列表
    }

    //下面这个是和recycle配套的方法
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//这是造格子的方法
        //recycle需要格子 但是系统不知道是什么样子的格子 所以需要在这里告知系统 你需要造一个什么样的格子
ItemDayunBinding binding = ItemDayunBinding.inflate(
        LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//这里是
      YunNianInfo yunNianInfo = daYunList.get(position);
        holder.binding.tvDaYunYear.setText(String.valueOf(yunNianInfo.startYear));
        holder.binding.tvDaYunAge.setText(String.valueOf(yunNianInfo.startAge));
        holder.binding.tvDaYunTianGan.setText(Tools.setColorOfGanZhi(yunNianInfo.tiangan));
        holder.binding.tvDaYunDiZhi.setText(Tools.setColorOfGanZhi(yunNianInfo.dizhi));


        if (yunNianInfo.startAge > 0) {
            holder.binding.tvDaYunYear.setVisibility(View.VISIBLE);

                holder.binding.tvDaYunYear.setText(yunNianInfo.startAge + "岁");
        }
            else {
            holder.binding.tvDaYunYear.setVisibility(View.GONE);
        }

        holder.itemView.setSelected(position == selectedPosition);
        holder.itemView.setOnClickListener(v -> {
            int old = selectedPosition;
            selectedPosition = holder.getBindingAdapterPosition();
            notifyItemChanged(old);
            notifyItemChanged(selectedPosition);
            listener.onDaYunClick(yunNianInfo, selectedPosition);
        }

    }

    @Override
    public int getItemCount() {



        return 0;
    }




}
