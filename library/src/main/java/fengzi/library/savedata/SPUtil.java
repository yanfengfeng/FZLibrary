package fengzi.library.savedata;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @类功能说明:(本地小数据保存)
 * @author yff
 * @date 2016年10月14日 下午2:57:11
 * @version V2.0
 * @备注(1.数据使用的SharedPreferences的方式,做的数据保存.
 * 2.而且保存的数据只能保存字符串.
 * 3.调用的时候,请不要直接使用,可以自己写一个类继承这个父类,从而实现子类需要实现的方法)
 */
public class SPUtil {

	static SPUtil spUtil;

	private  List<String> list_k = new ArrayList<String>();

	private  List<String> list_v = new ArrayList<String>();

	private  SharedPreferences sp;

	private static String TABELNAME = "SPUtil";

	private static Context context;

	public synchronized static SPUtil getIntance(Context ctt) {
		if(spUtil == null){
			spUtil = new SPUtil();
		}
		context = ctt;
		if(TextUtils.isEmpty(TABELNAME)){
			TABELNAME = ctt.getPackageName();
		}
		return spUtil;
	}

	public   void save(String key, String value) {
		clearData();
		list_k.add(key);
		list_v.add(value);
		saveData(list_k, list_v);
	}

	public   void save(List<String> list_k, List<String> list_v) {
		clearData();
		saveData(list_k, list_v);
	}

	private   void saveData(List<String> list_k, List<String> list_v) {

		if (context == null) {
			return;
		}
		if (sp == null) {
			sp = context.getSharedPreferences(TABELNAME, Context.MODE_PRIVATE);
		}
		Editor et = sp.edit();
		for (int i = 0; i < list_k.size(); i++) {
			et.putString(list_k.get(i), list_v.get(i));
		}
		et.commit();
	}

	public   String get(String key) {

		return getSp().getString(key, "");
	}

	public void saveObject(String key,Object o){
		if(o instanceof String ){
			getEdit().putString(key, (String) o);
		}else if(o instanceof Boolean){
			getEdit().putBoolean(key, (Boolean) o);
		}else if(o instanceof Integer){
			getEdit().putInt(key, (Integer) o);
		}else if(o instanceof Long){
			getEdit().putLong(key, (Long) o);
		}else if(o instanceof Float){
			getEdit().putFloat(key, (Float) o);
		}
	}

	SharedPreferences getSp(){
		if (sp == null) {
			sp = context.getSharedPreferences(TABELNAME, Context.MODE_PRIVATE);
		}
		return sp;
	}

	Editor getEdit(){

		return getSp().edit();
	}


	private void clearData() {
		list_k.clear();
		list_v.clear();
	}

}
