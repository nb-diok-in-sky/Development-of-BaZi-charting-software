package baZiSever;


import com.example.mybazi.databinding.ActivityMainBinding;
import com.nlf.calendar.EightChar;
import com.nlf.calendar.eightchar.DaYun;
import com.nlf.calendar.eightchar.Yun;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

public class Print {
    Tools tools ;
    private BaziResult baziResult;
    private Context context;
    ActivityMainBinding binding;

    public Print(BaziResult baziResult, Tools tools, ActivityMainBinding binding, Context context)
    {//这一段是把括号里面传进来的外部的baziresult数据保存在print里面
        this.baziResult = baziResult;
        this.tools = tools;
        this.binding = binding;
        this.context = context;
    }




    public void printResult()
    {

        EightChar eightChar = baziResult.getEightChar();

      List<GanZhiText> list =  tools.getGanZhiText(eightChar);//这里返回的是一个list类型 装载的是四柱，Ac
        //接下来要将刚刚的list传入textview里面
        TextView[] starViews = {binding.tvYearStar,binding.tvMonthStar,binding.tvDayStar, binding.tvTimeStar};
        TextView[] ganViews = {binding.tvYearGan, binding.tvMonthGan, binding.tvDayGan, binding.tvTimeGan};//创建数组把他们存起来，等会方便循环遍历
        TextView[] zhiViews = {binding.tvYearZhi, binding.tvMonthZhi, binding.tvDayZhi, binding.tvTimeZhi};
        TextView[] hideGanViews = {binding.tvYearHideGan, binding.tvMonthHideGan, binding.tvDayHideGan, binding.tvTimeHideGan};
        TextView[] naYinView = {binding.tvYearNaYin,binding.tvMonthNaYin,binding.tvDayNaYin,binding.tvTimeNaYin  };
        TextView[] diShiView = {binding.tvYearDiShi,binding.tvMonthDiShi,binding.tvDayDiShi,binding.tvTimeDiShi};

            for(int i = 0;i<list.size();i++){
                GanZhiText ganZhiText =list.get(i) ;
                starViews[i].setText(ganZhiText.star);
                ganViews[i].setText(Tools.setColorOfGanZhi(ganZhiText.gan));
                zhiViews[i].setText(Tools.setColorOfGanZhi(ganZhiText.zhi));
                hideGanViews[i].setText(Tools.setColorOfHideGanShiShen(context,ganZhiText.hideGan, ganZhiText.hideGanShiShen));
                diShiView[i].setText(ganZhiText.diShi);
                naYinView[i].setText(ganZhiText.naYin);

                //用三个循环来把这个干支view初始化了
                //我好棒
            }
    }

    public void printDaYun(){

        EightChar eightChar = baziResult.getEightChar();
        String DayGan = eightChar.getDayGan();//先获取日干
        Yun yun = eightChar.getYun(baziResult.gender, baziResult.liuPai);//将大运传出来，建立一个叫yun的东西，这样就是大运的基础
        DaYun[] daYuns = yun.getDaYun();//使用getdayun方法 把yun转化成数组存起来
        List<YunNianInfo> daYunList = YunNianFactory.buildDaYunList(daYuns,DayGan);//运用之前的list方法，把大运分解成干支和年龄和年份
        //在点击大运方法内部还需要新增点击流年的方法，和大运的内容一样 //在这里声明流年
        List<YunNianInfo> defaultList = YunNianFactory.buildLiuNianList(daYuns[1],DayGan);//先用第0个流年初始化  这是默认第0个流年
        LiuNianAdapter liuNianAdapter = new LiuNianAdapter(defaultList,(yunNianInfo,Position) ->{

        });
        binding.recLiuNian.setAdapter(liuNianAdapter);//这里设置了recycle的adapter，之后会在adapter内部生成合适的格子
        //用于装载流年  默认流年是第0个流年

        //之后需要把这个list拿去继续拆分，挨个上色
        DaYunAdapter daYunAdapter = new DaYunAdapter(daYunList,(yunNianInfo, Position) -> {//上面是点击事件，点击点击大运，获取位置，然后执行方法
            //现在是点击流年的方法 在这里要点击之后把数据，数据传入textview已经在liunianadapter里面封装好了传进recycle里面

             List<YunNianInfo> LiuNianList = YunNianFactory.buildLiuNianList(daYuns[Position],DayGan);
             //点击大运之后根据所在位置新建一个流年list用来装载流年
            liuNianAdapter.updateData(LiuNianList);


//这里是在做点击大运，刷新流年
        });
        binding.recDaYun.setAdapter(daYunAdapter);//这里设置了recycle的adapter，之后会在adapter内部生成合适的格子
        //用于装载大运

    }













//接下来打印旺相休囚死
    //如果月令是木
    //木	旺
    //火	相
    //水	休
    //金	囚
    //土	死


//五行	状态
//火	旺
//土	相
//木	休
//水	囚
//金	死


//金当令。
//
//五行	状态
//金	旺
//水	相
//土	休
//火	囚
//木	死


    //水当令。
    //
    //五行	状态
    //水	旺
    //木	相
    //金	休
    //土	囚
    //火	死




}
