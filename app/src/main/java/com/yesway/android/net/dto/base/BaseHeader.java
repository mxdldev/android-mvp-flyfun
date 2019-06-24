package com.yesway.android.net.dto.base;


import com.yesway.android.net.util.ConstantUtils;

/**
 * Description: <头信息><br>
 * Author:      mxdl<br>
 * Date:        2018/6/6<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class BaseHeader {
    protected NtspHeader ntspheader;

    public NtspHeader getNtspheader() {
        return ntspheader;
    }

    public void setNtspheader(NtspHeader ntspheader) {
        this.ntspheader = ntspheader;
    }
    public static BaseHeader newInstance(){
        BaseHeader baseHeader = new BaseHeader();
        baseHeader.setNtspheader(new NtspHeader.Builder().appkey(ConstantUtils.appkey).bulder());
        return baseHeader;
    }

}
