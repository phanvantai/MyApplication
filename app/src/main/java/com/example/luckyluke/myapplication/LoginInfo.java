package com.example.luckyluke.myapplication;

class LoginInfo {
    private String mUserName;
    private String mPassword;

    LoginInfo (String username, String password) {
        mUserName = username;
        mPassword = password;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getUserName() {
        return mUserName;
    }

    public boolean equals(LoginInfo member) {
        if (member.mPassword.equals(this.mPassword) && member.mUserName.equals(this.mUserName))
            return true;
        return false;
    }
}
