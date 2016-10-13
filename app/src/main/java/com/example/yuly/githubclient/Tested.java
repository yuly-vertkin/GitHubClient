/*
 * Copyright (c) New Cloud Technologies, Ltd., 2013-2016
 *
 * You can not use the contents of the file in any way without New Cloud Technologies, Ltd. written permission.
 * To obtain such a permit, you should contact New Cloud Technologies, Ltd. at http://ncloudtech.com/contact.html
 *
 */

package com.example.yuly.githubclient;

import android.content.Context;

public class Tested {
    private String str;

    public Tested(Context context) {
        this.str = context.getString(R.string.app_name);
    }

    public String getStr() {
        return str;
    }
}
