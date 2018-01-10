package com.zalo;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* Required By Zalo */
import java.util.Date;

import com.zing.zalo.zalosdk.common.Utils;
import com.zing.zalo.zalosdk.core.log.Log;
import com.zing.zalo.zalosdk.oauth.AuthenticateExtention;
import com.zing.zalo.zalosdk.oauth.LoginForm;
import com.zing.zalo.zalosdk.oauth.LoginForm.ShowProtectGuestAccountListener;
import com.zing.zalo.zalosdk.oauth.LoginVia;
import com.zing.zalo.zalosdk.oauth.OAuthCompleteListener;
import com.zing.zalo.zalosdk.oauth.OauthResponse;
import com.zing.zalo.zalosdk.oauth.ValidateOAuthCodeCallback;
import com.zing.zalo.zalosdk.oauth.ZaloSDK;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class echoes a string called from JavaScript.
 */
public class Zalo extends CordovaPlugin {
  public static String userId = "";
  private LoginListener listener;
  private LoginForm loginForm;
  private ImageView imgViewLoginFormBg;
  private Spinner zalo_login_type;
  private Spinner show_login_form_type;
  private String showProtectAccType ;

  AuthenticateExtention authenticateExtention;

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	  if (action.equals("login")) {
  	  String message = args.getString(0);
      this.echo(message, callbackContext);
      return true;
	  }
    return false;
  }

  private void echo(String message, CallbackContext callbackContext) {
    if (message != null && message.length() > 0) {
      callbackContext.success(message);
    }
    else{
      callbackContext.error("Expected one non-empty string argument.");
    }
  }

  // Login callback.
  /*
  private class LoginListener extends OAuthCompleteListener {
    private Activity ctx;
    public LoginListener(Activity ctx) {
      this.ctx = ctx;
    }

    @Override
    public void onSkipProtectAcc(Dialog dialog) {
      dialog.dismiss();
    }

    @Override
    public void onProtectAccComplete(int errorCode, String message, Dialog dialog) {
      if (errorCode == 0) {
        ZaloSDK.Instance.isAuthenticate(new ValidateOAuthCodeCallback() {
          @Override
          public void onValidateComplete(boolean validated, int error_Code, long userId, String oauthCode) {
          }
        });
        
        dialog.dismiss();
      }
      
      com.zing.zalo.zalosdk.payment.direct.Utils.showAlertDialog(ctx, message, null);
    }

    @Override
    public void onAuthenError(int errorCode, String message) {
      if (ctx != null && !TextUtils.isEmpty(message)) com.zing.zalo.zalosdk.payment.direct.Utils.showAlertDialog(ctx, message, null);
      super.onAuthenError(errorCode, message);
    }

    @Override
    public void onGetOAuthComplete(OauthResponse response) {
      android.util.Log.i("debuglog", "register thành cmn công!!!! response.isRegister(): " + response.isRegister());
      super.onGetOAuthComplete(response);
      if (!HomeActivity.DISABLE_SUBMIT_APP_USER) ZaloSDK.Instance.submitAppUserData("" + ZaloSDK.Instance.getZaloId(), ZaloSDK.Instance.getLastestLoginChannel(), "zalo", "appUTMSource", null);
      if (response.isRegister()) {
        Toast.makeText(OAuthDemoActivity.this, "Đăng kí thành công!", Toast.LENGTH_LONG).show();
      }
      else{
        Toast.makeText(OAuthDemoActivity.this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
      }
      
      Toast.makeText(OAuthDemoActivity.this, String.valueOf(response.getuId()), Toast.LENGTH_LONG).show();
      userId = String.valueOf(response.getuId());
      getProfile();
      ctx = null;
      
      if (loginForm.getVisibility() == View.VISIBLE){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
      }
    }
  }
  */

}
