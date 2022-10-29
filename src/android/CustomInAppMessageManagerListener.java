package com.appboy.cordova;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import com.braze.models.inappmessage.IInAppMessage;
import com.braze.models.inappmessage.MessageButton;
import com.braze.ui.inappmessage.InAppMessageCloser;
import com.braze.ui.inappmessage.InAppMessageOperation;
import com.braze.ui.inappmessage.listeners.IInAppMessageManagerListener;
import com.braze.support.BrazeLogger;

import java.util.Map;

public class CustomInAppMessageManagerListener implements IInAppMessageManagerListener {
  private final Activity mActivity;
  private final CallbackContext mCallbackContext;
  private static final String TAG = BrazeLogger.getBrazeLogTag(CustomInAppMessageManagerListener.class);

  public CustomInAppMessageManagerListener(Activity activity, final CallbackContext callbackContext) {
    mActivity = activity;
    mCallbackContext = callbackContext;
  }


  @Override
  public boolean onInAppMessageButtonClicked(IInAppMessage inAppMessage, MessageButton button, InAppMessageCloser inAppMessageCloser) {
    String message = "The button click was ignored." + button.getId();
    Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    BrazeLogger.v(TAG, "The button click was ignored.");
    // Closing should not be animated if transitioning to a new activity.
    // If remaining in the same activity, closing should be animated.
    PluginResult result = new PluginResult(PluginResult.Status.OK);
    result.setKeepCallback(true);
    mCallbackContext.sendPluginResult(result);
    inAppMessageCloser.close(true);
    return true;
  }
}
