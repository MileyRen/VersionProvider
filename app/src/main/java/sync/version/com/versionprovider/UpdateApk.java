package sync.version.com.versionprovider;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import sync.version.com.versionprovider.util.BaseConst;
import sync.version.com.versionprovider.util.HttpURLConnUtil;

public class UpdateApk extends AppCompatActivity {
    //install flags 状态，详见PackageManager
    private static final int INSTALL_REPLACE_EXISTING = 0X00000002;
    /**
     * 检测服务器上是否有新版本*
     *
     * @return boolean 服务器上版本和本地版本是否一致 true为一致，false为不一致*/
    public boolean hasNewVersion(){
        boolean result = false;
        //先获取操作员的apk版本
        String versionName  = null;
        Integer versionCode = null;
        Uri uri = Uri.parse("content://"+ BaseConst.OPERATER_AUTHORITY+"/"+BaseConst.VERSION_TABLE_NAME);
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                versionName = cursor.getString(cursor.getColumnIndex("versionName"));
                versionCode = cursor.getInt(cursor.getColumnIndex("versionCode"));
            }
            cursor.close();
        }
        //获取服务器上版本
        String remoteVersion = getRemoteVersion();
        try {
            if(remoteVersion != null){
            JSONObject  rVersion = new JSONObject(remoteVersion);
            String  rversonName = rVersion.getString("versionName").trim();
            int  rversionCode = rVersion.getInt("versionCode");
            if(rversonName!=null&&!rversonName.equals(versionName)&&rversionCode!=versionCode){
                result = true;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  result;
    }

    /**获取服务器上的apk版本**/
    public String getRemoteVersion(){
        String URL = BaseConst.GET_VERSION_SERVER_JSON;
        String response=null;
        try {
            response = HttpURLConnUtil.getByResponse(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  response;
    }
    /***
     * 下载服务器上apk新版本
     * @retutn*/
    public File downloadClientApk(){
        String URL = BaseConst.NEW_OPERATER_APK_DOWNLOAD_PATH;
        File file = null;
        try {
            file = HttpURLConnUtil.downloadTask(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  file;
    }
    /**安装新版本**/
    public void installAPK(File file){
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //执行数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
        //android.os.Process.killProcess(android.os.Process.myPid());//提示完成、打开
    }
    public void startApp(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ComponentName cn = new ComponentName("com.operaterapp", "com.operaterapp.MainActivity");
        intent.setComponent(cn);
        startActivity(intent);
    }

}
