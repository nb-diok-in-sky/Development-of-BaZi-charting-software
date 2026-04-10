package baZiSever;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mybazi.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class GanZhiBottomSheet extends BottomSheetDialogFragment {


    public interface OnGanZhiClickListener {
        void onGanZhiClick(String ganOrZhi,int position);
    }



    private OnGanZhiClickListener listener;

    private int selectedPosition = 0;
    private String ganOrZhi;

//还得再做一个return  string类型的方法   当点击干支的时候自动获取所在格子内部的string类型变量

    private static final String ARG_CHAR = "char";

    public static GanZhiBottomSheet newInstance(String ganOrZhi) {
        GanZhiBottomSheet sheet = new GanZhiBottomSheet();
        Bundle args = new Bundle();
        args.putString(ARG_CHAR, ganOrZhi);
        sheet.setArguments(args);
        return sheet;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_ganzhi, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String ganOrZhi = getArguments().getString(ARG_CHAR);

        TextView tvTitle   = view.findViewById(R.id.tvSheetTitle);
        TextView tvContent = view.findViewById(R.id.tvSheetContent);
        Button   btnClose  = view.findViewById(R.id.btnSheetClose);

        tvTitle.setText(ganOrZhi);
        tvTitle.setTextColor(Tools.getColor(ganOrZhi));
        tvContent.setText(BaZiInfo.get(requireContext(), ganOrZhi));
        btnClose.setOnClickListener(v -> dismiss());
    }
}