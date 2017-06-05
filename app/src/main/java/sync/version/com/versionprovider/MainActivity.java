package sync.version.com.versionprovider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends  UpdateApk  implements View.OnClickListener{
    File file = null;
    Button getLocalVersion,downloadApk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLocalVersion = (Button) findViewById(R.id.getLocalVersion);
        downloadApk = (Button)findViewById(R.id.downloadApk);

        getLocalVersion.setOnClickListener(this);
        downloadApk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getLocalVersion:
                boolean versionflag = hasNewVersion();
                Toast.makeText(this,"是否更新："+versionflag,Toast.LENGTH_SHORT).show();
                if(versionflag){
                    file = downloadClientApk();
                    if(file!=null)
                         Toast.makeText(this,"下载成功",Toast.LENGTH_SHORT).show();
                     installAPK(file);
                }
                break;
            case R.id.downloadApk:
                startApp();
                break;
            default:break;
        }
    }
}
