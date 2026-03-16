package com.example.mybazi;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;

import com.example.mybazi.databinding.ActivityMainBinding;

import baZiSever.BaziResult;
import baZiSever.Print;
import baZiSever.Tools;

public class MainActivity extends AppCompatActivity {


    private NumberPicker pickerYear,pickerMonth,pickerDay,pickerTime;
    private Spinner etGender, etLiuPai;
    private Button btnCalc;
 private ActivityMainBinding binding;
 private Tools tools = new Tools();

//声明了linearlayout了  接下来要初始化 还有绑定  然后就可以调整隐藏和释放了

public void calculateBaZi(BaziResult baziResult  ){
    //按钮点击事件内部逻辑，点完按钮就可以执行   这里是滚轮的逻辑
        int pickeryear = pickerYear.getValue();
        int pickermonth = pickerMonth.getValue();
        int pickerday = pickerDay.getValue();
        int pickertime = pickerTime.getValue();
        //流派1就转换化成1 流派2就转化成2
        int gender = etGender.getSelectedItem().toString().equals("男") ? 1 : 0;
        int liuPai = etLiuPai.getSelectedItem().toString().equals("流派1") ? 1 : 2;

        //获取信息
        baziResult.getUserInput(pickeryear, pickermonth, pickerday, pickertime,gender, liuPai);

        Print print = new Print(baziResult,tools,binding,this);
    //tvResult.setText(print.printAll());//将信息打印在textview里面



    findViewById(R.id.printControll).setVisibility(View.VISIBLE);
    findViewById(R.id.brithButton).setVisibility(View.GONE);
    findViewById(R.id.brithInput).setVisibility(View.GONE);
    print.printResult();



    binding.recDaYun.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    binding.recLiuNian.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    print.printDaYun();

}





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.ControlColor(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;

        });

        //下面是约束piker组件他的最大最小值
        pickerYear = findViewById(R.id.pickerYear);
        pickerYear.setMinValue(1800); // 最小年份
        pickerYear.setMaxValue(2200); // 最大年份
        pickerYear.setValue(2000);    // 默认值
        pickerMonth = findViewById(R.id.pickerMonth);
        pickerMonth.setMinValue(1);//最小月份
        pickerMonth.setMaxValue(12);//最大月份
        pickerMonth.setValue(1);//默认月份
        pickerDay = findViewById(R.id.pickerDay);
        pickerDay.setMinValue(1);//最小日子
        pickerDay.setMaxValue(31);//最大日子
        pickerDay.setValue(1);//默认月份
        pickerTime = findViewById(R.id.pickerTime);
        pickerTime.setMinValue(0);//最小时间
        pickerTime.setMaxValue(23);//最大时间
        pickerTime.setValue(0);//默认时间
        //实例化触控键 然后传入baziresult里面

        etGender = findViewById(R.id.etGender);
        etLiuPai = findViewById(R.id.etLiuPai);
        btnCalc = findViewById(R.id.btnCalc);

        findViewById(R.id.printControll).setVisibility(View.GONE);
        findViewById(R.id.brithButton).setVisibility(View.VISIBLE);
        findViewById(R.id.brithInput).setVisibility(View.VISIBLE);
        //将baziresult初始化
        BaziResult baziResult = new BaziResult();

        btnCalc.setOnClickListener(view -> calculateBaZi(baziResult)

        );

    }



}