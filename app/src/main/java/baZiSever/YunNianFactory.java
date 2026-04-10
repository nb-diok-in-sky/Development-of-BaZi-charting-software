package baZiSever;

import com.nlf.calendar.eightchar.DaYun;
import com.nlf.calendar.eightchar.LiuNian;
import com.nlf.calendar.eightchar.XiaoYun;
import com.nlf.calendar.util.LunarUtil;

import java.util.ArrayList;
import java.util.List;
public class YunNianFactory {




   //这个类是用来赋予天干和地支颜色以及十神和藏干和藏干十神的肥肠重要
    //就是把大运和六年的的初始数据，转化成YunNianInfo数据，转化完了就可以直接用了


public static List<YunNianInfo>  buildDaYunList(DaYun[] daYunArr,String dayGan){//这里面要存大运和日干 之后套用方法来分析相对关系，获取十神
    List<YunNianInfo> list = new ArrayList<>();
   YunNianInfo xiaoYunInfo = new YunNianInfo();
    //新建一个小运的信息

    xiaoYunInfo.startYear =daYunArr[0].getStartYear();
    xiaoYunInfo.startAge = daYunArr[0].getStartAge();
    xiaoYunInfo.tiangan = "小运";
    xiaoYunInfo.dizhi = "";
    list.add(xiaoYunInfo);

    //存完了之后需要进行拆解，将每个大运分开来拆成干和地支还有年龄和事件   dayunarr是大运数组  它可以拆分成很多很多数据
    //现在拆一下

    for(int i = 1;i<daYunArr.length;i++){//这一段是每次从DaYun这个list类里面拆出来一个数据，然后用add来分解来获取其中的年份年龄和干支 然后塞进daYunArr里面

        list.add(build(daYunArr[i].getGanZhi(),daYunArr[i].getStartYear(),daYunArr[i].getStartAge(),dayGan
        ));

    }

    return list;
}

//接下来获取流年信息 也是差不多的逻辑
//流年是10年大运里面的每一年  lunar库里面也有获取的方法
    public static List<YunNianInfo>  buildLiuNianList(DaYun daYun, String dayGan){//这里面要存大运和日干 之后套用方法来分析相对关系，获取十神
        List<YunNianInfo> list = new ArrayList<>();
        //存完了之后需要进行拆解，将每个大运分开来拆成干和地支还有年龄和事件   d组  它可以拆分成很多很多数据
        //流年的信息也包含几几年 年龄和具体的干支 和干支十神
        //先获取流年  库里直接给了流年算法 真好
        LiuNian[] liuNianArr = daYun.getLiuNian();//这里存了10个流年，接下来分别build就可以了

        for(LiuNian liuNian:liuNianArr){

        list.add(build(liuNian.getGanZhi(),liuNian.getYear(),liuNian.getAge(),dayGan));
        }

        return list;
    }
    public static List<YunNianInfo>  buildXiaoYunList(DaYun[] daYunArr, String dayGan){//这里面要存大运和日干 之后套用方法来分析相对关系，获取十神
        List<YunNianInfo> list = new ArrayList<>();
        //存完了之后需要进行拆解，将每个大运分开来拆成干和地支还有年龄和事件   d组  它可以拆分成很多很多数据
        //流年的信息也包含几几年 年龄和具体的干支 和干支十神
        //先获取流年  库里直接给了流年算法 真好

        for (XiaoYun xiaoYun : daYunArr[0].getXiaoYun()) {
            list.add(build(xiaoYun.getGanZhi(), xiaoYun.getYear(), xiaoYun.getAge(), dayGan));
        }
        return  list;
}

//来一个方法拼接流年和小运   当arr为0的时候   这条代码会执行







        //获取天干地支 藏干  和藏干十神的的方法
private static YunNianInfo build(String ganZhi,int startYear,int startAge,String dayGan){
//这里就是要处理干支关系了，
YunNianInfo yunNianInfo = new YunNianInfo();

    yunNianInfo.startAge  = startAge;
    yunNianInfo.startYear = startYear;
    yunNianInfo.tiangan = String.valueOf(ganZhi.charAt(0));//这里是把ganzhi的第一个字也就是天干取出来
    yunNianInfo.dizhi = String.valueOf(ganZhi.charAt(1));//这是把地支取出来存进去

    //接下来需要根据天干和地支与日干的相对关系来进行十神的传入
    String shiShen = LunarUtil.SHI_SHEN.get(dayGan + yunNianInfo.tiangan);
    yunNianInfo.shiShenTianGan = shiShen != null ? shiShen : "";

    //天干的十神和藏干已经做完了，还有地支的藏干和十神没有做完
    //由于地支藏干和地支十神都是多个数据，所以需要新建数组来存储
    yunNianInfo.cangGan  = LunarUtil.ZHI_HIDE_GAN.get(yunNianInfo.dizhi);//这里的意思是用地支来查找对应的藏干



    //找到了地支藏干那接下来就需要进行藏干到十神的转化  其实还是需要拼接字符
//依旧for
    //下面是藏干变十神的东西
    if (yunNianInfo.cangGan != null) {
        for (String shishencanggan : yunNianInfo.cangGan)//每一次循环，取出来shishencanggan里面的一条
        { //这里是计算十神藏干   计算方式是找到日主 和对应的字 然后返回一个十神   所以需要先凑一个日主＋某一个藏干的组合
            //由于藏干会有很多个 所以需要将藏干一个一个传入进去，再与日干拼接，最后返回十神

            String cangGanShiShen = LunarUtil.SHI_SHEN.get(dayGan + shishencanggan);
            yunNianInfo.shiShenCangGan.add(cangGanShiShen != null ? cangGanShiShen : "");
        }
    }

    return yunNianInfo ;
}



}
