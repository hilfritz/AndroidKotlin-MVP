package com.hilfritz.samplekotlin.util;

import java.net.UnknownHostException;

/**
 * Created by hilfritz on 2/12/17.
 */

public class ExceptionUtil {

    public static final boolean isNoNetworkException(Throwable e){
        if (e !=null){
            if (e instanceof UnknownHostException){
                return true;
            }
        }
        return false;
    }
}
