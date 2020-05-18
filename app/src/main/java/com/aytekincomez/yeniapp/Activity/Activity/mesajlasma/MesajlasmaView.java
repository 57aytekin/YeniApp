package com.aytekincomez.yeniapp.Activity.Activity.mesajlasma;

import java.util.Map;

public interface MesajlasmaView {
    void showProgress();
    void hideProgress();
    void onGetResult(Map<String, String> map) ;
}
