package baZiSever;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import com.example.mybazi.R;
public class BaZiInfo {

    private static final Map<String, Integer> RES_MAP = new HashMap<>();

    static {
        // 天干
        RES_MAP.put("甲", R.string.info_jia);
        RES_MAP.put("乙", R.string.info_yi);
        RES_MAP.put("丙", R.string.info_bing);
        RES_MAP.put("丁", R.string.info_ding);
        RES_MAP.put("戊", R.string.info_wu);
        RES_MAP.put("己", R.string.info_ji);
        RES_MAP.put("庚", R.string.info_geng);
        RES_MAP.put("辛", R.string.info_xin);
        RES_MAP.put("壬", R.string.info_ren);
        RES_MAP.put("癸", R.string.info_gui);

        // 地支
        RES_MAP.put("子", R.string.info_zi);
        RES_MAP.put("丑", R.string.info_chou);
        RES_MAP.put("寅", R.string.info_yin);
        RES_MAP.put("卯", R.string.info_mao);
        RES_MAP.put("辰", R.string.info_chen);
        RES_MAP.put("巳", R.string.info_si);
        RES_MAP.put("午", R.string.info_wu2);
        RES_MAP.put("未", R.string.info_wei);
        RES_MAP.put("申", R.string.info_shen);
        RES_MAP.put("酉", R.string.info_you);
        RES_MAP.put("戌", R.string.info_xu);
        RES_MAP.put("亥", R.string.info_hai);
    }

    // 注意这里加了Context参数，用来读strings.xml
    public static String get(Context context, String ganOrZhi) {
        Integer resId = RES_MAP.get(ganOrZhi);
        return resId != null ? context.getString(resId) : "暂无资料";
    }

}