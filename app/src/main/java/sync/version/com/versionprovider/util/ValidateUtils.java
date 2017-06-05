package sync.version.com.versionprovider.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by Miley_Ren on 2017/5/21.
 */

public class ValidateUtils {
    public static boolean isNotEmpty(String strValue){
        boolean ret = false;
        if(TextUtils.isEmpty(strValue)){
            ret=false;
        }
        else{
            ret = true;
        }
        return  ret;
    }
    /**检查SD卡是否可用*/
    public static boolean checkSDCard(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean createFileDir(String fileDir){
        String path = fileDir.trim();
        File file = new File(path);
        Boolean ret = false;
        if(!file.exists()){
            ret = file.mkdirs();
        }
        return ret;
    }
}
