package com.aytekincomez.yeniapp.Activity.Fragment.message;

import com.aytekincomez.yeniapp.Activity.Model.MessageList;

import java.util.List;

public interface FragmentMessageView {
    void successMessage(String message);
    void errorMessage(String message);
    void onGetResult(List<MessageList> messageLists);
}
