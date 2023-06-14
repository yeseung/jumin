package com.gongdaeoppa.jumin;

public class JuminCheck {

    private int make_num = 0, sum = 0, verificationNum = 0;
    private String birth_gender, tmpJumin, codeJumin = "";
    private float tmp = 0.0f;
    private int[] tmpJuminArr = new int[12];
    private int[] checkNum = {2,3,4,5,6,7,8,9,2,3,4,5};


    public JuminCheck(String _birth_gender, int _make_num) {

        this.birth_gender = _birth_gender;
        this.make_num = _make_num;
    }

    private String juminVerification(){

        tmpJumin = birth_gender + String.valueOf(Math.random()).substring(2, 7);

        for (int i = 0; i < tmpJumin.length(); i++){
            tmpJuminArr[i] = Integer.parseInt(tmpJumin.substring(i, i+1));
        }

        for (int i = 0; i < tmpJuminArr.length; i++){
            sum += tmpJuminArr[i] * checkNum[i];
        }

        tmp = (int)(sum / 11.0f) * 11.0f + 11.0f - sum;
        sum = 0;
        verificationNum = (int)(tmp - (int)(tmp / 10.0f) * 10.0f);

        return tmpJumin.substring(0, 6) + "-" + tmpJumin.substring(6) + verificationNum + "\n";
    }


    public String result(){

        for (int i = 0 ; i < make_num ; i++){
            codeJumin += juminVerification();
        }
        return codeJumin;
    }
}

