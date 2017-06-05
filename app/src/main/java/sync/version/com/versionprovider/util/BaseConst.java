package sync.version.com.versionprovider.util;

/**
 * Created by Miley_Ren on 2017/6/3.
 */

public class BaseConst {
    public static final String VERSION_TABLE_NAME = "version";
    public static final String OPERATER_AUTHORITY="com.operaterapp.DataHelper.provider";

    //服务器上信息
    public static final String SERVER_IP = "49.52.10.237";
    public static final String SERVER_BASE_URL="http://"+BaseConst.SERVER_IP+"/vending";
    public static final String GET_VERSION_SERVER_JSON = BaseConst.SERVER_BASE_URL+"/operater_version.json";
    public static final String DOWNLOAD_PATH = "download_cache";
    public static final String NEW_OPERATER_CLIENT_APK_NAME = "OperaterApp.apk";

    public static final String NEW_OPERATER_APK_DOWNLOAD_PATH = BaseConst.SERVER_BASE_URL+ "/app-debug.apk";

}
