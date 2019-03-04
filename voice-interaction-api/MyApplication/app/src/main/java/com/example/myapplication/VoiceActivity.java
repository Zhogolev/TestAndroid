package com.example.myapplication;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;

public class VoiceActivity extends Activity {
    private Bundle bundle = new Bundle();
    class Confirm extends VoiceInteractor.ConfirmationRequest {
        Confirm(String ttsPrompt, String visualPrompt) {
           super(new VoiceInteractor.Prompt(
                    new String[]{ttsPrompt}, visualPrompt), null);
        }

        @Override
        public void onConfirmationResult(
                boolean confirmed, Bundle null) {
            if (confirmed) {
               Log.d("Confirm", "on cofirm");
                // doAction();
            }
            finish();
        }
    }

    ;

    @Override
    public void onResume() {
        super.onResume();
        if (isVoiceInteraction()) {
            String ttsPrompt = "confirmation tts";
            String visualPrompt = "confirmation visual text";
            getVoiceInteractor().sendRequest(new Confirm(ttsPrompt, visualPrompt));
        } else {
            finish();
        }
    }
}
