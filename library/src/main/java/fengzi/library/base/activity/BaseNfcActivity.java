package fengzi.library.base.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.support.annotation.Nullable;

import fengzi.library.nfc_or_bluetooth.NfcTypeData;
import fengzi.library.tool.LogUtil;
import fengzi.library.tool.ToastUtil;

/**
 * 作者: {yff} time: 2017/3/29  11:12
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{需要添加nfc权限  <uses-permission android:name="android.permission.NFC" />}
 */

public abstract class BaseNfcActivity extends FBaseActivity {

    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    NfcType type;
    NfcTypeData nfctypeData;
    //需要写入的标签
    String writeTag = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNfc();
    }

    void initNfc() {
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null) {
            ToastUtil.show(this, "该设备不支持nfc功能");
            finish();
            return;
        } else if (!mAdapter.isEnabled()) {
            ToastUtil.show(this, "请打开NFC功能");
            finish();
            return;
        }
        nfctypeData = new NfcTypeData();
        // 初始化PendingIntent，当有NFC设备连接上的时候，就交给当前Activity处理
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        // 新建IntentFilter，使用的是第二种的过滤机制
        IntentFilter techFilter = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
//        IntentFilter neffFilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
//        IntentFilter tagFilter = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
//        techFilter.addCategory(Intent.CATEGORY_DEFAULT);

        mFilters = new IntentFilter[]{techFilter};
        mTechLists = new String[][]{new String[]{Ndef.class.getName()},
                new String[]{NdefFormatable.class.getName()},
                new String[]{NfcF.class.getName()}};
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAdapter != null) mAdapter.disableForegroundDispatch(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter != null)
            mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
                    mTechLists);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        LogUtil.d("ibfo", "=======onNewIntent========" + intent.getAction());
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action) || NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag == null) {
                LogUtil.e("=====intent is tag null=======");
                return;
            }
            resolveIntent(tag);
        }
        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)){
            toast("=====ndef tag======");
        }
    }

    private void resolveIntent(Tag tag) {

        if (!nfctypeData.cardIsFormart(tag)) {
            if (!nfctypeData.formatCard(tag)) {
                toast("卡片格式化失败");
                return;
            }
        }
        try {
            if (type == NfcType.readId || type == null) {
                readCardId(nfctypeData.getCardInfo(tag));
            } else if (type == NfcType.writeTag) {
                writeCardCallBack(nfctypeData.writeNdefData(tag, writeTag));
            } else if (type == NfcType.readTag) {
                readCardTag(nfctypeData.readNdefData(tag));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("=========Tag异常============" + e.getMessage());
        }
    }


    /**
     * 改变nfc的读写状态
     *
     * @param type
     */
    protected void readOrwriteStatue(NfcType type) {
        this.type = type;
    }

    /**
     * 读取card的id
     *
     * @param msg
     */
    protected abstract void readCardId(String msg);

    /**
     * 读取卡片的标签
     *
     * @param msg
     */
    protected abstract void readCardTag(String msg);

    /**
     * 写入的数据是否成功
     *
     * @param isSucces
     */
    protected abstract void writeCardCallBack(boolean isSucces);

    /**
     * 往卡片里面写入信息
     *
     * @param msg
     */
    protected void writeCardTag(String msg) {
        writeTag = msg;
        readOrwriteStatue(NfcType.writeTag);
    }

    public enum NfcType {
        readId,

        readTag,

        writeTag
    }

}
