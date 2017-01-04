package com.example.administrator.testbmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.testbmob.bean.Person;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        createInfo();
//        findInfo();
//        updateInfo();
        deleteInfo();
    }

    private void deleteInfo() {
       final Person p2 = new Person();
        p2.setObjectId("4a98277eeb");
        p2.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this, "删除成功:"+p2.getUpdatedAt(), Toast.LENGTH_SHORT).show();
//                    toast("删除成功:"+p2.getUpdatedAt());
                }else{
                Toast.makeText(MainActivity.this, "删除失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    toast("删除失败：" + e.getMessage());
                }
            }

        });
    }

    private void updateInfo() {
//更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
       final Person p2 = new Person();
        p2.setAddress("北京朝阳");
        p2.update("d30ead4e95", new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this, "更新成功:"+p2.getUpdatedAt(), Toast.LENGTH_SHORT).show();
//                    toast("更新成功:"+p2.getUpdatedAt());
                }else{
                    Toast.makeText(MainActivity.this, "更新失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    toast("更新失败：" + e.getMessage());
                }
            }

        });
    }

    private void findInfo() {
//查找Person表里面id为6b6c11c537的数据
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("d30ead4e95", new QueryListener<Person>() {
            @Override
            public void done(Person object,BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "查询失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    toast("查询失败：" + e.getMessage());
                }
            }
        });
    }

    private void createInfo() {
        Person p2 = new Person();
        p2.setName("wang");
        p2.setAddress("吉林长春");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this, "添加数据成功，返回objectId为："+objectId, Toast.LENGTH_SHORT).show();
//                    toast("添加数据成功，返回objectId为："+objectId);
                }else{
                    Toast.makeText(MainActivity.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();

//                    toast("创建数据失败：" + e.getMessage());
                }
            }
        });
    }

    private void init() {
//        Bmob.initialize(this,"29449acda1bd3e7e7cfe30d3ef40b058");
//        第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
        //设置appkey
        .setApplicationId("29449acda1bd3e7e7cfe30d3ef40b058")
        //请求超时时间（单位为秒）：默认15s
        .setConnectTimeout(30)
        //文件分片上传时每片的大小（单位字节），默认512*1024
        .setUploadBlockSize(1024*1024)
        //文件的过期时间(单位为秒)：默认1800s
        .setFileExpiration(2500)
        .build();
        Bmob.initialize(config);
    }
}
