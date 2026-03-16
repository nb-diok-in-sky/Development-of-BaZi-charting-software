package baZiSever;


import static android.view.View.VISIBLE;

import static baZiSever.Tools.getDiShi;

import com.example.mybazi.databinding.ActivityMainBinding;
import com.nlf.calendar.EightChar;
import com.nlf.calendar.eightchar.DaYun;
import com.nlf.calendar.eightchar.Yun;
import com.nlf.calendar.util.LunarUtil;

import android.content.Context;
import android.widget.TextView;

import java.util.Arrays;
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
        List<YunNianInfo> daYunList = YunNianFactory.buildDaYunList(daYuns,DayGan);
        //运用之前的list方法，把大运分解成干支和年龄和年份
        //在点击大运方法内部还需要新增点击流年的方法，和大运的内容一样
        List<YunNianInfo> defaultList = YunNianFactory.buildXiaoYunList(daYuns,DayGan);//先用第1个大运初始化  这是默认第1个大运 第0个大运是出生年月的小运

        LiuNianAdapter liuNianAdapter = new LiuNianAdapter(defaultList,(yunNianInfo,position) ->{

           bindLiuNianToTextView(yunNianInfo,DayGan);

        });
        binding.recLiuNian.setAdapter(liuNianAdapter);//这里设置了recycle的adapter，之后会在adapter内部生成合适的格子
        //用于装载流年  默认流年是第0个流年

        //之后需要把这个list拿去继续拆分，挨个上色
        DaYunAdapter daYunAdapter = new DaYunAdapter(daYunList,(yunNianInfo, position) -> {//上面是点击事件，点击点击大运，获取位置，然后执行方法
            //现在是点击流年的方法 在这里要点击之后把数据，数据传入textview已经在liunianadapter里面封装好了传进recycle里面

            if(position == 0){
                List<YunNianInfo> LiuNianList = YunNianFactory.buildXiaoYunList(daYuns,DayGan);
                liuNianAdapter.updateData(LiuNianList);


                binding.tvYunStar.setText("");
                binding.tvYunGan.setText("");
                binding.tvYunZhi.setText("");
                binding.tvYunHideGan.setText("");
                binding.tvYunZiZuo.setText("");
                binding.tvYunNaYin.setText("");

            }
            else {
                List<YunNianInfo> LiuNianList = YunNianFactory.buildLiuNianList(daYuns[position], DayGan);
                //点击大运之后根据所在位置新建一个流年list用来装载流年
                liuNianAdapter.updateData(LiuNianList);

                YunNianInfo defaultLiuNian = LiuNianList.get(0);
                bindLiuNianToTextView(defaultLiuNian,DayGan);
                //下面是流年的逻辑，在点击事件切换大运之后，流年要默认传输第一位也就是大运的第一个流年而不是之前原本的流年

//下面这些是大运的逻辑  负责settext到那个textview上面去

                binding.tvYunStar.setText(yunNianInfo.shiShenTianGan);
                binding.tvYunGan.setText( Tools.setColorOfGanZhi(yunNianInfo.tiangan));
                binding.tvYunZhi.setText(Tools.setColorOfGanZhi(yunNianInfo.dizhi));
                binding.tvYunHideGan.setText(Tools.setColorOfHideGanShiShen(context,yunNianInfo.cangGan,yunNianInfo.shiShenCangGan));

                binding.tvYunZiZuo.setText(getDiShi(DayGan, Arrays.asList(LunarUtil.ZHI).indexOf(yunNianInfo.dizhi)));
                binding.tvYunNaYin.setText(LunarUtil.NAYIN.get(yunNianInfo.tiangan+yunNianInfo.dizhi));



            }



//这里是在做点击大运，刷新流年
        });
        binding.recDaYun.setAdapter(daYunAdapter);//这里设置了recycle的adapter，之后会在adapter内部生成合适的格子
        //用于装载大运

    }



    public  void bindLiuNianToTextView(YunNianInfo yunNianInfo,String DayGan){

        binding.tvLiuStar.setText(yunNianInfo.shiShenTianGan);
        binding.tvLiuGan.setText(Tools.setColorOfGanZhi(yunNianInfo.tiangan));
        binding.tvLiuZhi.setText(Tools.setColorOfGanZhi(yunNianInfo.dizhi));
        binding.tvLiuHideGan.setText(Tools.setColorOfHideGanShiShen(context,yunNianInfo.cangGan,yunNianInfo.shiShenCangGan));

        binding.tvLiuZiZuo.setText(getDiShi(DayGan, Arrays.asList(LunarUtil.ZHI).indexOf(yunNianInfo.dizhi)));
        binding.tvLiuNaYin.setText(LunarUtil.NAYIN.get(yunNianInfo.tiangan+yunNianInfo.dizhi));

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
